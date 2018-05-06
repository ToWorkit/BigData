package demo13;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
// k6 v6 k7 v7
public class classificationReducer_02 extends Reducer<Text, Text, Text, Text>{
	@Override
	protected void reduce(Text k3, Iterable<Text> v3, Reducer<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		String name = "";
		for(Text str: v3) {
			name += str.toString() + "-";
		}
		// 从 头开始截取到 "-" 出现的最后位置，含头不含尾
		name = name.substring(0, name.lastIndexOf("-"));
		context.write(new Text(k3), new Text(name));
	}
}