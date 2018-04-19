package demo08;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;

public class PartEmployeeMain {
	public static void main(String[] args) throws Exception {
		// 创建一个job
		Job job = Job.getInstance(new Configuration());
		job.setJarByClass(PartEmployeeMain.class);
		
		// 指定job的Mapper和输出的类型 k2 v2
		job.setMapperClass(PartEmployeeMapper.class);
		job.setMapOutputKeyClass(IntWritable.class); // 部门号
		job.setMapOutputValueClass(Employee.class); // 员工对象
		
		// 指定job的分区规则
		job.setPartitionerClass(MyEmployeeParitioner.class);
		// 传参，指定分区的个数
		job.setNumReduceTasks(3);
		
		// 指定job的Reducer和输出的类型 k4 v4
	}
}
