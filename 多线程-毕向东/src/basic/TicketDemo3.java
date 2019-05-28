package basic;

/*
 * ͬ�������õ�����һ������
 * ������Ҫ��������ã���ô��������һ��������������ã�����this
 * ����ͬ������ʹ�õ�����this
 * 
 * ͨ���ó��������֤
 * 
 * ʹ�������߳�����Ʊ��
 * һ���߳���ͬ���������
 * һ���߳���ͬ��������
 * ����ִ����Ʊ����
 * 
 * */

public class TicketDemo3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ticket3 t = new Ticket3();//����������̣߳���Ϊ��Thread��û�й�ϵ
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

