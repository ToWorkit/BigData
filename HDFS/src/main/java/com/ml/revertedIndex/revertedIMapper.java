package com.ml.revertedIndex;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class revertedIMapper extends Mapper<LongWritable, Text, Text, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // love.txt => I Love You And You Love Me
        String[] words = value.toString().split(" ");
        // 获取传入的文件名路径 /test/love01.txt
        String path = ((FileSplit) context.getInputSplit()).getPath().toString();

        // 截取文件名 最后一个斜线(含头不含尾)
        String substring = path.substring(path.lastIndexOf("/") + 1);

        // 输出
        for (String word : words) {
            // 单词:文件名
            context.write(new Text(word + ":" + substring), new Text("1"));
        }
    }
}
