package demo16;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class SalaryTotalReducer extends Reducer<IntWritable, Employee, IntWritable, IntWritable>{
	@Override
	protected void reduce(IntWritable k3, Iterable<Employee> v3,
			Reducer<IntWritable, Employee, IntWritable, IntWritable>.Context context)
			throws IOException, InterruptedException {
		// 取出 v3 中的每个员工，进行工资求和
		int total = 0;
		for(Employee e: v3) {
			total += e.getSal();
		}
		
		// 输出
		context.write(k3, new IntWritable(total));
	}
}
