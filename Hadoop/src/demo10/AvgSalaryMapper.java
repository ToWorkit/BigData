package demo10;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AvgSalaryMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	@Override
	protected void map(LongWritable k1, Text v1, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		// ���ݣ�7654,MARTIN,SALESMAN,7698,1981/9/28,1250,1400,30
		String data = v1.toString();
		
		// �ִ�
		String[] words = data.split(",");
		
		// ��� sal ��������
		context.write(new Text("sal"), new IntWritable(Integer.parseInt(words[5])));
	}
}
