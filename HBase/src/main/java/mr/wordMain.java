package mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;

public class wordMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 配置信息
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum", "192.168.220.11");

        // Job
        Job job = Job.getInstance(conf);
        job.setJarByClass(wordMain.class);

        // 定义一个扫描器只读取 content:info 这个列的数据
        Scan scan = new Scan();
        scan.addColumn(Bytes.toBytes("content"), Bytes.toBytes("info"));

        // 指定Mapper
        TableMapReduceUtil.initTableMapperJob(Bytes.toBytes("word"), // 表
                scan, // 扫描器，过滤获取需要的数据
                wordMapper.class,
                Text.class,
                IntWritable.class,
                job
            );

        // 指定Reducer
        TableMapReduceUtil.initTableReducerJob("stat", wordReducer.class, job);

        // 执行任务
        job.waitForCompletion(true);
    }
}
