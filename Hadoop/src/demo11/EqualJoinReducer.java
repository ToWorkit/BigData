package demo11;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class EqualJoinReducer extends Reducer<IntWritable, Text, Text, Text>{
	@Override
	protected void reduce(IntWritable k3, Iterable<Text> v3, Reducer<IntWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		// �����������ƺ�Ա������
		String dname = "";
		String empListName = "";
		
		for (Text str: v3) {
			String name = str.toString();
			// �ж��Ƿ�洢�� * ��
			int index = name.indexOf("*");
			
			if (index >= 0) {
				// �ǲ������ƣ�ȥ����һ��*��
				dname = name.substring(1);
			} else {
				// ��Ա��������
				empListName = name + ";" + empListName;
			}
		}
		// ���
		context.write(new Text(dname), new Text(empListName));
	}
}
