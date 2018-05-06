package demo15;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SelfJoinMapper extends Mapper<LongWritable, Text, IntWritable, Text>{
	@Override
	protected void map(LongWritable k1, Text v1, Mapper<LongWritable, Text, IntWritable, Text>.Context context)
			throws IOException, InterruptedException {
		//数据：7654,MARTIN,SALESMAN,7698,1981/9/28,1250,1400,30
		String data = v1.toString();
		// 分词
		String[] words = data.split(",");
		
		// 输出
		// 1、作为老板表
		context.write(new IntWritable(Integer.parseInt(words[0])), new Text("*" + words[1]));
		// 2、作为员工表 输出老板号可能会产生Exception(老板自己没有老板号)
		try {
			context.write(new IntWritable(Integer.parseInt(words[3])), new Text(words[1]));
		} catch (Exception ex) {
			// 产生异常则表示是大老板
			context.write(new IntWritable(-1), new Text(words[1]));
		}
	}
}
