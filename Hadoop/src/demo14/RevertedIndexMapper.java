package demo14;

/*And	(data_03.txt:1)
I	(data_01.txt:1)(data_03.txt:1)
Love	(data_03.txt:2)(data_02.txt:1)(data_01.txt:1)
Me	(data_02.txt:1)(data_03.txt:1)
You	(data_01.txt:1)(data_03.txt:2)(data_02.txt:1)*/

// hadoop jar test.jar /mydata/data_0*.txt /ouput/myR_01

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class RevertedIndexMapper extends Mapper<LongWritable, Text, Text, Text>{
	@Override
	protected void map(LongWritable k1, Text v1, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		// ���ݣ�I love Beijing and love Shanghai
		// �õ����������ĸ��ļ���  /mydata/data01.txt
		String path = ((FileSplit)context.getInputSplit()).getPath().toString();
		
		// �õ��ļ���
		int index = path.lastIndexOf("/");
		// �ļ���
		String fileName = path.substring(index + 1);
		
		// �ִ�
		String[] words = v1.toString().split(" ");
		for (String w: words) {
			// ����:�ļ���
			context.write(new Text(w + ":" + fileName), new Text("1"));
		}
	}
}
