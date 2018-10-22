package com.ml.partition;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class empMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // Job
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(empMain.class);

        // map及其输出
        job.setMapperClass(empMapper.class);
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(Employee.class);

        // 分区 创建 3个
        job.setPartitionerClass(empPartition.class);
        job.setNumReduceTasks(3);

        // reducer及其输出
        job.setReducerClass(empReducer.class);
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(Text.class);

        // job的输入和输出
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.waitForCompletion(true);
    }
}
