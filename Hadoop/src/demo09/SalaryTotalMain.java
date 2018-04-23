package demo09;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class SalaryTotalMain {
	public static void main(String[] args) throws Exception {
		// 创建一个job
		Job job = Job.getInstance(new Configuration());
		job.setJarByClass(SalaryTotalMain.class);
		
		// 指定Mapper和Mapper的输出格式
		job.setMapperClass(SalaryTotalMapper.class);
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		// 指定Reducer和Reducer的输出格式
		job.setReducerClass(SalaryTotalReducer.class);
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(IntWritable.class);
		
		// 指定job的输入和输出的路径
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		// 执行并打印日志
		job.waitForCompletion(true);
	}
}
