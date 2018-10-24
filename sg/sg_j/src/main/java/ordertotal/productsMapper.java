package ordertotal;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class productsMapper extends Mapper<LongWritable, Text, IntWritable, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 转为字符串
        String string = value.toString();
        // 分词
        String[] words = string.split(",");

        // 得到输入的HDFS的路径 /input/sh/ ... 多个文件
        String path = ((FileSplit) context.getInputSplit()).getPath().getName();
        // 取文件名 sales
        String fileName = path.substring(path.lastIndexOf("/") + 1);

        // 输出
        if (fileName.equals("products")) {  // 商品信息
            // 商品ID 名称
            context.write(new IntWritable(Integer.parseInt(words[0])), new Text("name: " + words[1]));
        } else {    // 订单信息
            // ID   年份:金额
            context.write(new IntWritable(Integer.parseInt(words[0])), new Text(words[2] + ":" + words[6]));
        }
    }
}
