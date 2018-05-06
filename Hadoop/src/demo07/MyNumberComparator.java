package demo07;

import org.apache.hadoop.io.IntWritable;

public class MyNumberComparator extends IntWritable.Comparator {
	@Override
	public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
		// 定义自己的排序比较规则: 改为降序 负号
		return -super.compare(b1, s1, l1, b2, s2, l2);
	}
}
