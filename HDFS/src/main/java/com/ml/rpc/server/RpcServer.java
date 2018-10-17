package com.ml.rpc.server;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;

public class RpcServer {
    public static void main(String[] args) throws IOException {
        // 定义一个 RPC Builder
        RPC.Builder builder = new RPC.Builder(new Configuration());

        // 指定RPC Server的参数
        builder.setBindAddress("localhost");
        builder.setPort(9090);

        // 将代码部署到Server上
        builder.setProtocol(myRpcInterface.class);
        builder.setInstance(new myRpcImpl());

        // 创建Server
        RPC.Server build = builder.build();

        // 启动
        build.start();
    }
}
