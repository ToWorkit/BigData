package demo15;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SelfJoinReducer extends Reducer<IntWritable, Text, Text, Text>{
	@Override
	protected void reduce(IntWritable k3, Iterable<Text> v3, Reducer<IntWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {	
		// 定义变量来保存老板的姓名，员工的姓名
		String bossName = "";
		String empNameList = "";
		
		for (Text t:v3) {
			String name = t.toString();
			
			// 判断是否存在 *
			int index = name.indexOf("*");
			if (index >= 0) {
				// 表示老板的名字, 去掉 *
				bossName = name.substring(1);
			} else {
				// 表示员工的姓名
				empNameList = name + ";" + empNameList;
			}
		}
		// 输出
		// 有老板和员工的时候才输出
		if(bossName.length() > 0 && empNameList.length() > 0) {
			context.write(new Text(bossName), new Text(empNameList));
		}
	}
}
