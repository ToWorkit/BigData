package demo07;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class NumberSortMain {
	public static void main(String[] args) throws Exception {
		// 创建一个job和任务入口(指定main函数)
		Job job = Job.getInstance(new Configuration());
		job.setJarByClass(NumberSortMain.class);
		
		// 指定job的Mapper和输出的类型 <k2 v2>
		job.setMapperClass(NumberSortMapper.class);
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(NullWritable.class);
		
		// 指定自己的比较器
		job.setSortComparatorClass(MyNumberComparator.class);
		
		// 指定job的输入和输出
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		// 执行job查看日志
		job.waitForCompletion(true);
	}
}
