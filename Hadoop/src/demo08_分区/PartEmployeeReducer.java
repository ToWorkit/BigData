package demo08_分区;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
// k3 v3 k4 v4
public class PartEmployeeReducer extends Reducer<IntWritable, Employee_08, IntWritable, Text>{
	@Override
	protected void reduce(IntWritable k3, Iterable<Employee_08> v3,
			Reducer<IntWritable, Employee_08, IntWritable, Text>.Context context)
			throws IOException, InterruptedException {
		/*
		 * k3 部门号
		 * v3 部门的员工
		 */
		for (Employee_08 e: v3) {
			context.write(k3, new Text(e.toString()));
		}
	}
}
