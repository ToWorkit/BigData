package annualtotal;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

// 输出 年份(k2) 金额(v2)
public class AnnualTotalMapper extends Mapper<LongWritable, Text, IntWritable, DoubleWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 数据：13,1660,1998-01-10,3,999,1,1232.16
        // 转为字符串
        String string = value.toString();
        // 分词
        String[] words = string.split(",");
        // 输出
        context.write(new IntWritable(Integer.parseInt(words[2].substring(0, 4))), new DoubleWritable(Double.parseDouble(words[6])));
    }
}
