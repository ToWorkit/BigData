package demo03.server;

import java.io.IOException;

import org.apache.hadoop.HadoopIllegalArgumentException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.RPC.Server;

public class RPCServer {
	public static void main(String[] args) throws HadoopIllegalArgumentException, IOException {
		// 定义一个RPC Builder
		RPC.Builder builder = new RPC.Builder(new Configuration());
		
		// 配置参数
		builder.setBindAddress("localhost");
		builder.setPort(9090);
		
		// 将自己写的程序部署到Server上
		builder.setProtocol(MyInterface.class);
		builder.setInstance(new MyInterfaceImpl());
		
		// 创建Server
		Server server = builder.build();
		
		// 启动
		server.start();
	}
}
