package demo02;

import java.lang.reflect.Method;

import org.mockito.cglib.proxy.InvocationHandler;
import org.mockito.cglib.proxy.Proxy;

public class ProxyTestMain {
	public static void main(String[] args) {
		// ���������Ķ���
		MyBusiness obj = new MyBusinessImpl();
		
		// ͨ��������д method1��ʵ��  -> ���޸�Դ����������
		/*
  		MyBusiness proxyObj = Proxy.newProxyInstance(
				loader,  �������(��ǰ������)
				interfaces, �����Ķ���(ʵ�ֽӿڵĶ���)
				h) InvocationHandler ��ʾ�ͻ�����ε��ô������
		*/
		
		// ������������Ĵ������, ��Ҫǿת
		MyBusiness proxyObj = (MyBusiness) Proxy.newProxyInstance(ProxyTestMain.class.getClassLoader(), 
				obj.getClass().getInterfaces(), 
				new InvocationHandler() {
					// ����
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						// �ͻ��˵�һ�ε���
						/*
						 * method �ͻ��˵��÷�����
						 * args �����Ĳ���
						 */
						if (method.getName().equals("method01")) {
							// ��д
							System.out.println("method01����д��");
							return null;
						} else {
							// �����ķ�����������(ֱ�ӵ��������Ķ�����ɾͺ�)
							return method.invoke(obj, args);
						}
					}
				});
		
		// ͨ������������ method01��method02
		proxyObj.method01();
		proxyObj.method02();
	}
}
