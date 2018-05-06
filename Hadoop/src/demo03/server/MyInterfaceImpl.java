package demo03.server;

import java.io.IOException;

import org.apache.hadoop.ipc.ProtocolSignature;

public class MyInterfaceImpl implements MyInterface {

	@Override
	public ProtocolSignature getProtocolSignature(String arg0, long arg1, int arg2) throws IOException {
		// ָ��ǩ��(�汾�ű�ʶ)
		return new ProtocolSignature(MyInterface.versionID, null);
	}

	@Override
	public long getProtocolVersion(String arg0, long arg1) throws IOException {
		// ���ظ�ʵ����İ汾��(ͬ���İ汾�ű�ʶ)
		return MyInterface.versionID;
	}

	@Override
	public String sayHello(String name) {
		// ��д����
		System.out.println("���õ���Server�˵ķ���");
		return "Hello " + name;
	}
}
