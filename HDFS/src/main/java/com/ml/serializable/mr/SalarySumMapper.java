package com.ml.serializable.mr;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class SalarySumMapper extends Mapper<LongWritable, Text, IntWritable, Employee> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 数据：7654,MARTIN,SALESMAN,7698,1981/9/28,1250,1400,30
        String data = value.toString();
        // 分词
        String[] words = data.split(",");

        // 创建员工对象并设置员工属性
        Employee employee = new Employee();
        /**
         * 员工号
         * 姓名
         * 职位
         * 老板号  注意老板号为null值时的处理
         * 入职日期
         * 月薪
         * 奖金   注意奖金为null值时的处理
         * 部门号
         */
        employee.setEmpno(Integer.parseInt(words[0]));
        employee.setEname(words[1]);
        employee.setJob(words[2]);
        // 老板号
        try {
            employee.setMgr(Integer.parseInt(words[3]));
        } catch (Exception e) {
            employee.setMgr(-1);
        }
        employee.setHiredate(words[4]);
        employee.setSal(Integer.parseInt(words[5]));
        // 奖金
        try {
            employee.setComm(Integer.parseInt(words[6]));
        } catch (Exception e) {
            employee.setComm(0);
        }
        employee.setDeptno(Integer.parseInt(words[7]));

        // 输出 k2 部门号 v2 员工对象
        context.write(new IntWritable(employee.getDeptno()), employee);
    }
}
