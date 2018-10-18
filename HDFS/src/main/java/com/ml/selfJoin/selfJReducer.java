package com.ml.selfJoin;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class selfJReducer extends Reducer<IntWritable, Text, Text, Text> {
    @Override
    protected void reduce(IntWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        // 存储老板和员工
        String boosName = "";
        String empNameList = "";

        for (Text value : values) {
            // 需要转为String格式
            String name = value.toString();

            // 判断是老板还是员工
            if (name.indexOf("*") >= 0) {
                // 老板, 去掉*号
                boosName = name.substring(1);
            } else {
                // 员工
                empNameList = name + "; " + empNameList;
            }
        }
        // 输出
        /**
         * 清洗掉没有老板或者没有员工的数据
         */
        if (boosName.length() > 0 && empNameList.length() > 0) {
            context.write(new Text(boosName), new Text(empNameList));
        }
    }
}
