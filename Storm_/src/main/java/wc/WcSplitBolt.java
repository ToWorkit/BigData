package wc;


import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * 第二级组件 用于单词的拆分
 */
public class WcSplitBolt extends BaseRichBolt {

    // 输出流
    private OutputCollector collector;

    /**
     * 初始化 Bolt
     */
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.collector = outputCollector;
    }

    /**
     * 清洗数据并发送到下一个组件
     */
    public void execute(Tuple tuple) {
        // 接收数据 需要和上一个组件发送过来的 tuple 对接
        String data = tuple.getStringByField("sentence");

        // 分词
        String[] words = data.split(" ");

        // 输出
        for (String word : words) {
            this.collector.emit(new Values(word, 1));
        }

    }

    /**
     * 声明输出到下一级组件的tuple格式 (schema)
     */
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("word", "count"));
    }
}
