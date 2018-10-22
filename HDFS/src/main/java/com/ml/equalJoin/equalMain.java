package com.ml.equalJoin;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class equalMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 创建job并指定运行函数
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(equalMain.class);

        // 指定map和输出类型
        job.setMapperClass(equalMapper.class);
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(Text.class);

        // 指定reducer和输出类型
        job.setReducerClass(equalReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        // 指定job的输出和输入
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        /**
         * ACCOUNTING	MILLER; KING; CLARK;
         * RESEARCH	    ADAMS; SCOTT; SMITH; JONES; FORD;
         * SALES	    TURNER; ALLEN; BLAKE; MARTIN; WARD; JAMES;
         * OPERATIONS
         */
        // 打印日志
        job.waitForCompletion(true);
    }
}
