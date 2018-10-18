package com.ml.offset;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class offsetMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 创建job并指定运行函数
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(offsetMain.class);

        // 指定map和输出类型
        job.setMapperClass(offsetMapper.class);
        job.setMapOutputKeyClass(LongWritable.class);
        job.setMapOutputValueClass(Text.class);

        // 没有reducer

        // 指定Job的输入和输出
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        /**
         * 0	I LOVE YOU
         * 11	YOU LOVE ME
         * 23	I LOVE YOU AND YOU LOVE ME
         */
        job.waitForCompletion(true);
    }
}

