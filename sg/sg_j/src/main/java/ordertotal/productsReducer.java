package ordertotal;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashMap;

public class productsReducer extends Reducer<IntWritable, Text, Text, Text> {
    @Override
    protected void reduce(IntWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        // 商品名称
        String productName = "";
        // 定义一个HashMap保存每一年的订单金额    年份 金额
        HashMap<Integer, Double> result = new HashMap<>();

        for (Text value : values) {
            // 先转为String类型
            String s = value.toString();
            // 判断是否为商品表
            if (s.indexOf("name: ") >= 0) {
                productName = s.substring(s.indexOf("name: ") + 1);
            } else { // 订单表
                // words[2] + ":" + words[6]
                // 订单信息  年份:金额    1998-01-10:1232.16
                int year = Integer.parseInt(s.substring(0, 4));
                double amount = Double.parseDouble(s.substring(s.lastIndexOf(":") + 1));

                if (result.containsKey(year)) {
                    // 有年份订单则累计
                    result.put(year, result.get(year) + amount);
                } else { // 无则创建
                    result.put(year, amount);
                }
            }
        }

        // 输出
        context.write(new Text(productName), new Text(result.toString()));
    }
}
