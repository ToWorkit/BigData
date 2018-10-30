package demo;

import java.util.Map;
import java.util.Random;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;

/*
 * 作用：采集数据，并且送到下一个bolt组件
 * 模拟：产生一些数据
 */
public class WordCountSpout extends BaseRichSpout{

	//输出流
	private SpoutOutputCollector collector;
	
	//定义我们的数据
	private String[] data = {"I love Beijing","I love China","Beijing is the capital of China"};
	
	@Override
	public void nextTuple() {
		//每隔3秒采集一次数据
		Utils.sleep(3000);
		
		// 由Storm的框架进行调用，用于接收外部系统产生的数据
		//随机产生一个字符串，代表采集的数据
		int random = (new Random()).nextInt(3); //3以内的随机数
		
		//采集数据，然后发送给下一个组件
		System.out.println("采集的数据是：" + data[random]);
		this.collector.emit(new Values(data[random]));
	}

	/**
	 * SpoutOutputCollector collector: spout组件的输出流
	 */
	@Override
	public void open(Map arg0, TopologyContext arg1, SpoutOutputCollector collector) {
		// 是spout组件初始化的方法
		this.collector = collector;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		//用于申明输出的Schema
		declarer.declare(new Fields("sentence"));
	}

}