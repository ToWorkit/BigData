package com.ml.selfJoin;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class selfJMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 创建一个job并指定job的运行函数
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(selfJMain.class);

        // 指定map和输出类型
        job.setMapperClass(selfJMapper.class);
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(Text.class);

        // 指定reducer和输出类型
        job.setReducerClass(selfJReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        // 指定job的输入和输出
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        /**
         * JONES	SCOTT; FORD;
         * BLAKE	WARD; MARTIN; ALLEN; TURNER; JAMES;
         * CLARK	MILLER;
         * SCOTT	ADAMS;
         * KING	CLARK; JONES; BLAKE;
         * FORD	SMITH;
         */
        // 打印日志
        job.waitForCompletion(true);
    }
}
