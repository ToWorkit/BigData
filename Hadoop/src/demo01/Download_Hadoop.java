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
		// 指定用户
		System.setProperty("HADOOP_USER_NAME", "root");
		
		// 配置连接信息
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.8.14:9000");
		
		// 建立客户端连接
		FileSystem client = FileSystem.get(conf);
		
		// 输入流(获取数据) -> HDFS
		InputStream in = client.open(new Path("/test01/test.tar.gz"));
		
		// 输出流(写入数据) -> 本地
		OutputStream out = new FileOutputStream("F:\\bigdata\\test.tar.gz");
		
		// 工具类
		IOUtils.copyBytes(in, out, 1024);
	}
}
