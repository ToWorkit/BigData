package wc;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

/**
 * 任务主程序 创建任务 Topology
 */
public class WcTopology {
    public static void main(String[] args) throws InvalidTopologyException, AuthorizationException, AlreadyAliveException {
        TopologyBuilder builder = new TopologyBuilder();

        // 设置任务的 spout 组件
        builder.setSpout("wc_spout", new WcSpout());

        // 设置任务的单词拆分组件和数据源(上一个需要对接)的组件，分组模式为 随机
        builder.setBolt("wc_split", new WcSplitBolt()).shuffleGrouping("wc_spout");

        // 设置任务的单词求和组件和上一个需要的对接的组件，分组模式为 按字段分组
        builder.setBolt("wc_total", new WcTotalBolt()).fieldsGrouping("wc_split", new Fields("word"));

        // 创建任务 Topology
        StormTopology topology = builder.createTopology();

        // 创建 config 对象，保存配置信息
        Config config = new Config();

        /**
         * 本地模式
         */
/*        LocalCluster localCluster = new LocalCluster();
        // 别名，配置信息，topology
        localCluster.submitTopology("WC", config, topology);*/

        /**
         * 集群模式
         */
        StormSubmitter.submitTopology("WC", config, topology);
    }
}
