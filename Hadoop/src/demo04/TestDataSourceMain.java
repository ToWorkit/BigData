package demo04;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDataSourceMain {

	public static void main(String[] args) throws Exception {
		// �����ӳ��л�ȡ���ӣ�ʹ����Ϻ��ͷ�����
		MyDataSourcePool pool = new MyDataSourcePool();
		for (int i = 0; i < 10; i ++) {
			// �õ������������������ݿ�����
			Connection conn = pool.getConnection();
			
			System.out.println("��" + i + "�����ӣ���" + conn);
			
			// �����ݿ�����ӻ��������ݿ⣬���������ӳ�
			conn.close();
		}
	}
}
