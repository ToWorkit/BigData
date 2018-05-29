package demo11;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class EqualJoinMain {
	public static void main(String[] args) throws Exception {
		// 创建job并指定main执行函数
		Job job = Job.getInstance(new Configuration());
		job.setJarByClass(EqualJoinMain.class);
		
		// 指定Mapper以及输出类型
		job.setMapperClass(EqualJoinMapper.class);
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(Text.class);
		
		// 指定Reducer以及输出类型
		job.setReducerClass(EqualJoinReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		// 指定输入输出路径
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		// 执行job
		job.waitForCompletion(true);
	}
}
