package com.ml.revertedIndex;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class revertedCombiner extends Reducer<Text, Text, Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        // 数据 I:Love01.txt 1
        // 对单词频率求和
        int total = 0;
        for (Text value : values) {
            total += Integer.parseInt(value.toString());
        }

        // 将单词和文件分离
        String[] strings = key.toString().split(":");
        String word = strings[0];
        String path = strings[1];

        // 输出
        context.write(new Text(word), new Text(path + ":" + total));
    }
}
