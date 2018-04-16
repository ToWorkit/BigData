package demo01;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

public class MetaData_Hadoop {
	@Test
	public void test01() throws Exception {
		// �������ӵ�ַ
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.8.14:9000");
		
		// �����ͻ�������
		FileSystem client = FileSystem.get(conf);
		
		// ��ȡ��Ŀ¼�µ������ļ���Ϣ
		FileStatus[] filesStatus = client.listStatus(new Path("/test01"));
		
		// ����
		for (FileStatus f: filesStatus) {
			System.out.println(f.isDirectory() ? "Ŀ¼" : "�ļ�");
			System.out.println(f.getPath().getName());
			System.out.println(f.getBlockSize());
		}
		
		client.close();
	}
	
	@Test
	public void test02() throws Exception {
		// ����������Ϣ
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.8.14:9000");
		
		// �����ͻ�������
		FileSystem client = FileSystem.get(conf);
		
		// ��ȡ���ļ�����Ϣ
		FileStatus fs = client.getFileStatus(new Path("/test01/test.tar.gz"));
		
		// ��ȡ�ļ������ݿ���Ϣ
		BlockLocation[] location = client.getFileBlockLocations(fs, 0, fs.getLen());
		for (BlockLocation block: location) {
			System.out.println(block);
			System.out.println(Arrays.toString(block.getHosts()) + "\t" + Arrays.toString(block.getNames()));
		}
		
		// �ر�����
		client.close();
	}
}
