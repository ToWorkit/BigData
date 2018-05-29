package demo08_分区;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;
// k2 v2
public class MyEmployeeParitioner extends Partitioner<IntWritable, Employee_08> {

	/*
	 * (non-Javadoc)
	 * @see org.apache.hadoop.mapreduce.Partitioner#getPartition(java.lang.Object, java.lang.Object, int)
	 * 
	 * numParition
	 *  参数 -> 建立多少个分区
	 */
	@Override
	public int getPartition(IntWritable k2, Employee_08 v2, int numParition) {
		// 建立分区
		// 根据部门号
		if (v2.getDeptno() == 10) {
			// 放入 1 号分区
			// 取模，求余
			return 1 % numParition;
		} else if (v2.getDeptno() == 20) {
			// 放入 2 号分区
			return 2 % numParition;
		} else {
			// 放入 0 号分区
			return 3 % numParition;
		}
	}

}
