package demo14;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class RevertedIndexMain {
	public static void main(String[] args) throws Exception {
		// 创建job并指定main函数
		Job job = Job.getInstance(new Configuration());
		job.setJarByClass(RevertedIndexMain.class);
		
		// 指定job的mapper以及输出类型
		job.setMapperClass(RevertedIndexMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
		// 引入combiner
		job.setCombinerClass(RevertedIndexCombiner.class);
		
		// 指定job的reducer以及输出的类型
		job.setReducerClass(RevertedIndexReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		// 指定job的输入和输出路径
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.waitForCompletion(true);
	}
}
