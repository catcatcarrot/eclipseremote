package highter;

import java.util.concurrent.TimeUnit;

/*
stop方法已经过时

如何停止线程？
只有一种，run方法结束
开启多线程运行，运行代码通常是循环结构，

只要控制住循环，就可以让run方法结束，也就是线程结束

特殊情况：
当线程处于冻结状态
就不会读取到标记，那么线程就不会结束

当没有指定的方式让冻结的线程恢复到运行状态时，这时就要对冻结进行清除。
强制让线程恢复到运行状态中，这样就可以操作标记，让线程结束 

Thread 类提供该方法 interrupt()

*/

public class StopThreadDemo2 {

	public static void main(String[] args) {
		StopThread st = new StopThread();
		
		Thread t1 = new Thread(st);
		Thread t2 = new Thread(st);
		
		t1.start();
		t2.start();
		
		System.out.println("start");
		int num = 0;
		while(true) {
			if(num++ == 60) {
				//st.changeFlag();
				t1.interrupt();
				t2.interrupt();
				/*
				try {
					TimeUnit.SECONDS.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/
				//st.changeFlag();
				break;
			}
			System.out.println(Thread.currentThread().getName()+"...run"+num);
		}
		System.out.println("over");
	}

}

class StopThread implements Runnable{
	private volatile boolean flag = true;

	@Override
	public synchronized void run() {
		while(flag) {
			System.out.println("start Thread");
			try {
				//Thread.sleep(10);//暂停该线程(不会释放锁)   其他线程可以获得运行资格
				this.wait();   //阻塞状态，必须被唤醒  暂停该线程(释放锁)
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName()+"...Exception");
				flag = false;
			}
			
			System.out.println(Thread.currentThread().getName()+"...run");
		}
	}
	
	public void changeFlag() {
		flag = false;
	}
	
}
