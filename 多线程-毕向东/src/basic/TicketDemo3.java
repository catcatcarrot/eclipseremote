package basic;

/*
 * 同步函数用的是哪一个锁？
 * 函数需要被对象调用，那么函数都有一个所属对象的引用，就是this
 * 所以同步函数使用的锁是this
 * 
 * 通过该程序进行验证
 * 
 * 使用两个线程来卖票：
 * 一个线程在同步代码块中
 * 一个线程在同步函数中
 * 都在执行卖票动作
 * 
 * */

public class TicketDemo3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ticket3 t = new Ticket3();//这个对象不是线程，因为和Thread类没有关系
		Thread t1 = new Thread(t);
		Thread t2 = new Thread(t);
		t1.start();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t.flag=false;
		t2.start();
	}

}

class Ticket3 implements Runnable{
	private int ticket = 100;
	Object obj = new Object();
	boolean flag = true;
	
	public void run() {
		if(flag) {
			while(true) {
				synchronized (this) {
					if(ticket>0) {
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName()+"...code...:"+ticket--);
					}
				}
			}
		}else {
			while(true)
				show();
		}
	}
	
	public synchronized void show() {
		if(ticket>0) {
			   try {
			       Thread.sleep(10);
			   } catch (InterruptedException e) {
					e.printStackTrace();
			   }
			   System.out.println(Thread.currentThread().getName()+"...show......:"+ticket--);
		}
	}
	
	
}

