package annualtotal;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class AnnualTotalMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // Job
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(AnnualTotalMain.class);

        // Map及其输出
        job.setMapperClass(AnnualTotalMapper.class);
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(DoubleWritable.class);

        // Reducer及其输出
        job.setReducerClass(AnnualTotalReducer.class);
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(Text.class);

        // job的输入和输出
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // 执行
        job.waitForCompletion(true);
    }
}
