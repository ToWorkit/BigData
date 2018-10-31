package wc;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.hdfs.bolt.HdfsBolt;
import org.apache.storm.hdfs.bolt.format.DefaultFileNameFormat;
import org.apache.storm.hdfs.bolt.format.DelimitedRecordFormat;
import org.apache.storm.hdfs.bolt.rotation.FileSizeRotationPolicy;
import org.apache.storm.hdfs.bolt.sync.CountSyncPolicy;
import org.apache.storm.redis.bolt.RedisStoreBolt;
import org.apache.storm.redis.common.config.JedisPoolConfig;
import org.apache.storm.redis.common.mapper.RedisDataTypeDescription;
import org.apache.storm.redis.common.mapper.RedisStoreMapper;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.ITuple;

/**
 * 任务主程序 创建任务 Topology
 */
public class WcTopology {
    public static void main(String[] args) throws Exception {
        TopologyBuilder builder = new TopologyBuilder();

        // 设置任务的 spout 组件
        builder.setSpout("wc_spout", new WcSpout());

        // 设置任务的单词拆分组件和数据源(上一个需要对接)的组件，分组模式为 随机
        builder.setBolt("wc_split", new WcSplitBolt()).shuffleGrouping("wc_spout");

        // 设置任务的单词求和组件和上一个需要的对接的组件，分组模式为 按字段分组
        builder.setBolt("wc_total", new WcTotalBolt()).fieldsGrouping("wc_split", new Fields("word"));

        /**
         * 第三个Bolt组件，将结果保存到HDFS上，使用Storm提供的Bolt
         */
//        builder.setBolt("wc_hdfs", createHDFSBolt()).shuffleGrouping("wc_total");


        /**
         * 第三个Bolt组件，将结果保存的Redis上
         */
        builder.setBolt("wc_redis", createRedisBolt()).shuffleGrouping("wc_total");

        // 创建任务 Topology
        StormTopology topology = builder.createTopology();

        // 创建 config 对象，保存配置信息
        Config config = new Config();

        /**
         * 本地模式
         */
        LocalCluster localCluster = new LocalCluster();
        // 别名，配置信息，topology
        localCluster.submitTopology("WC", config, topology);

        /**
         * 集群模式
         */
//        StormSubmitter.submitTopology("WC", config, topology);
    }


    /**
     * 将结果统计到HDFS上
     */
    private static IRichBolt createHDFSBolt() {
        // 配置HDFS
        HdfsBolt bolt = new HdfsBolt();
        bolt.withFsUrl("hdfs://192.168.220.11:9000");

        // 设置保存到HDFS的位置目录
        bolt.withFileNameFormat(new DefaultFileNameFormat().withPath("/stormData"));
        // 保存的格式 <key, value> 设置数据保存到文件的分隔符为 --  <Love, 9>  -> love--9
        bolt.withRecordFormat(new DelimitedRecordFormat().withFieldDelimiter("--"));

        // 流式处理，每 5M 的数据生成一个文件
        bolt.withRotationPolicy(new FileSizeRotationPolicy(5.0f, FileSizeRotationPolicy.Units.MB));

        // 当输出tuple达到一定大小就会和HDFS进行一次同步
        bolt.withSyncPolicy(new CountSyncPolicy(1000));

        return bolt;
    }

    /**
     * 统计结果写入redis
     */
    private static IRichBolt createRedisBolt() {
        // 创建Redis的连接池
        JedisPoolConfig.Builder builder = new JedisPoolConfig.Builder();
        builder.setHost("192.168.220.11");
        builder.setPort(6379);
        JedisPoolConfig poolConfig = builder.build();

        // 指定存入Redis中的数据格式
        return new RedisStoreBolt(poolConfig, new RedisStoreMapper() {

            /**
             * 定义Redis中的数据类型
             *  使用Hash集合
             */
            public RedisDataTypeDescription getDataTypeDescription() {
                return new RedisDataTypeDescription(RedisDataTypeDescription.RedisDataType.HASH, "WordCount");
            }

            /**
             * 从上一个Tuple中取出值    总数
             */
            public String getKeyFromTuple(ITuple iTuple) {
                return String.valueOf(iTuple.getIntegerByField("total"));
            }

            /**
             * 从上一个Tuple中取出Key  单词
             */
            public String getValueFromTuple(ITuple iTuple) {
                return iTuple.getStringByField("word");
            }
        });
    }

}
