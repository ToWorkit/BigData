package demo08;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;

public class PartEmployeeMain {
	public static void main(String[] args) throws Exception {
		// ����һ��job
		Job job = Job.getInstance(new Configuration());
		job.setJarByClass(PartEmployeeMain.class);
		
		// ָ��job��Mapper����������� k2 v2
		job.setMapperClass(PartEmployeeMapper.class);
		job.setMapOutputKeyClass(IntWritable.class); // ���ź�
		job.setMapOutputValueClass(Employee.class); // Ա������
		
		// ָ��job�ķ�������
		job.setPartitionerClass(MyEmployeeParitioner.class);
		// ���Σ�ָ�������ĸ���
		job.setNumReduceTasks(3);
		
		// ָ��job��Reducer����������� k4 v4
	}
}
