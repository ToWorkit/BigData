package demo07;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
//参数 -> HDFS 的数据类型 -> k1(偏移量), v1, k2 薪水 (默认根据k2排序), v2 空值
public class NumberSortMapper extends Mapper<LongWritable, Text, IntWritable, NullWritable> {
	@Override
	protected void map(LongWritable key1, Text value1,
			Mapper<LongWritable, Text, IntWritable, NullWritable>.Context context)
			throws IOException, InterruptedException {
		// 数据：7654,MARTIN,SALESMAN,7698,1981/9/28,1250,1400,30
		String data = value1.toString();
		
		// 分词
		String[] words = data.split(",");
		
		// 输出 k2 薪水 v2 空值
		context.write(new IntWritable(Integer.parseInt(words[5])), NullWritable.get());
	}
}
