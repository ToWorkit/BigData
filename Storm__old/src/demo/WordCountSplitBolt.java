package demo;

import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

/*
 * 第一个Bolt组件：用于拆分单词
 */
public class WordCountSplitBolt extends BaseRichBolt{

	//输出流
	private OutputCollector collector;
	
	@Override
	public void execute(Tuple tuple) {
		//如何处理上一个组件发来的数据
		//获取数据
		String line = tuple.getStringByField("sentence");
		
		//分词
		String[] words = line.split(" ");
		
		//输出
		for(String w:words){
			this.collector.emit(new Values(w,1));
		}
	}

	/**
	 * OutputCollector collector: bolt组件的输出流
	 */
	@Override
	public void prepare(Map arg0, TopologyContext arg1, OutputCollector collector) {
		//初始化bolt组件
		this.collector = collector;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		//用于声明输出的Schema
		declarer.declare(new Fields("word","count"));
	}

}















