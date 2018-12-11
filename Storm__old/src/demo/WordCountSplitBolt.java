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
 * ��һ��Bolt��������ڲ�ֵ���
 */
public class WordCountSplitBolt extends BaseRichBolt{

	//�����
	private OutputCollector collector;
	
	@Override
	public void execute(Tuple tuple) {
		//��δ�����һ���������������
		//��ȡ����
		String line = tuple.getStringByField("sentence");
		
		//�ִ�
		String[] words = line.split(" ");
		
		//���
		for(String w:words){
			this.collector.emit(new Values(w,1));
		}
	}

	/**
	 * OutputCollector collector: bolt����������
	 */
	@Override
	public void prepare(Map arg0, TopologyContext arg1, OutputCollector collector) {
		//��ʼ��bolt���
		this.collector = collector;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		//�������������Schema
		declarer.declare(new Fields("word","count"));
	}

}















