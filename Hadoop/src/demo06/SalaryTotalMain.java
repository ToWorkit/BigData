package demo06;

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
		// 执行的main函数
		job.setJarByClass(SalaryTotalMain.class);
		
		// 指定job的Mapper和输出的类型 k2 v2
		job.setMapperClass(SalaryTotalMapper.class);
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(Employee.class);
		
		// 指定job的Reducer和输出的类型 k4 v4
		job.setReducerClass(SalaryTotalReducer.class);
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(IntWritable.class);
		
		// 指定job的输入和输出路径
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		// 执行任务
		job.waitForCompletion(true);
	}
}
