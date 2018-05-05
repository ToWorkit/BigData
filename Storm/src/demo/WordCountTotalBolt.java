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
 * �ڶ���bolt��������ʵļ���
 */
public class WordCountTotalBolt extends BaseRichBolt{
	//�����
	private OutputCollector collector;
	
	//����һ��Map���ϣ����ڱ������Ľ��
	private Map<String, Integer> result = new HashMap<>();
	
	@Override
	public void execute(Tuple tuple) {
		//��δ�����һ���������������
		//��ȡ���ݣ� ���ʡ�Ƶ�ʣ�1
		String word = tuple.getStringByField("word");
		int count = tuple.getIntegerByField("count");
		
		if(result.containsKey(word)){
			//�õ����Ѿ����ڣ��ۼ�
			int total = result.get(word);
			result.put(word, total+count);
		}else{
			//�õ��ʲ�����
			result.put(word, count);
		}
		
		//���
		System.out.println("����Ľ���ǣ�" + result);
		//������һ�����
		this.collector.emit(new Values(word,result.get(word)));
	}

	@Override
	public void prepare(Map arg0, TopologyContext arg1, OutputCollector collector) {
		// TODO Auto-generated method stub
		this.collector = collector;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		//�������������Schema
		declarer.declare(new Fields("word","total"));
	}

}














