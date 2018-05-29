package demo10;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class AvgSalaryMain {
	public static void main(String[] args) throws Exception {
		// 创建job并指定main执行函数
		Job job = Job.getInstance(new Configuration());
		job.setJarByClass(AvgSalaryMain.class);
		
		// 指定 Mapper以及Mapper的输出格式
		job.setMapperClass(AvgSalaryMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		// avg_02 添加combiner
		job.setCombinerClass(AvgSalaryReducer.class);
		// combiner之后Reducer的输入格式就会出错，报ERROR
		
		// 指定Reducer以及Reducer的输出格式
		job.setReducerClass(AvgSalaryReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(DoubleWritable.class);
		
		// 指定job的输入和输出路径
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		// 执行任务并打印日志
		job.waitForCompletion(true);
	}
}
