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
		// ����һ��job���������
		Job job = Job.getInstance(new Configuration());
		// main�������ڵ�class
		job.setJarByClass(WordCountMain.class);
		
		// ָ��job��Mapper�����������<k2 v2>
		job.setMapperClass(WordCountMapper.class);
		// k2 ����������
		job.setMapOutputKeyClass(Text.class);
		// v2 ����������
		job.setMapOutputValueClass(IntWritable.class);
		
		// ָ��job��Reducer�����������<k4 v4>
		job.setReducerClass(WordCountReducer.class);
		// k4 ����������
		job.setOutputKeyClass(Text.class);
		// v4 ����������
		job.setOutputValueClass(IntWritable.class);
		
		// ָ��job������·��(jar����)�����·��(jar����)
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		// ִ�� job ����־�ɹ��鿴
		job.waitForCompletion(true);
	}
}
