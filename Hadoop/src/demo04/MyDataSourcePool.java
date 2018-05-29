package demo04;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class MyDataSourcePool implements DataSource {
	
	// 配置jdbc
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://192.168.8.14:3306/hive";
	private static String user = "hiveowner";
	private static String password = "Password_1";
	
	// 定义个一个链表保存10个链接
	private static LinkedList<Connection> dataSource = new LinkedList<>();
	
	// 来十个
	static {
		try {
			// 注册驱动
			Class.forName(driver);
			// jdbc
			for (int i = 0; i < 10; i ++) {
				dataSource.add(DriverManager.getConnection(url, user, password));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//初始化连接池，放入10个链接
	@Override
	public Connection getConnection(String arg0, String arg1) throws SQLException {
		// 从连接池中返回一个连接
		if (dataSource.size() > 0) {
			// 有一个连接，就直接返回该链接
			return dataSource.removeFirst();
		} else {
			throw new SQLException("系统忙");
		}
	}
	
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLoginTimeout(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
