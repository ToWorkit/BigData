package com.ml.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理
 */
public class MyProxyMain {
    public static void main(String[] args) {
        // 创建真正的对象
        final MyMethod myMethod = new MyMethodImpl();
        /**
         * 通过代理在不修改源码的前提下重写 method1
         */
        // 生成真正对象的代理对象
//        Object newProxyInstance = (MyMethod)Proxy.newProxyInstance(MyProxyMain.class.getClassLoader(), myMethod.getClass().getInterfaces(), new InvocationHandler() {
        MyMethod newProxyInstance = (MyMethod) Proxy.newProxyInstance(MyProxyMain.class.getClassLoader(), myMethod.getClass().getInterfaces(), new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                /**
                 * 重写
                 * method 客户端调用方法名
                 * args 方法的参数
                 */
                if (method.getName().equals("method1")) {
                    // 重写
                    System.out.println("method1方法重写");
                    return null;
                } else {
                    // 其他方法直接调用真正的对象执行不作修改
                    return method.invoke(myMethod, args);
                }
            }
        });

        // 通过代理对象调用 method1 method2
/*        ((MyMethod) newProxyInstance).method1();
        ((MyMethod) newProxyInstance).method2();*/
        newProxyInstance.method1();
        newProxyInstance.method2();
    }
}
