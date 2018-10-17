package com.ml.wordCount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Map输入
 *  文本      k3
 *  计数      v3
 * Reduce输出
 *  文本     k4
 *  累加和   v4
 */
public class myReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        //  对map传过来的数据累加求和 v3
        int sum  = 0;
        for (IntWritable v: values) {
            sum += v.get();
        }

        // 输出
        // k4 v4
        context.write(key, new IntWritable(sum));
    }
}
