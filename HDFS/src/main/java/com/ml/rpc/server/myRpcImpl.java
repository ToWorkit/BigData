package com.ml.rpc.server;

import org.apache.hadoop.ipc.ProtocolSignature;

import java.io.IOException;

public class myRpcImpl implements myRpcInterface {
    public String say(String name) {
        System.out.println("客户端调用了服务端的代码");
        return "Hello" + name;
    }

    public long getProtocolVersion(String s, long l) throws IOException {
        // 返回该实现类的版本号
        return versionID;
    }

    public ProtocolSignature getProtocolSignature(String s, long l, int i) throws IOException {
        // 指定版本号(签名)
        return new ProtocolSignature(versionID, null);
    }
}
