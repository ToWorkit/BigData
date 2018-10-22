package com.ml.revertedIndex;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class revertedReducer extends Reducer<Text, Text, Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        // 将 Iterable<Text> 处理为 Text
        String str = "";
        for (Text value : values) {
            str += value.toString() + ";";
        }
        // 数据直接输出，自带group by(去重合并分组)
        context.write(key, new Text(str));
    }
}
