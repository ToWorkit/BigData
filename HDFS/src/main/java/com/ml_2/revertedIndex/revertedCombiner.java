package com.ml_2.revertedIndex;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class revertedCombiner extends Reducer<Text, Text, Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        // 数据 I:test01.txt 1
        // I:test02.txt (1, 1, 1)
        // 对单词频率求和
        int total = 0;
        for (Text value : values) {
            total += Integer.parseInt(value.toString());
        }

        // 将单词与文件分离

    }
}
