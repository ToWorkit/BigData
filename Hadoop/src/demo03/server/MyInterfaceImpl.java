package demo03.server;

import java.io.IOException;

import org.apache.hadoop.ipc.ProtocolSignature;

public class MyInterfaceImpl implements MyInterface {

	@Override
	public ProtocolSignature getProtocolSignature(String arg0, long arg1, int arg2) throws IOException {
		// 指定签名(版本号标识)
		return new ProtocolSignature(MyInterface.versionID, null);
	}

	@Override
	public long getProtocolVersion(String arg0, long arg1) throws IOException {
		// 返回该实现类的版本号(同样的版本号标识)
		return MyInterface.versionID;
	}

	@Override
	public String sayHello(String name) {
		// 重写方法
		System.out.println("调用到了Server端的方法");
		return "Hello " + name;
	}
}
