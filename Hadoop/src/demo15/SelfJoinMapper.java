<<<<<<< HEAD
package demo15;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SelfJoinMapper extends Mapper<LongWritable, Text, IntWritable, Text>{
	@Override
	protected void map(LongWritable k1, Text v1, Mapper<LongWritable, Text, IntWritable, Text>.Context context)
			throws IOException, InterruptedException {
		//数据：7654,MARTIN,SALESMAN,7698,1981/9/28,1250,1400,30
		String data = v1.toString();
		// 分词
		String[] words = data.split(",");
		
		// 输出
		// 1、作为老板表
		context.write(new IntWritable(Integer.parseInt(words[0])), new Text("*" + words[1]));
		// 2、作为员工表 输出老板号可能会产生Exception(老板自己没有老板号)
		try {
			context.write(new IntWritable(Integer.parseInt(words[3])), new Text(words[1]));
		} catch (Exception ex) {
			// 产生异常则表示是大老板
			context.write(new IntWritable(-1), new Text(words[1]));
		}
	}
}
/*	7369,SMITH,CLERK,7902,1980/12/17,800,,20
	7499,ALLEN,SALESMAN,7698,1981/2/20,1600,300,30
	7521,WARD,SALESMAN,7698,1981/2/22,1250,500,30
	7566,JONES,MANAGER,7839,1981/4/2,2975,,20
	7654,MARTIN,SALESMAN,7698,1981/9/28,1250,1400,30
	7698,BLAKE,MANAGER,7839,1981/5/1,2850,,30
	7782,CLARK,MANAGER,7839,1981/6/9,2450,,10
	7788,SCOTT,ANALYST,7566,1987/4/19,3000,,20
	7839,KING,PRESIDENT,,1981/11/17,5000,,10
	7844,TURNER,SALESMAN,7698,1981/9/8,1500,0,30
	7876,ADAMS,CLERK,7788,1987/5/23,1100,,20
	7900,JAMES,CLERK,7698,1981/12/3,950,,30
	7902,FORD,ANALYST,7566,1981/12/3,3000,,20
	7934,MILLER,CLERK,7782,1982/1/23,1300,,10	*/
=======
package demo15;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SelfJoinMapper extends Mapper<LongWritable, Text, IntWritable, Text>{
	@Override
	protected void map(LongWritable k1, Text v1, Mapper<LongWritable, Text, IntWritable, Text>.Context context)
			throws IOException, InterruptedException {
		//数据：7654,MARTIN,SALESMAN,7698,1981/9/28,1250,1400,30
		String data = v1.toString();
		// 分词
		String[] words = data.split(",");
		
		// 输出
		// 1、作为老板表
		context.write(new IntWritable(Integer.parseInt(words[0])), new Text("*" + words[1]));
		// 2、作为员工表 输出老板号可能会产生Exception(老板自己没有老板号)
		try {
			context.write(new IntWritable(Integer.parseInt(words[3])), new Text(words[1]));
		} catch (Exception ex) {
			// 产生异常则表示是大老板
			context.write(new IntWritable(-1), new Text(words[1]));
		}
	}
}
>>>>>>> 319b834b6626113fe9d78979fe185d71cc63acec
