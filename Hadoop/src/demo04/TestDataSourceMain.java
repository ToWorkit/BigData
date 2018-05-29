package demo04;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDataSourceMain {

	public static void main(String[] args) throws Exception {
		// 从连接池中获取链接，使用完毕后释放链接
		MyDataSourcePool pool = new MyDataSourcePool();
		for (int i = 0; i < 10; i ++) {
			// 得到的连接是真正的数据库链接
			Connection conn = pool.getConnection();
			
			System.out.println("第" + i + "个链接，是" + conn);
			
			// 把数据库的链接还给了数据库，而不是连接池
			conn.close();
		}
	}
}
