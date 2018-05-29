package demo14;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class RevertedIndexCombiner extends Reducer<Text, Text, Text, Text>{
	@Override
	protected void reduce(Text k21, Iterable<Text> v21, Reducer<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {	
		// ��ͣ���ͬһ���ļ��е�ÿ�����ʵ�Ƶ�����
		int total = 0;
		for(Text v: v21) {
			total += Integer.parseInt(v.toString());
		}
		// ���
		// �����ʺ��ļ�������  k21 => love:data01.txt
		String data = k21.toString();
		// ð��
		int index = data.indexOf(":");
		
		// ����
		String word = data.substring(0, index);
		// �ļ���
		String fileName = data.substring(index + 1);
		
		// ���
		context.write(new Text(word), new Text(fileName + ":" + total));
	}
}
