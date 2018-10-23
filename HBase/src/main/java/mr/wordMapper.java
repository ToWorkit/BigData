package mr;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;

// <k1  v1> 就是表中的一条记录               k2      v2
public class wordMapper extends TableMapper<Text, IntWritable> {
    @Override
    protected void map(ImmutableBytesWritable key, Result value, Context context) throws IOException, InterruptedException {
        // 获取数据 I Love You， HBase 中没有数据类型，都是二进制格式的，需要转为String
        String string = Bytes.toString(value.getValue(Bytes.toBytes("content"), Bytes.toBytes("info")));

        // 分词
        String[] words = string.split(" ");

        // 输出
        for (String word : words) {
            context.write(new Text(word), new IntWritable(1));
        }
    }
}
