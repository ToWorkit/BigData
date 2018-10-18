package com.ml.selfJoin;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class selfJMapper extends Mapper<LongWritable, Text, IntWritable, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 数据：7654,MARTIN,SALESMAN,7698,1981/9/28,1250,1400,30
        String string = value.toString();
        String[] words = string.split(",");

        // 输出
        /**
         * 作为老板表
         */
        context.write(new IntWritable(Integer.parseInt(words[0])), new Text("*" + words[1]));
        /**
         * 作为员工表
         */
        try {
            context.write(new IntWritable(Integer.parseInt(words[3])), new Text(words[1]));
        } catch (Exception e) {
            // 大老板只有员工号没有老板号
            context.write(new IntWritable(-1), new Text(words[1]));
        }
    }
}
