package com.ml_2.revertedIndex;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * combiner
 *      map -> combiner 会自动对单个文件内出现的单词分组
 *      将单个文件中出现的词频数合并
 */
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
        String[] words = key.toString().split(":");
        String word = words[0];
        String file = words[1];

        // 输出
        //  Love    "xx1.txt->1"
        //  Love    "xx2.txt->4"
        context.write(new Text(word), new Text(file + "->" + total));
    }
}
