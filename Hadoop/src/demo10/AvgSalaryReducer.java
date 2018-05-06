package demo10;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

// ƽ������ double����
public class AvgSalaryReducer extends Reducer<Text, IntWritable, Text, DoubleWritable>{
	@Override
	protected void reduce(Text k3, Iterable<IntWritable> v3,
			Reducer<Text, IntWritable, Text, DoubleWritable>.Context context) throws IOException, InterruptedException {
		// ��ƽ��ֵ
		int total = 0;
		int count = 0;
		for (IntWritable v: v3) {
			total += v.get(); // �����ܶ�
			count ++; // ����
		}
		// ���
		context.write(new Text("Avarge Salary is: "), new DoubleWritable(total / count));
	}
}
