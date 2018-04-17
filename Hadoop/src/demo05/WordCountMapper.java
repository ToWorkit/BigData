package demo05;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
// 泛型 
// 参数 -> HDFS 的数据类型 -> k1(偏移量), v1, k2(默认根据k2排序), v2
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	@Override
	protected void map(LongWritable key1, Text value1, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		/*
		 * context -> 表示 Mapper 的上下文
		 * 上文 -> HDFS
		 * 下文 -> Mapper
		 */
		// 数据： I Love You
		String data = value1.toString();
		// 分词
		String[] words = data.split(" ");
		
		// 输出 k2 v2
		// 单词，标记为 1
		for(String w: words) {
			context.write(new Text(w), new IntWritable(1));
		}
	}
}
