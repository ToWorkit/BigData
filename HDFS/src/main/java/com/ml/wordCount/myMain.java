package com.ml.wordCount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 组合 map reducer
 * 运行
 *  hadoop jar wordcount.jar com.ml.wordCount.myMain /mp/test.text /mp/wc01
 */
public class myMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        /**
         * 创建job和任务入口
         */
        Job job = Job.getInstance(new Configuration());
        // main方法所在的class
        job.setJarByClass(myMain.class);

        /**
         * 指定job的Mapper及其输出的类型 <k2 v2>
         */
        job.setMapperClass(myMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        /**
         * 指定job的Reducer及其输出的类型 <k4 v4> (同时也是任务job的输出)
         */
        job.setReducerClass(myReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        /**
         * 指定job的输入和输出
         */
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // 执行job并开启日志模式
        job.waitForCompletion(true);
    }
}
