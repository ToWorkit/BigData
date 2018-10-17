package com.ml.rpc.server;

import org.apache.hadoop.ipc.VersionedProtocol;

public interface myRpcInterface extends VersionedProtocol {
    // 定义一个版本号
    public static long versionID = 1;

    // 定义客户端可以调用的方法
    public String say(String name);
}
