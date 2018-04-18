package demo07;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
//���� -> HDFS ���������� -> k1(ƫ����), v1, k2 нˮ (Ĭ�ϸ���k2����), v2 ��ֵ
public class NumberSortMapper extends Mapper<LongWritable, Text, IntWritable, NullWritable> {
	@Override
	protected void map(LongWritable key1, Text value1,
			Mapper<LongWritable, Text, IntWritable, NullWritable>.Context context)
			throws IOException, InterruptedException {
		// ���ݣ�7654,MARTIN,SALESMAN,7698,1981/9/28,1250,1400,30
		String data = value1.toString();
		
		// �ִ�
		String[] words = data.split(",");
		
		// ��� k2 нˮ v2 ��ֵ
		context.write(new IntWritable(Integer.parseInt(words[5])), NullWritable.get());
	}
}
