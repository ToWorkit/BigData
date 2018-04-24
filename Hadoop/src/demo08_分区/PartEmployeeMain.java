package demo08_����;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class PartEmployeeMain {
	public static void main(String[] args) throws Exception {
		// ����һ��job
		Job job = Job.getInstance(new Configuration());
		job.setJarByClass(PartEmployeeMain.class);
		
		// ָ��job��Mapper����������� k2 v2
		job.setMapperClass(PartEmployeeMapper.class);
		job.setMapOutputKeyClass(IntWritable.class); // ���ź�
		job.setMapOutputValueClass(Employee_08.class); // Ա��
		
		// ָ��job�ķ�������
		job.setPartitionerClass(MyEmployeeParitioner.class);
		// ���Σ�ָ�������ĸ���
		job.setNumReduceTasks(3);
		
		// ָ��job��Reducer����������� k4 v4
		job.setReducerClass(PartEmployeeReducer.class);
		job.setOutputKeyClass(IntWritable.class); // ���ź�
		job.setOutputValueClass(Employee_08.class); // Ա��
		
		// ָ��job����������·��
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		// ִ�в������־
		job.waitForCompletion(true);
	}
}
