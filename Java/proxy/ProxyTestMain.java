import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTestMain {
    public static void main(String[] args) {
        // 创建真正的对象
        MyBusiness obj = new MyBusinessImpl();

        // 重写方法一，生成代理对象
        // 类的加载器，真正对象的实现接口，客户端如何调用代理对象(new一个然后实现方法的重写)
        MyBusiness proxyObj = (MyBusiness) Proxy.newProxyInstance(ProxyTestMain.class.getClassLoader(), obj.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                /**
                 * 定位要重写的方法
                 */
                if (method.getName().equals("method_01")) {
                    // 重写
                    System.out.println("调用method_01的方法时会被代理到此处的逻辑");
                } else {
                    // 不感兴趣的放过
                    return method.invoke(obj, args);
                }
                return 1;
            }
        });
        // 通过代理对象调用方法
        proxyObj.method_01();
        proxyObj.method_02();
    }
}
