package ordertotal;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class productsMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //1、创建Job
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(productsMain.class);

        //2、指定任务的Mapper和输出的类型
        job.setMapperClass(productsMapper.class);
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(Text.class);

        //3、指定任务的Reducer和输出的类型
        job.setReducerClass(productsReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        //4、任务的输入和输出
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        //5、执行
        job.waitForCompletion(true);

    }
}
