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
		// ����job��ָ��mainִ�к���
		Job job = Job.getInstance(new Configuration());
		job.setJarByClass(EqualJoinMain.class);
		
		// ָ��Mapper�Լ��������
		job.setMapperClass(EqualJoinMapper.class);
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(Text.class);
		
		// ָ��Reducer�Լ��������
		job.setReducerClass(EqualJoinReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		// ָ���������·��
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		// ִ��job
		job.waitForCompletion(true);
	}
}
