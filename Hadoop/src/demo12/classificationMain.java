package demo12;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class classificationMain {
	public static void main(String[] args) throws Exception {
		// 指定Job和main执行函数
		Job job = Job.getInstance(new Configuration());
		job.setJarByClass(classificationMain.class);
		
		// 指定Mapper以及输出类型
		job.setMapperClass(classificationMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
		// 指定Reducer以及输出类型
		job.setReducerClass(classificationReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		// 数据输入和输出路径
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		// 执行并打印日志
		job.waitForCompletion(true);
	}
}
