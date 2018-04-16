package demo01;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

/*
 * ��Hadoop�ϴ����ļ�
 */

public class Mkdir_Hadoop {
	@Test
	public void test01() throws Exception {
		// ʹ��root
		System.setProperty("HADOOP_USER_NAME", "root");
		
		// ����NameNode���ڵ�ĵ�ַ
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.8.14:9000");
		
		// ��ȡһ��HDFS�Ŀͻ���
		FileSystem client = FileSystem.get(conf);
		
		// ����Ŀ¼������Ϊ�ļ�����
		client.mkdirs(new Path("/test01"));
		
		// �ر�����
		client.close();
	}
}
