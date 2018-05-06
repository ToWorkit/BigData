package demo06;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class SalaryTotalMain {
	public static void main(String[] args) throws Exception {
		// ����һ��job
		Job job = Job.getInstance(new Configuration());
		// ִ�е�main����
		job.setJarByClass(SalaryTotalMain.class);
		
		// ָ��job��Mapper����������� k2 v2
		job.setMapperClass(SalaryTotalMapper.class);
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(Employee.class);
		
		// ָ��job��Reducer����������� k4 v4
		job.setReducerClass(SalaryTotalReducer.class);
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(IntWritable.class);
		
		// ָ��job����������·��
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		// ִ������
		job.waitForCompletion(true);
	}
}
