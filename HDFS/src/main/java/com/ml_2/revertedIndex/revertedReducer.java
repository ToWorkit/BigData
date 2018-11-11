package com.ml_2.revertedIndex;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * reducer
 *      combiner -> reducer 会自动对单词分组(shuffle)
 *      Love     ("xx.txt->1", "xx.txt->2", "xx.txt->1")
 */
public class revertedReducer extends Reducer<Text, Text, Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        // Love     ("xx.txt->1", "xx.txt->2", "xx.txt->1")
        String  str = "";
        for (Text value : values) {
            str += value.toString() + ";";
        }
        // 输出   Love    "xx.txt->1";"xx.txt->2";"xx.txt->1"
        context.write(key, new Text(str));
    }
}
