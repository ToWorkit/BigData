package com.ml.partition;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class empReducer extends Reducer<IntWritable, Employee, IntWritable, Text> {
    @Override
    protected void reduce(IntWritable key, Iterable<Employee> values, Context context) throws IOException, InterruptedException {
        // 输出，部门号，员工对象
/*        for (Employee value : values) {
            context.write(key, value);
        }*/
        String str = "";
        for (Employee value : values) {
            str += value.toString() + "---";
        }
        context.write(key, new Text(str));
    }
}
