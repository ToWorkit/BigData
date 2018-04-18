package demo07;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class NumberSortMain {
	public static void main(String[] args) throws Exception {
		// ����һ��job���������(ָ��main����)
		Job job = Job.getInstance(new Configuration());
		job.setJarByClass(NumberSortMain.class);
		
		// ָ��job��Mapper����������� <k2 v2>
		job.setMapperClass(NumberSortMapper.class);
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(NullWritable.class);
		
		// ָ���Լ��ıȽ���
		job.setSortComparatorClass(MyNumberComparator.class);
		
		// ָ��job����������
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		// ִ��job�鿴��־
		job.waitForCompletion(true);
	}
}
