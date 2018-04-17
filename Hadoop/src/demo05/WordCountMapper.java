package demo05;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
// ���� 
// ���� -> HDFS ���������� -> k1(ƫ����), v1, k2(Ĭ�ϸ���k2����), v2
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	@Override
	protected void map(LongWritable key1, Text value1, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		/*
		 * context -> ��ʾ Mapper ��������
		 * ���� -> HDFS
		 * ���� -> Mapper
		 */
		// ���ݣ� I Love You
		String data = value1.toString();
		// �ִ�
		String[] words = data.split(" ");
		
		// ��� k2 v2
		// ���ʣ����Ϊ 1
		for(String w: words) {
			context.write(new Text(w), new IntWritable(1));
		}
	}
}
