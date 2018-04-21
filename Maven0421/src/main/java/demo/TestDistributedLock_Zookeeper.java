package demo;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class TestDistributedLock_Zookeeper {
	// 定义共享的资源
	private static int number = 10;
	private static void getNumber() {
		System.out.println("业务方法开始");
		System.out.println("当前值" + number);
		number --;
		// 休眠两秒
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("业务结束");
	}
	
	public static void main(String[] args) {
		// 等待策略
		/*
		 * 1000 等待的时间
		 * 10 重试的次数
		 */
		RetryPolicy policy = new ExponentialBackoffRetry(1000, 10);
		
		// 创建客户端请求从zk中获取锁
		CuratorFramework cf = CuratorFrameworkFactory.builder()
								// zk 的地址
								.connectString("192.168.106.11:2181")
								// 等待策略
								.retryPolicy(policy)
								.build();
		// 启动客户端
		cf.start();
		// 获取锁的信息
		final InterProcessMutex lock = new InterProcessMutex(cf, "/mylock");
		
		// 启动10个线程访问共享资源
		for (int i = 0; i < 10; i ++) {
			new Thread(new Runnable() {

				public void run() {
					try {
						// 请求得到锁，没有得到就会使用RetryPolicy
						lock.acquire();
						// 实现访问共享的资源
						getNumber();
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							// 释放锁
							lock.release();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
			}).start();
		}
	}
}
