package com.ml.partition;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class empPartition extends Partitioner<IntWritable, Employee> {
    /**
     * @param intWritable   Map 的输出
     * @param employee      Map 的输出
     * @param numPartitions 建立多少个分区
     * @return
     */
    @Override
    public int getPartition(IntWritable intWritable, Employee employee, int numPartitions) {
        // 取模建立分区
        // 10号部门
        if (employee.getDeptno() == 10) {
            // 分区 1
            return 1 % numPartitions;
        } else if (employee.getDeptno() == 20) {  // 20号部门
            // 分区 2
            return 2 % numPartitions;
        } else {    // 30 号部门
            // 分区 3
            return 3 % numPartitions;
        }
    }
}
