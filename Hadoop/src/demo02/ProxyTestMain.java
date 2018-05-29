package demo02;

import java.lang.reflect.Method;

import org.mockito.cglib.proxy.InvocationHandler;
import org.mockito.cglib.proxy.Proxy;

public class ProxyTestMain {
	public static void main(String[] args) {
		// 创建真正的对象
		MyBusiness obj = new MyBusinessImpl();
		
		// 通过代理重写 method1的实现  -> 不修改源代码的情况下
		/*
  		MyBusiness proxyObj = Proxy.newProxyInstance(
				loader,  类加载器(当前代理类)
				interfaces, 真正的对象(实现接口的对象)
				h) InvocationHandler 表示客户端如何调用代理对象
		*/
		
		// 生成真正对象的代理对象, 需要强转
		MyBusiness proxyObj = (MyBusiness) Proxy.newProxyInstance(ProxyTestMain.class.getClassLoader(), 
				obj.getClass().getInterfaces(), 
				new InvocationHandler() {
					// 调用
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						// 客户端的一次调用
						/*
						 * method 客户端调用方法名
						 * args 方法的参数
						 */
						if (method.getName().equals("method01")) {
							// 重写
							System.out.println("method01被重写了");
							return null;
						} else {
							// 其他的方法正常调用(直接调用真正的对象完成就好)
							return method.invoke(obj, args);
						}
					}
				});
		
		// 通过代理对象调用 method01和method02
		proxyObj.method01();
		proxyObj.method02();
	}
}
