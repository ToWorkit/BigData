1、kafka 版本 2.11.x
	启动
		/root/training/kafka_2.11-0.10.2.1/bin/kafka-server-start.sh -daemon /root/training/kafka_2.11-0.10.2.1/config/server.properties
	创建主题
		/root/training/kafka_2.11-0.10.2.1/bin/kafka-topics.sh --create --zookeeper bigdata111:2181,bigdata112:2181,bigdata113:2181 --replication-factor 1 --partitions 3 --topic calllog
		释义
			replication-factor 冗余度
			partitions 分区
			kafka为什么要有分区
				https://www.zhihu.com/question/28925721/answer/43648910
	查看主题
		/root/training/kafka_2.11-0.10.2.1/bin/kafka-topics.sh --zookeeper bigdata111:2181,bigdata112:2181,bigdata113:2181 --list
	验证主题是否正常配置
		/root/training/kafka_2.11-0.10.2.1/bin/kafka-topics.sh --describe --zookeeper bigdata111:2181,bigdata112:2181,bigdata113:2181 --topic calllog
	启动flume监听数据之后启动kafka的消费者
		/root/training/kafka_2.11-0.10.2.1/bin/kafka-console-consumer.sh --bootstrap-server bigdata111:9092,bigdata112:9092,bigdata113:9092 --topic calllog --from-beginning

2、zookpeer
	启动
		zkServer.sh start
	查看状态
		zkServer.sh status 
		
3、flume
	bigdata111 /calllog/flume_job_calllog2kafka.conf
	生产数据后启动flume
		bin/flume-ng agent --conf conf/ --name a1 --conf-file /root/calllog/flume_job_calllog2kafka.conf
	
4、jar包生产数据
	java -cp ct_producer-1.0-SNAPSHOT.jar producer.ProductLog /root/tmp/calllog/calllog.csv
	
	
	
开发
	resources
		hbase_consumer.properties -> 将需要的属性映射到该文件中