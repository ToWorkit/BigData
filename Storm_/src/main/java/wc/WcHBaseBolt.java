package wc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.io.IOException;
import java.util.Map;

public class WcHBaseBolt extends BaseRichBolt {

    // 定义一个HBase的表的客户端
    private HTable table = null;

    /**
     *  初始化Bolt 创建 HTable客户端
     */
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        Configuration configuration = new Configuration();
        // 指定zookeeper地址
        configuration.set("hbase.zookeeper.quorum", "192.168.220.11");

        // 创建HTable的客户端
        try {
            this.table = new HTable(configuration, "result");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 把上一个组件发送来的数据存入HBase
     */
    public void execute(Tuple tuple) {
        String word = tuple.getStringByField("word");
        // 上一个组件是以int形式传过来的
        int total = tuple.getIntegerByField("total");

        // 构建HBase 的 put 对象
        // 数据都是字节类型的
        Put put = new Put(Bytes.toBytes(word));
        put.add(Bytes.toBytes("info"), Bytes.toBytes("word"), Bytes.toBytes(word));
        // 需要转为String之后在进行字节转换
        put.add(Bytes.toBytes("info"), Bytes.toBytes("total"), Bytes.toBytes(String.valueOf(total)));

        try {
            this.table.put(put);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        // 不需要发送给下一个组件了
    }
}
