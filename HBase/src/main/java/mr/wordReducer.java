package mr;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;

//                                             k3       v3          keyOut 表示输出一条记录(指定行键)
public class wordReducer extends TableReducer<Text, IntWritable, ImmutableBytesWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        // 求和
        int total = 0;
        for (IntWritable value : values) {
            total += value.get();
        }

        // 输出的是表中的一条记录所以需要构造Put对象
        // key.toString( -> rowKey 行键
        Put put = new Put(Bytes.toBytes(key.toString()));
        put.add(Bytes.toBytes("content"), // 列族
                Bytes.toBytes("result"), // 列
                Bytes.toBytes(String.valueOf(total)) // 值
            );

        // 输出
        context.write(new ImmutableBytesWritable(Bytes.toBytes(key.toString())), put);

    }
}
