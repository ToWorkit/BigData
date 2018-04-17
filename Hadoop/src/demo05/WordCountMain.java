package demo05;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.sun.jersey.core.impl.provider.entity.XMLJAXBElementProvider.Text;

public class WordCountMain {
	public static void main(String[] args) throws Exception {
		// 创建一个job和任务入口
		Job job = Job.getInstance(new Configuration());
		// main方法所在的class
		job.setJarByClass(WordCountMain.class);
		
		// 指定job的Mapper和输出的类型<k2 v2>
		job.setMapperClass(WordCountMapper.class);
		// k2 的数据类型
		job.setMapOutputKeyClass(Text.class);
		// v2 的数据类型
		job.setMapOutputValueClass(IntWritable.class);
		
		// 指定job的Reducer和输出的类型<k4 v4>
		job.setReducerClass(WordCountReducer.class);
		// k4 的数据类型
		job.setOutputKeyClass(Text.class);
		// v4 的数据类型
		job.setOutputValueClass(IntWritable.class);
		
		// 指定job的输入路径(jar参数)和输出路径(jar参数)
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		// 执行 job 有日志可供查看
		job.waitForCompletion(true);
	}
}
