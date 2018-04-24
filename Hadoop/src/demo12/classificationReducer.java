package demo12;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class classificationReducer extends Reducer<Text, Text, Text, Text>{
	@Override
	protected void reduce(Text k3, Iterable<Text> v3, Reducer<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		/*		a:A,B,C,D
		b:B,C,D
		c:A,D,E
		d:E,F,A
		e:A,C,F*/
		// A (a, c, d, e)
		String name = " ";
		for(Text str: v3) {
			name += str.toString();
		}
		context.write(new Text(k3), new Text(name));
	}
}
