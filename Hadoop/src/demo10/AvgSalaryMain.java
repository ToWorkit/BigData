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
		// ����job��ָ��mainִ�к���
		Job job = Job.getInstance(new Configuration());
		job.setJarByClass(AvgSalaryMain.class);
		
		// ָ�� Mapper�Լ�Mapper�������ʽ
		job.setMapperClass(AvgSalaryMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		// avg_02 ���combiner
		job.setCombinerClass(AvgSalaryReducer.class);
		// combiner֮��Reducer�������ʽ�ͻ������ERROR
		
		// ָ��Reducer�Լ�Reducer�������ʽ
		job.setReducerClass(AvgSalaryReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(DoubleWritable.class);
		
		// ָ��job����������·��
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		// ִ�����񲢴�ӡ��־
		job.waitForCompletion(true);
	}
}
