package wc;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.HashMap;
import java.util.Map;

/**
 * 第三级组件，用于单词计数
 */
public class WcTotalBolt extends BaseRichBolt {

    // 输出流
    private OutputCollector collector;

    // 存数据的 Map 集合
    private Map<String, Integer> result = new HashMap<String, Integer>();


    // TODO Auto-generated method stub
    /**
     * 初始化 Bolt
     */
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.collector = outputCollector;
    }

    /**
     * 清洗处理数据并发送到下一个组件
     *  对每个单词进行计数
     */
    public void execute(Tuple tuple) {
        // 取出数据
        String word = tuple.getStringByField("word");
        int count = tuple.getIntegerByField("count");

        // 存到 map 集合当中 (map 集合，key相同，值会被覆盖) 有则更新无则添加
        if (result.containsKey(word)) {
            int total = result.get(word);
            result.put(word, total + count);
        } else {
            result.put(word, count);
        }

        System.out.println(" 统计 " + result);

        // 将结果发送到下一个组件
        this.collector.emit(new Values(word, result.get(word)));
    }

    /**
     * 声明输出到下一级组件的tuple格式 (schema)
     */
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("word", "total"));
    }
}
