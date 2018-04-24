package demo12;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class classificationMapper extends Mapper<LongWritable, Text, Text, Text>{
	@Override
	protected void map(LongWritable k1, Text v1, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
/*		a:A,B,C,D
		b:B,C,D
		c:A,D,E
		d:E,F,A
		e:A,C,F*/
		// 找重 a:A,B,C,D 处理数据
		String data = v1.toString();
		
		// 分词
		// 小写
		String lower = data.split(":")[0];
		// 大写
		String[] upper = data.split(":")[1].split(",");
		for (String str: upper) {
			context.write(new Text(str), new Text(lower));
		}
		
	}
}
