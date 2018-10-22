package com.ml.equalJoin;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class equalReducer extends Reducer<IntWritable, Text, Text, Text> {
    @Override
    protected void reduce(IntWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        // 部门名和员工名
        String bName = "";
        String empListName = "";

        for (Text value : values) {
            String string = value.toString();
            if (string.indexOf("*") >= 0) {
                // 部门名称，并去掉 *
                bName = string.substring(1);
            } else {
                // 员工姓名
                empListName += string + "; ";
            }
        }

        // 输出
        context.write(new Text(bName), new Text(empListName));
    }
}
