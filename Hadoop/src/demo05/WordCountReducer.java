package demo05;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
// k3, v3 => Mapper�����(k2, v2)
// k4, v4 => Reducer�Ľ��
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	@Override
	protected void reduce(Text k3, Iterable<IntWritable> v3,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		/*
		 * context �� Reduce ��������
		 * ���� -> Mapper
		 * ���� -> HDFS
		 */
		// �Խ��չ���������(v2)v3�������
		int total = 0;
		for (IntWritable v: v3) {
			total += v.get();
		}
		
		// ��� ����(k4)��Ƶ��(v4)
		// k3 ���� Mapper ����� k2, �Ѿ���HDFS�����ݸ�ʽ���Բ���Ҫת��
		context.write(k3, new IntWritable(total));
	}
}
