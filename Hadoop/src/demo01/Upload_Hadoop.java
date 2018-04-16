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
	// 原生的写法
	@Test 
	public void test01() throws Exception {
		// 构造一个输入流(获取数据)
		InputStream in = new FileInputStream("F:\\bigdata\\hadoop-2.7.3.tar.gz");
		
		// 配偶NameNode（hadoop主节点）地址
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.8.14:9000");
		
		// 构建客户端
		FileSystem client = FileSystem.get(conf);
		
		// 创建输出流(上传数据的路径)
		OutputStream out = client.create(new Path("/test01/hadoop_01_2.7.3.tar.gz"));
		
		// 提高效率
		// 构造一个缓冲区
		byte[] buffer = new byte[1024];
		int len = 0;
		while((len = in.read(buffer)) > 0) {
			// 将读取到的数据写入
			out.write(buffer, 0, len);
		}
		// 使用缓冲io是要注意刷新
		out.flush();
		
		// 关闭
		out.close();
		in.close();
		client.close();
	}
	
	// Hadoop工具类
	@Test
	public void test02() throws Exception {
		// 构造输入流(获取数据)
		InputStream in = new FileInputStream("F:\\bigdata\\hadoop-2.7.3.tar.gz");
		
		// 配置连接
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.8.14:9000");
		
		// 建立客户端
		FileSystem client = FileSystem.get(conf);
		
		// 建立输出流(上传数据)
		OutputStream out = client.create(new Path("/test01/test.tar.gz"));
		
		// 工具类
		IOUtils.copyBytes(in, out, 1024);
	}
}
