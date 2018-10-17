package com.ml.rpc.client;

import com.ml.rpc.server.myRpcInterface;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;

public class RpcClient {
    public static void main(String[] args) throws IOException {
        // 得到服务器端的一个代理对象
        myRpcInterface proxy = RPC.getProxy(myRpcInterface.class,  // 调用服务器端的接口函数
                myRpcInterface.versionID, // 版本号
                new InetSocketAddress("localhost", 9090), // 指定RPC Server的地址
                new Configuration());

        System.out.println(proxy.say(" World"));
    }
}
