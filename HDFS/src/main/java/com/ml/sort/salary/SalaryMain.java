package com.ml.sort.salary;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class SalaryMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 创建job并指定运行函数
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(SalaryMain.class);

        // 指定map和输出类型
        job.setMapperClass(SalaryMapper.class);
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(NullWritable.class);

        // 指定自己的比较器
        job.setSortComparatorClass(SalaryComparator.class);

        // 指定job的输入和输出
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // 执行job并打印日志
        job.waitForCompletion(true);
    }
}
