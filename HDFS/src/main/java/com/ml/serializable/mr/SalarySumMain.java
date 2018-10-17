package com.ml.serializable.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class SalarySumMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 创建job并指定job的运行函数
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(SalarySumMain.class);

        // 指定map并设置map的输出
        job.setMapperClass(SalarySumMapper.class);
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(Employee.class);

        // 指定reducer并设置reducer的输出
        job.setReducerClass(SalarySumReducer.class);
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(IntWritable.class);

        // 指定job的输入和输出路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // 执行job并打印日志
        job.waitForCompletion(true);
    }
}
