package demo16;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SalaryTotalMapper extends Mapper<LongWritable, Text, IntWritable, Employee>{
	@Override
	protected void map(LongWritable k1, Text v1, Mapper<LongWritable, Text, IntWritable, Employee>.Context context)
			throws IOException, InterruptedException {
		// 数据：7654,MARTIN,SALESMAN,7698,1981/9/28,1250,1400,30
		String data = v1.toString();
		// 分词
		String[] words = data.split(",");
		
		// 创建员工对象
		Employee e = new Employee();
		
		// 设置员工的属性
		// 员工号
		e.setEmpno(Integer.parseInt(words[0]));
		// 姓名
		e.setEname(words[1]);
		// 职位
		e.setJob(words[2]);
		// 老板号，需要注意老板木有老板号
		try {
			e.setMgr(Integer.parseInt(words[3]));
		} catch (Exception ex) {
			// 木有老板号的
			e.setMgr(-1);
		}
		// 入职日期
		e.setHiredate(words[4]);
		// 月薪
		e.setSal(Integer.parseInt(words[5]));
		
		// 奖金，有些干的不好木有奖金
		try {
			e.setComm(Integer.parseInt(words[6]));
		} catch(Exception ex) {
			e.setComm(0);
		}
		
		// 部门号
		e.setDeptno(Integer.parseInt(words[7]));
		
		// 输出 k2 部门号，v2 员工对象
		context.write(new IntWritable(e.getDeptno()), e);
	}
}
