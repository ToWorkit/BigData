package demo09;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SalaryTotalMapper extends Mapper<LongWritable, Text, IntWritable, IntWritable>{
	@Override
	protected void map(LongWritable k1, Text v1,
			Mapper<LongWritable, Text, IntWritable, IntWritable>.Context context)
			throws IOException, InterruptedException {
		// Ҫ��������ݸ�ʽ
		//���ݣ�7654,MARTIN,SALESMAN,7698,1981/9/28,1250,1400,30
		String data = v1.toString();
		// �ִ�
		String[] words = data.split(",");
		// ��� ���źţ�нˮ
		context.write(new IntWritable(Integer.parseInt(words[7])), new IntWritable(Integer.parseInt(words[5])));
	}
}
