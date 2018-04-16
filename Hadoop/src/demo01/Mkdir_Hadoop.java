package demo01;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

/*
 * 在Hadoop上创建文件
 */

public class Mkdir_Hadoop {
	@Test
	public void test01() throws Exception {
		// 使用root
		System.setProperty("HADOOP_USER_NAME", "root");
		
		// 配置NameNode主节点的地址
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.8.14:9000");
		
		// 获取一个HDFS的客户端
		FileSystem client = FileSystem.get(conf);
		
		// 创建目录（参数为文件对象）
		client.mkdirs(new Path("/test01"));
		
		// 关闭连接
		client.close();
	}
}
