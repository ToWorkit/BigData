package demo03.client;

import java.net.InetSocketAddress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import demo03.server.MyInterface;

public class RPCClient {
	public static void main(String[] args) throws Exception {
		
/*		RPC.getProxy(
 * 			protocol, 调用服务器端的接口
 * 			clientVersion, 版本号
 * 			addr, 指定RPC Server的地址
 * 			conf  配置	
 * 		)
 * */
		
		// 得到的是服务器的一个代理对象
		MyInterface proxy = RPC.getProxy(
				MyInterface.class, 
				MyInterface.versionID, 
				new InetSocketAddress("localhost", 9090), 
				new Configuration());
		
		// 调用RPC 服务端的方法并传参
		String result = proxy.sayHello("World");
		System.out.println(result);
	}
}
