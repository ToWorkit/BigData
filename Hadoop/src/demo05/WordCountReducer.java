package demo05;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
// k3, v3 => Mapper的输出(k2, v2)
// k4, v4 => Reducer的结果
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	@Override
	protected void reduce(Text k3, Iterable<IntWritable> v3,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		/*
		 * context 是 Reduce 的上下文
		 * 上文 -> Mapper
		 * 下文 -> HDFS
		 */
		// 对接收过来的数据(v2)v3进行求和
		int total = 0;
		for (IntWritable v: v3) {
			total += v.get();
		}
		
		// 输出 单词(k4)，频率(v4)
		// k3 就是 Mapper 传入的 k2, 已经是HDFS的数据格式所以不需要转换
		context.write(k3, new IntWritable(total));
	}
}
