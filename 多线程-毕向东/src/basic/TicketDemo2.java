package basic;

/**
 * 需求：简单的卖票程序
 * 多个窗口同时卖票
 *  
 * 创建线程的第二种方式：实现Runnable接口
 * 
 * 步骤：
 * 1.定义类实现Runnable接口
 * 2.覆盖Runnable接口中的run方法
 * 		将线程要运行的代码存放在该run方法中
 * 
 * 3.通过Thread类建立线程对象
 * 4.将Runnable接口中的子类对象作为实际参数传递给Thread类的构造函数
 * 		为什么要将Runnable接口的子类对象传递给Thread类的构造函数
 * 		因为自定义的run方法所属的对象是Runnable接口的子类对象
 * 		所以要让线程去执行指定对象的run方法，必须明确该run方法所属的对象
 * 
 * 5.调用Thread类的start方法开始线程并调用Runnable接口子类的run方法
 * 
 * 实现方式和继承方式有什么区别？
 * 
 * 实现方式好处：避免了单继承的局限性
 * 在定义线程时，建议使用实现方式
 * 
 * 两种方式区别：
 * 继承Thread：线程代码存放在Thread子类的run方法中
 * 实现Runnable：线程代码存在接口子类的run方法中
 * 
 * 
 */

/*
 * 通过分析，发现打印出0，-1，-2等错票
 * 
 * 多线程的运行出现了安全问题。	
 * 
 * 问题的原因：
 * 		当多条语句在操作同一个线程共享数据时，一个线程对多条语句只执行了一部分，还没有执行完
 * 		另一个线程参与进来执行，导致了共享数据的错误。
 * 
 * 解决办法：
 * 		对多条操作共享数据的语句，只能让一个线程都执行完，在执行过程中，其他线程不可以参与运行。
 * 
 * java对于多线程的安全问题提供了专业的解决方式
 * 
 * 就是 同步代码块
 * 
 * synchronized(对象)
 * {
 * 		需要被同步的代码
 * }
 * 
 * 对象如同锁，持有锁的线程可以在同步中执行，
 * 没有持有锁的线程即使获取CPU的执行权，也进不去，因为没有获取锁
 * 
 * 火车上的卫生间---经典
 * 
 * 同步的前提：
 * 1.必须要有两个或者两个以上的线程
 * 2.必须是多个线程使用同一个锁
 * 
 * 必须保证同步中只能有一个线程在运行
 * 
 * 好处：解决了多线程的安全问题
 * 
 * 弊端：多个线程需要判断锁，较为消耗资源
 * 
 * */

public class TicketDemo2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ticket2 t = new Ticket2();//这个对象不是线程，因为和Thread类没有关系
		Thread t1 = new Thread(t);
		Thread t2 = new Thread(t);
		Thread t3 = new Thread(t);
		Thread t4 = new Thread(t);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
	}

}

class Ticket2 implements Runnable{
	private int ticket = 10;
	private Object obj = new Object();
	
	public void run() {
		while(true) {
			//synchronized (obj) {
				if(ticket>0) {
					/*try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}*/
					System.out.println(Thread.currentThread().getName()+"sale:"+ticket--);
				}
			//}
		}
	}
}

