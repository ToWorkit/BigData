package demo12;

public class Test {
	public static void main(String[] args) {
		/*		a:A,B,C,D
		b:B,C,D
		c:A,D,E
		d:E,F,A
		e:A,C,F*/
		// ���� a:A,B,C,D ��������
		String data = "a:A,B,C,D";
		
		// �ִ�
		// Сд
		String lower = data.split(":")[0];
		// ��д
		String[] upper = data.split(":")[1].split(",");
		System.out.println(lower);
		for (String item: upper) {
			System.out.println(item);			
		}
	}
}
