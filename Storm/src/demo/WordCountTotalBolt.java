package demo;

import java.util.HashMap;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

/*
 * 第二个bolt组件：单词的计数
 */
public class WordCountTotalBolt extends BaseRichBolt{
	//输出流
	private OutputCollector collector;
	
	//定义一个Map集合，用于保存最后的结果
	private Map<String, Integer> result = new HashMap<>();
	
	@Override
	public void execute(Tuple tuple) {
		//如何处理上一个组件发来的数据
		//获取数据： 单词、频率：1
		String word = tuple.getStringByField("word");
		int count = tuple.getIntegerByField("count");
		
		if(result.containsKey(word)){
			//该单词已经存在：累加
			int total = result.get(word);
			result.put(word, total+count);
		}else{
			//该单词不存在
			result.put(word, count);
		}
		
		//输出
		System.out.println("输出的结果是：" + result);
		//发送下一个组件
		this.collector.emit(new Values(word,result.get(word)));
	}

	@Override
	public void prepare(Map arg0, TopologyContext arg1, OutputCollector collector) {
		// TODO Auto-generated method stub
		this.collector = collector;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		//用于申明输出的Schema
		declarer.declare(new Fields("word","total"));
	}

}














