package demo08;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class PartEmployeeMapper extends Mapper<LongWritable, Text, IntWritable, Employee_08> {
	@Override
	protected void map(LongWritable key1, Text value1, Mapper<LongWritable, Text, IntWritable, Employee_08>.Context context)
			throws IOException, InterruptedException {
		// ���ݣ�7654,MARTIN,SALESMAN,7698,1981/9/28,1250,1400,30]
		String data = value1.toString();
		// �ִ�
		String[] words = data.split(",");
		
		// ����Ա������
		Employee_08 e = new Employee_08();
		
		// ����Ա��������
		// Ա����
		e.setEmpno(Integer.parseInt(words[0]));
		// ����
		e.setEname(words[1]);
		// ְλ
		e.setJob(words[2]);
		// �ϰ�� -> ��Ҫɸѡ(��Щû���ϰ�)
		try {
			// ����ת��
			e.setMgr(Integer.parseInt(words[3]));
		} catch(Exception ex) {
			e.setMgr(-1);
		}
		// ��ְ����
		e.setHiredate(words[4]);
		// ��н
		e.setSal(Integer.parseInt(words[5]));
		// ���� -> ͬ����Ҫɸѡ
		try {
			e.setComm(Integer.parseInt(words[6]));
		} catch(Exception ex) {
			e.setComm(0);
		}
		// ���ź�
		e.setDeptno(Integer.parseInt(words[7]));
		
		// ���: k2 ���ź�	v2 Ա������
		context.write(new IntWritable(e.getDeptno()), e);
	}
}