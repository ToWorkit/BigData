package demo06;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;

public class SalaryTotalMain {
	public static void main(String[] args) throws Exception {
		// 创建一个job
		Job job = Job.getInstance(new Configuration());
		// 执行的main函数
		job.setJarByClass(SalaryTotalMain.class);
	}
}
