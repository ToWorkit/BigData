package demo;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.redis.bolt.RedisStoreBolt;
import org.apache.storm.redis.common.config.JedisPoolConfig;
import org.apache.storm.redis.common.mapper.RedisDataTypeDescription;
import org.apache.storm.redis.common.mapper.RedisStoreMapper;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.ITuple;

public class WordCountTopology {

	public static void main(String[] args) throws Exception {
		// ����һ������Topology = spout + bolt(s)
		
		TopologyBuilder builder = new TopologyBuilder();
		
		//��������ĵ�һ�������spout���
		builder.setSpout("mywordcount_spout", new WordCountSpout());
		//builder.setSpout("mywordcount_spout", createKafkaSpout());
		
		//��������ĵڶ��������bolt�������ֵ���
		builder.setBolt("mywordcount_split", new WordCountSplitBolt()).shuffleGrouping("mywordcount_spout");
		
		//��������ĵ����������bolt��������ʼ���
		builder.setBolt("mywordcount_total", new WordCountTotalBolt()).fieldsGrouping("mywordcount_split", new Fields("word"));
		
		//��������ĵ��ĸ������bolt����������д��Redis��
		//builder.setBolt("mywordcount_redis", createRedisBolt()).shuffleGrouping("mywordcount_total");
		
		//��������ĵ��ĸ������bolt����������д��HDFS��
		//builder.setBolt("mywordcount_hdfs", createHDFSBolt()).shuffleGrouping("mywordcount_total");

		//��������ĵ��ĸ������bolt����������д��HBase��
		// builder.setBolt("mywordcount_hdfs", new WordCountHBaseBolt()).shuffleGrouping("mywordcount_total");		
		
		//��������
		StormTopology topology = builder.createTopology();
		
		//���ò���
		Config conf = new Config();
		
		//�ύ����: ��ʽ1������ģʽ     ��ʽ2����Ⱥģʽ
		//��ʽ1������ģʽ 
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("mywordcount", conf, topology);
		
		// ��ʽ2����Ⱥģʽ: storm jar temp/storm.jar demo.WordCountTopology MyStormWordCount
		//StormSubmitter.submitTopology(args[0], conf, topology);
	}
}























