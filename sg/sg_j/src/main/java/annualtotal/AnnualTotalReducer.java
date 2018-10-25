package annualtotal;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

// 输出 年份(k4) 订单数 + 销售总额(v4)
public class AnnualTotalReducer extends Reducer<IntWritable, DoubleWritable, IntWritable, Text> {
    @Override
    protected void reduce(IntWritable key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException {
        // 订单数和销售总额
        int totalCount = 0;
        double totalMoney = 0;
        for (DoubleWritable value : values) {
            totalCount ++;
            totalMoney += value.get();
        }

        // 输出
        context.write(key, new Text(totalCount + "\t" +totalMoney));
    }
}
