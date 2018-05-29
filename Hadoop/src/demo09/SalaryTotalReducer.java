package demo09;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class SalaryTotalReducer extends Reducer<IntWritable, IntWritable, IntWritable, IntWritable>{
	@Override
	protected void reduce(IntWritable k3, Iterable<IntWritable> v3,
			Reducer<IntWritable, IntWritable, IntWritable, IntWritable>.Context context)
			throws IOException, InterruptedException {
		// �� v3 ��ͣ��õ����Ź��ʵ��ܶ�
		int total = 0;
		for (IntWritable v: v3) {
			total += v.get();
		}
		// ��������ź� �ܶ�
		context.write(k3, new IntWritable(total));
	}
}
