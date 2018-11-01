package wc;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;

import java.util.Map;
import java.util.Random;

/**
 * 第一级组件，任务的Spout组件来采集数据
 */
public class WcSpout extends BaseRichSpout {

    /**
     * 模拟数据
     */
    private String[] datas = { "I Love You", "You Love Me", "I Love You And  You Love Me" };
    // 需要定义一个变量用来保存输出流
    private SpoutOutputCollector collector;

    // 初始化
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        // SpoutOutputCollector spoutOutputCollector => 输出流
        this.collector = spoutOutputCollector;
    }

    /**
     * 将数据发送给下一个组件
     */
    public void nextTuple() {
        // 每隔两秒采集一次
        Utils.sleep(2000);

        /**
         * Storm的框架自行调用，如何接收数据
         */
        // 产生 3 以内的随机数 0, 1, 2
        int random = new Random().nextInt(3);
        // 获取数据
        String data = datas[random];

        System.out.println("采集的数据 " + random + " <----> " + data);

        // 将数据发送给下一个组件
        // 必须和声明的tuple格式一致符合schema结构
        this.collector.emit(new Values(data));

    }

    /**
     * 声明输出到下一级组件的tuple格式
     */
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("sentence"));
    }
}
