package com.ml.wordCount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 输入
 *  偏移量 k1
 *  文本   v1
 * 输出
 *  文本   k2
 *  计数   v2
 */
public class myMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 接收数据
        String string = value.toString();

        // 分词
        String[] words = string.split(" ");

        // 输出
        for (String w: words) {
            // 拆分后的每个单词计数为 1
            context.write(new Text(w), new IntWritable(1));
        }
    }
}
