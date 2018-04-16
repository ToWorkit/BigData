package demo01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

public class Upload_Hadoop {
	// ԭ����д��
	@Test 
	public void test01() throws Exception {
		// ����һ��������(��ȡ����)
		InputStream in = new FileInputStream("F:\\bigdata\\hadoop-2.7.3.tar.gz");
		
		// ��żNameNode��hadoop���ڵ㣩��ַ
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.8.14:9000");
		
		// �����ͻ���
		FileSystem client = FileSystem.get(conf);
		
		// ���������(�ϴ����ݵ�·��)
		OutputStream out = client.create(new Path("/test01/hadoop_01_2.7.3.tar.gz"));
		
		// ���Ч��
		// ����һ��������
		byte[] buffer = new byte[1024];
		int len = 0;
		while((len = in.read(buffer)) > 0) {
			// ����ȡ��������д��
			out.write(buffer, 0, len);
		}
		// ʹ�û���io��Ҫע��ˢ��
		out.flush();
		
		// �ر�
		out.close();
		in.close();
		client.close();
	}
	
	// Hadoop������
	@Test
	public void test02() throws Exception {
		// ����������(��ȡ����)
		InputStream in = new FileInputStream("F:\\bigdata\\hadoop-2.7.3.tar.gz");
		
		// ��������
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.8.14:9000");
		
		// �����ͻ���
		FileSystem client = FileSystem.get(conf);
		
		// ���������(�ϴ�����)
		OutputStream out = client.create(new Path("/test01/test.tar.gz"));
		
		// ������
		IOUtils.copyBytes(in, out, 1024);
	}
}
