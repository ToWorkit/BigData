package com.ml.sort.salary;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class SalaryMapper extends Mapper<LongWritable, Text, IntWritable, NullWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 数据：7654,MARTIN,SALESMAN,7698,1981/9/28,1250,1400,30
        String string = value.toString();
        String[] words = string.split(",");

        // 输出 k2 薪水，v3 空值
        context.write(new IntWritable(Integer.parseInt(words[5])), NullWritable.get());
    }
}
