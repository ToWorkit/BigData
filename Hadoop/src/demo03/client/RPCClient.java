package demo03.client;

import java.net.InetSocketAddress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import demo03.server.MyInterface;

public class RPCClient {
	public static void main(String[] args) throws Exception {
		
/*		RPC.getProxy(
 * 			protocol, ���÷������˵Ľӿ�
 * 			clientVersion, �汾��
 * 			addr, ָ��RPC Server�ĵ�ַ
 * 			conf  ����	
 * 		)
 * */
		
		// �õ����Ƿ�������һ���������
		MyInterface proxy = RPC.getProxy(
				MyInterface.class, 
				MyInterface.versionID, 
				new InetSocketAddress("localhost", 9090), 
				new Configuration());
		
		// ����RPC ����˵ķ���������
		String result = proxy.sayHello("World");
		System.out.println(result);
	}
}
