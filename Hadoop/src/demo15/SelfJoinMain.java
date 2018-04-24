package demo15;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.sun.jersey.core.impl.provider.entity.XMLJAXBElementProvider.Text;

public class SelfJoinMain {
	public static void main(String[] args) throws Exception {
		// ����Job��ָ��main����
		Job job = Job.getInstance(new Configuration());
		job.setJarByClass(SelfJoinMain.class);
		
		// ָ��Mapper���������
		job.setMapperClass(SelfJoinMapper.class);
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(Text.class);
		
		// ָ��Reducer���������
		job.setReducerClass(SelfJoinReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		// ָ��������������·��
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		// ִ��
		job.waitForCompletion(true);
	}
}
