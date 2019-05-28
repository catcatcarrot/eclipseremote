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
 */

/**
 * 如果同步函数被静态修饰后,使用的锁是什么呢？
 * 
 * 通过验证，发现不再是this。因为静态方法中也不可以定义this
 * 
 * 静态进内存中时，内存中没有本类对象，但是一定有该类对应的字节码文件对象
 * 类名.class  该对象的类型是Class
 * 
 * 静态的同步方法，使用的锁是该方法所在类的字节码文件对象   类名.class
 * */

public class TicketDemo4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ticket4 t = new Ticket4();//这个对象不是线程，因为和Thread类没有关系
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

class Ticket4 implements Runnable{
	private static int ticket = 100;
	Object obj = new Object();
	boolean flag = true;
	
	public void run() {
		if(flag) {
			while(true) {
				synchronized (Ticket4.class) {
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
	
	public static synchronized void show() {
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

