package demo08_分区;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class PartEmployeeMain {
	public static void main(String[] args) throws Exception {
		// 创建一个job
		Job job = Job.getInstance(new Configuration());
		job.setJarByClass(PartEmployeeMain.class);
		
		// 指定job的Mapper和输出的类型 k2 v2
		job.setMapperClass(PartEmployeeMapper.class);
		job.setMapOutputKeyClass(IntWritable.class); // 部门号
		job.setMapOutputValueClass(Employee_08.class); // 员工
		
		// 指定job的分区规则
		job.setPartitionerClass(MyEmployeeParitioner.class);
		// 传参，指定分区的个数
		job.setNumReduceTasks(3);
		
		// 指定job的Reducer和输出的类型 k4 v4
		job.setReducerClass(PartEmployeeReducer.class);
		job.setOutputKeyClass(IntWritable.class); // 部门号
		job.setOutputValueClass(Employee_08.class); // 员工
		
		// 指定job的输入和输出路径
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		// 执行并输出日志
		job.waitForCompletion(true);
	}
}
