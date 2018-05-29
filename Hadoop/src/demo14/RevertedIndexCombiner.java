package demo14;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class RevertedIndexCombiner extends Reducer<Text, Text, Text, Text>{
	@Override
	protected void reduce(Text k21, Iterable<Text> v21, Reducer<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {	
		// 求和，对同一个文件中的每个单词的频率求和
		int total = 0;
		for(Text v: v21) {
			total += Integer.parseInt(v.toString());
		}
		// 输出
		// 将单词和文件名分离  k21 => love:data01.txt
		String data = k21.toString();
		// 冒号
		int index = data.indexOf(":");
		
		// 单词
		String word = data.substring(0, index);
		// 文件名
		String fileName = data.substring(index + 1);
		
		// 输出
		context.write(new Text(word), new Text(fileName + ":" + total));
	}
}
