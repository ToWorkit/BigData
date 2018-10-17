package com.ml.serializable.mr;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

// 接收map的输出 k3 部门号 v3 (员工对象1， 员工对象2，...)
public class SalarySumReducer extends Reducer<IntWritable, Employee, IntWritable, IntWritable> {
    @Override
    protected void reduce(IntWritable key, Iterable<Employee> values, Context context) throws IOException, InterruptedException {
        // 求和
        int sum = 0;
        for (Employee value : values) {
            sum += (value.getSal() + value.getComm());
        }
        // 输出
        context.write(key, new IntWritable(sum));
    }
}
