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
	
	// ����jdbc
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://192.168.8.14:3306/hive";
	private static String user = "hiveowner";
	private static String password = "Password_1";
	
	// �����һ��������10������
	private static LinkedList<Connection> dataSource = new LinkedList<>();
	
	// ��ʮ��
	static {
		try {
			// ע������
			Class.forName(driver);
			// jdbc
			for (int i = 0; i < 10; i ++) {
				dataSource.add(DriverManager.getConnection(url, user, password));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//��ʼ�����ӳأ�����10������
	@Override
	public Connection getConnection(String arg0, String arg1) throws SQLException {
		// �����ӳ��з���һ������
		if (dataSource.size() > 0) {
			// ��һ�����ӣ���ֱ�ӷ��ظ�����
			return dataSource.removeFirst();
		} else {
			throw new SQLException("ϵͳæ");
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
