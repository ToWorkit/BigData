package demo14;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class RevertedIndexMain {
	public static void main(String[] args) throws Exception {
		// ����job��ָ��main����
		Job job = Job.getInstance(new Configuration());
		job.setJarByClass(RevertedIndexMain.class);
		
		// ָ��job��mapper�Լ��������
		job.setMapperClass(RevertedIndexMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
		// ����combiner
		job.setCombinerClass(RevertedIndexCombiner.class);
		
		// ָ��job��reducer�Լ����������
		job.setReducerClass(RevertedIndexReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		// ָ��job����������·��
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.waitForCompletion(true);
	}
}
