1��kafka �汾 2.11.x
	����
		/root/training/kafka_2.11-0.10.2.1/bin/kafka-server-start.sh -daemon /root/training/kafka_2.11-0.10.2.1/config/server.properties
	��������
		/root/training/kafka_2.11-0.10.2.1/bin/kafka-topics.sh --create --zookeeper bigdata111:2181,bigdata112:2181,bigdata113:2181 --replication-factor 1 --partitions 3 --topic calllog
		����
			replication-factor �����
			partitions ����
			kafkaΪʲôҪ�з���
				https://www.zhihu.com/question/28925721/answer/43648910
	�鿴����
		/root/training/kafka_2.11-0.10.2.1/bin/kafka-topics.sh --zookeeper bigdata111:2181,bigdata112:2181,bigdata113:2181 --list
	��֤�����Ƿ���������
		/root/training/kafka_2.11-0.10.2.1/bin/kafka-topics.sh --describe --zookeeper bigdata111:2181,bigdata112:2181,bigdata113:2181 --topic calllog
	����flume��������֮������kafka��������
		/root/training/kafka_2.11-0.10.2.1/bin/kafka-console-consumer.sh --bootstrap-server bigdata111:9092,bigdata112:9092,bigdata113:9092 --topic calllog --from-beginning

2��zookpeer
	����
		zkServer.sh start
	�鿴״̬
		zkServer.sh status 
		
3��flume
	bigdata111 /calllog/flume_job_calllog2kafka.conf
	�������ݺ�����flume
		bin/flume-ng agent --conf conf/ --name a1 --conf-file /root/calllog/flume_job_calllog2kafka.conf
	
4��jar����������
	java -cp ct_producer-1.0-SNAPSHOT.jar producer.ProductLog /root/tmp/calllog/calllog.csv
	
	
	
����
	resources
		hbase_consumer.properties -> ����Ҫ������ӳ�䵽���ļ���