package demo06;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;

public class SalaryTotalMain {
	public static void main(String[] args) throws Exception {
		// ����һ��job
		Job job = Job.getInstance(new Configuration());
		// ִ�е�main����
		job.setJarByClass(SalaryTotalMain.class);
	}
}
