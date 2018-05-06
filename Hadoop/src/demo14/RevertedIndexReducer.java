package demo14;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class RevertedIndexReducer extends Reducer<Text, Text, Text, Text>{
	@Override
	protected void reduce(Text k3, Iterable<Text> v3, Reducer<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		// 对combiner的输出的value
		String str = "";
		for (Text t: v3) {
			str = "(" + t.toString() + ")" + str;
		}
		context.write(k3, new Text(str));
	}
}
