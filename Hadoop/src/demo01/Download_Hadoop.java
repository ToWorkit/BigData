package demo01;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

public class Download_Hadoop {
	@Test
	public void test01() throws Exception {
		// ָ���û�
		System.setProperty("HADOOP_USER_NAME", "root");
		
		// ����������Ϣ
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.8.14:9000");
		
		// �����ͻ�������
		FileSystem client = FileSystem.get(conf);
		
		// ������(��ȡ����) -> HDFS
		InputStream in = client.open(new Path("/test01/test.tar.gz"));
		
		// �����(д������) -> ����
		OutputStream out = new FileOutputStream("F:\\bigdata\\test.tar.gz");
		
		// ������
		IOUtils.copyBytes(in, out, 1024);
	}
}
