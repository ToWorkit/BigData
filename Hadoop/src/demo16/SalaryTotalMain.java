package demo16;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class SalaryTotalMain {
	public static void main(String[] args) throws Exception {
		// 创建一个job 并指定 main函数
		Job job = Job.getInstance(new Configuration());
		job.setJarByClass(SalaryTotalMain.class);
		
		// 指定job的Mapper以及输出的类型
		job.setMapperClass(SalaryTotalMapper.class);
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(Employee.class);
		
		// 指定job的Reducer以及输出的类型
		job.setReducerClass(SalaryTotalReducer.class);
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(IntWritable.class);
		
		// 指定job的输入和输出的路径
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		// 执行job
		job.waitForCompletion(true);
	}
}
