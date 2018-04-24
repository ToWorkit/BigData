package demo13;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class classificationMain_02 {
	public static void main(String[] args) throws Exception {
		// ָ��Job��mainִ�к���
		Job job = Job.getInstance(new Configuration());
		job.setJarByClass(classificationMain_02.class);
		
		// ָ��Mapper�Լ��������
		job.setMapperClass(classificationMapper_02.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
		// ָ��Reducer�Լ��������
		job.setReducerClass(classificationReducer_02.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		// ������������·��
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		// ִ�в���ӡ��־
		job.waitForCompletion(true);
	}
}