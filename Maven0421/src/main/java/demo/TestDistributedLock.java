package demo;

public class TestDistributedLock {
	// 定义一个共享的资源
	private static int number = 10;
	private static void getNubmer () {
		System.out.println("业务方法开始");
		System.out.println("当前值" + number);
		number --;
		
		// 休眠两秒钟
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("业务方法结束");
	}
	public static void main(String[] args) {
		for (int i = 0; i < 10; i ++) {
			new Thread(new Runnable() {

				public void run() {
					// TODO Auto-generated method stub
					getNubmer();
				}
				
			}).start();
		}
	}
}
