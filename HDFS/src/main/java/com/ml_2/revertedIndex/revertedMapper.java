package com.ml_2.revertedIndex;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class revertedMapper extends Mapper<LongWritable, Text, Text, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // test.txt => I Love You And You Love Me
        String[] words = value.toString().split(" ");
        // 获取输入的文件路径名 test/love01.txt
        // context.getInputSplit() -> org.apache.hadoop.mapreduce.InputSplit, 需要转为 FileSplit -> org.apache.hadoop.mapreduce.lib.input.FileSplit
        // getPath() -> org.apache.hadoop.fs.Path
        String path = ((FileSplit)context.getInputSplit()).getPath().toString();

        // 截取文件名
        String substring = path.substring(path.lastIndexOf("/") + 1);

        // 输出
        for (String word : words) {
            // 单词:文件名   1
            context.write(new Text(word + ":" + substring), new Text("1"));
        }
    }
}
