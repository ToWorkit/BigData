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
		// 数据：7654,MARTIN,SALESMAN,7698,1981/9/28,1250,1400,30
		String data = v1.toString();
		
		// 分词
		String[] words = data.split(",");
		
		// 输出 sal 各个工资
		context.write(new Text("sal"), new IntWritable(Integer.parseInt(words[5])));
	}
}
