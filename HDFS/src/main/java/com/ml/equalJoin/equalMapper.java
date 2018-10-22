package com.ml.equalJoin;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class equalMapper extends Mapper<LongWritable, Text, IntWritable, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 同时获取两个表的数据
        String string = value.toString();
        //  分词
        String[] words = string.split(",");

        // 部门表  10,ACCOUNTING,NEW YORK
        // 员工表  7499,ALLEN,SALESMAN,7698,1981/2/20,1600,300,30
        if (words.length == 8) {
            // 员工表
            context.write(new IntWritable(Integer.parseInt(words[7])), new Text(words[1]));
        } else {
            // 因为reducer会根据部门号group by所以加 * 区分部门和员工
            context.write(new IntWritable(Integer.parseInt(words[0])), new Text("*" + words[1]));
        }
    }
}
