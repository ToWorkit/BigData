package demo03.server;

import org.apache.hadoop.ipc.VersionedProtocol;

public interface MyInterface extends VersionedProtocol {
	// ����һ���汾�ţ�ǩ������ʶ���ͻ�����Ҫ����һ�£�
	public static long versionID = 1;
	// ����ͻ��˿��Ե��õķ���
	public String sayHello(String name);
}
