package demo08_����;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;
// k2 v2
public class MyEmployeeParitioner extends Partitioner<IntWritable, Employee_08> {

	/*
	 * (non-Javadoc)
	 * @see org.apache.hadoop.mapreduce.Partitioner#getPartition(java.lang.Object, java.lang.Object, int)
	 * 
	 * numParition
	 *  ���� -> �������ٸ�����
	 */
	@Override
	public int getPartition(IntWritable k2, Employee_08 v2, int numParition) {
		// ��������
		// ���ݲ��ź�
		if (v2.getDeptno() == 10) {
			// ���� 1 �ŷ���
			// ȡģ������
			return 1 % numParition;
		} else if (v2.getDeptno() == 20) {
			// ���� 2 �ŷ���
			return 2 % numParition;
		} else {
			// ���� 0 �ŷ���
			return 3 % numParition;
		}
	}

}
