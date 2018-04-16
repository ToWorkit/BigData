package demo01;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class mkdir_test01 {
	public static void main(String[] args) throws IOException {
		// hadoop上配置将权限检查关闭（false）就可以不需要指定root了
		System.setProperty("HADOOP_USER_NAME", "root");
		
		// 连接需要的配置信息
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.8.14:9000");
		
		// 连接并创建客户端
		FileSystem client = FileSystem.get(conf);
		// 创建操作，参数为Path对象
		client.mkdirs(new Path("/input"));
		
		// 完成后将连接关闭掉
		client.close();
	}
}
