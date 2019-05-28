package basic;

/*
死锁：
同步中嵌套同步
*/

public class DeadLockDemo {

	public static void main(String[] args) {
		TicketDead t = new TicketDead();
		
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

class TicketDead implements Runnable{
	private int ticket = 100;
	Object obj = new Object();
	boolean flag = true;
	
	public void run() {
		if(flag) {
			while(true) {
				synchronized (obj) {
					show();
				}
			}
		}else {
			while(true)
				show();
		}
	}
	
	public synchronized void show() {
		synchronized (obj) {
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
	
}


