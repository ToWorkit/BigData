package demo11;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class EqualJoinReducer extends Reducer<IntWritable, Text, Text, Text>{
	@Override
	protected void reduce(IntWritable k3, Iterable<Text> v3, Reducer<IntWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		// 解析部门名称和员工姓名
		String dname = "";
		String empListName = "";
		
		for (Text str: v3) {
			String name = str.toString();
			// 判断是否存储了 * 号
			int index = name.indexOf("*");
			
			if (index >= 0) {
				// 是部门名称，去掉第一个*号
				dname = name.substring(1);
			} else {
				// 是员工的姓名
				empListName = name + ";" + empListName;
			}
		}
		// 输出
		context.write(new Text(dname), new Text(empListName));
	}
}
