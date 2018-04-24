package demo12_demo13_面试题;

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
		String name = "";
		for(Text str: v3) {
			name += str.toString() + "-";
		}
		// 从 头开始截取到 "-" 出现的最后位置，含头不含尾
		name = name.substring(0, name.lastIndexOf("-"));
		context.write(new Text(k3), new Text(name));
		
/*		A	a-e-d-c
		B	b-a
		C	b-e-a
		D	c-b-a
		E	d-c
		F	d-e
		*/

	}
}
