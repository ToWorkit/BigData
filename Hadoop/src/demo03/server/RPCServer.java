package demo03.server;

import java.io.IOException;

import org.apache.hadoop.HadoopIllegalArgumentException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.RPC.Server;

public class RPCServer {
	public static void main(String[] args) throws HadoopIllegalArgumentException, IOException {
		// ����һ��RPC Builder
		RPC.Builder builder = new RPC.Builder(new Configuration());
		
		// ���ò���
		builder.setBindAddress("localhost");
		builder.setPort(9090);
		
		// ���Լ�д�ĳ�����Server��
		builder.setProtocol(MyInterface.class);
		builder.setInstance(new MyInterfaceImpl());
		
		// ����Server
		Server server = builder.build();
		
		// ����
		server.start();
	}
}
