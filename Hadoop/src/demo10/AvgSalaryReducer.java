package demo10;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

// 平均数， double类型
public class AvgSalaryReducer extends Reducer<Text, IntWritable, Text, DoubleWritable>{
	@Override
	protected void reduce(Text k3, Iterable<IntWritable> v3,
			Reducer<Text, IntWritable, Text, DoubleWritable>.Context context) throws IOException, InterruptedException {
		// 求平均值
		int total = 0;
		int count = 0;
		for (IntWritable v: v3) {
			total += v.get(); // 工资总额
			count ++; // 人数
		}
		// 输出
		context.write(new Text("Avarge Salary is: "), new DoubleWritable(total / count));
	}
}
