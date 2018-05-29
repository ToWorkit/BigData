package demo13;

/*		A	a-e-d-c
		B	b-a
		C	b-e-a
		D	c-b-a
		E	d-c
		F	d-e
		*/

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
// k4 v4 k5 v5
public class classificationMapper_02 extends Mapper<LongWritable, Text, Text, Text>{
	@Override
	protected void map(LongWritable k1, Text v1, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		// 数据
		String data = v1.toString();
		// 分词处理
		String upper = data.split("\t")[0];
		String[] lower = data.split("\t")[1].split("-");
		for (String str: lower) {
			context.write(new Text(str), new Text(upper));
		}
	}
}

