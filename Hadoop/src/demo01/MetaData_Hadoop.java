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
		// 配置连接地址
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.8.14:9000");
		
		// 建立客户端连接
		FileSystem client = FileSystem.get(conf);
		
		// 获取该目录下的所有文件信息
		FileStatus[] filesStatus = client.listStatus(new Path("/test01"));
		
		// 遍历
		for (FileStatus f: filesStatus) {
			System.out.println(f.isDirectory() ? "目录" : "文件");
			System.out.println(f.getPath().getName());
			System.out.println(f.getBlockSize());
		}
		
		client.close();
	}
	
	@Test
	public void test02() throws Exception {
		// 配置连接信息
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.8.14:9000");
		
		// 创建客户端连接
		FileSystem client = FileSystem.get(conf);
		
		// 获取该文件的信息
		FileStatus fs = client.getFileStatus(new Path("/test01/test.tar.gz"));
		
		// 获取文件的数据块信息
		BlockLocation[] location = client.getFileBlockLocations(fs, 0, fs.getLen());
		for (BlockLocation block: location) {
			System.out.println(block);
			System.out.println(Arrays.toString(block.getHosts()) + "\t" + Arrays.toString(block.getNames()));
		}
		
		// 关闭连接
		client.close();
	}
}
