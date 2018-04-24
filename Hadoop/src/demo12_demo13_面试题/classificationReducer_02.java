package demo12_demo13_√Ê ‘Ã‚;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
// k6 v6 k7 v7
public class classificationReducer_02 extends Reducer<Text, Text, Text, Text>{
	@Override
	protected void reduce(Text k6, Iterable<Text> v6, Reducer<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		String name = "";
		for (Text v: v6) {
			context.write(new Text(k6), new Text(v));
		}
	}
}
