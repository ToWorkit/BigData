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
 * ���ã��ɼ����ݣ������͵���һ��bolt���
 * ģ�⣺����һЩ����
 */
public class WordCountSpout extends BaseRichSpout{

	//�����
	private SpoutOutputCollector collector;
	
	//�������ǵ�����
	private String[] data = {"I love Beijing","I love China","Beijing is the capital of China"};
	
	@Override
	public void nextTuple() {
		//ÿ��3��ɼ�һ������
		Utils.sleep(3000);
		
		// ��Storm�Ŀ�ܽ��е��ã����ڽ����ⲿϵͳ����������
		//�������һ���ַ���������ɼ�������
		int random = (new Random()).nextInt(3); //3���ڵ������
		
		//�ɼ����ݣ�Ȼ���͸���һ�����
		System.out.println("�ɼ��������ǣ�" + data[random]);
		this.collector.emit(new Values(data[random]));
	}

	/**
	 * SpoutOutputCollector collector: spout����������
	 */
	@Override
	public void open(Map arg0, TopologyContext arg1, SpoutOutputCollector collector) {
		// ��spout�����ʼ���ķ���
		this.collector = collector;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		//�������������Schema
		declarer.declare(new Fields("sentence"));
	}

}