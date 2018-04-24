package demo12;

public class Test {
	public static void main(String[] args) {
		/*		a:A,B,C,D
		b:B,C,D
		c:A,D,E
		d:E,F,A
		e:A,C,F*/
		// 找重 a:A,B,C,D 处理数据
		String data = "a:A,B,C,D";
		
		// 分词
		// 小写
		String lower = data.split(":")[0];
		// 大写
		String[] upper = data.split(":")[1].split(",");
		System.out.println(lower);
		for (String item: upper) {
			System.out.println(item);			
		}
	}
}
