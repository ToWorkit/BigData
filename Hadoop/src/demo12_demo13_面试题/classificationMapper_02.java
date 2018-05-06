package demo12_demo13_面试题;

/*		A	a-e-d-c
		B	b-a
		C	b-e-a
		D	c-b-a
		E	d-c
		F	d-e
		*/

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
// k4 v4 k5 v5
public class classificationMapper_02 extends Mapper<Text, Text, Text, Text>{
	@Override
	protected void map(Text k4, Text v4, Mapper<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		// 数据
		String data = v4.toString();
		// 分词处理
		String upper = data.split("\t")[0];
		String[] lower = data.split("\t")[1].split("-");
		for (String str: lower) {
			context.write(new Text(str), new Text(upper));
		}
	}
}
