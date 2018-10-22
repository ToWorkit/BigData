package com.ml.partition;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class empMapper extends Mapper<LongWritable, Text, IntWritable, Employee> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        /**
         * private int empno;
         * private String ename;
         * private String job;
         * private int mgr;
         * private String hiredate;
         * private int sal;
         * private int comm;
         * private int deptno;
         */
        // 转为字符串 7654,MARTIN,SALESMAN,7698,1981/9/28,1250,1400,30
        String string = value.toString();
        // 分词
        String[] words = string.split(",");

        // 构建Employee对象
        Employee emp = new Employee();
        emp.setEmpno(Integer.parseInt(words[0]));
        emp.setEname(words[1]);
        emp.setJob(words[2]);
        // 老板号可能为空
        try {
            emp.setMgr(Integer.parseInt(words[3]));
        } catch (Exception e) {
            emp.setMgr(-1);
        }
        emp.setHiredate(words[4]);
        emp.setSal(Integer.parseInt(words[5]));
        // 奖金可能为空
        try {
            emp.setComm(Integer.parseInt(words[6]));
        } catch (Exception e) {
            emp.setComm(0);
        }
        emp.setDeptno(Integer.parseInt(words[7]));

        // 输出 部门号，员工对象
        context.write(new IntWritable(emp.getDeptno()), emp);
    }
}
