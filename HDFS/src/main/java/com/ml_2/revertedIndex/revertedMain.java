package com.ml_2.revertedIndex;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class revertedMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // job
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(revertedMain.class);

        // map
        job.setMapperClass(revertedMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        // combiner
        job.setCombinerClass(revertedCombiner.class);

        // reducer
        job.setReducerClass(revertedReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        // 输入和输出
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // run
        job.waitForCompletion(true);
    }
}
