package demo01;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class mkdir_test01 {
	public static void main(String[] args) throws IOException {
		// hadoop�����ý�Ȩ�޼��رգ�false���Ϳ��Բ���Ҫָ��root��
		System.setProperty("HADOOP_USER_NAME", "root");
		
		// ������Ҫ��������Ϣ
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.8.14:9000");
		
		// ���Ӳ������ͻ���
		FileSystem client = FileSystem.get(conf);
		// ��������������ΪPath����
		client.mkdirs(new Path("/input"));
		
		// ��ɺ����ӹرյ�
		client.close();
	}
}
