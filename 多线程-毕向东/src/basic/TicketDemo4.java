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
 */

/**
 * ���ͬ����������̬���κ�,ʹ�õ�����ʲô�أ�
 * 
 * ͨ����֤�����ֲ�����this����Ϊ��̬������Ҳ�����Զ���this
 * 
 * ��̬���ڴ���ʱ���ڴ���û�б�����󣬵���һ���и����Ӧ���ֽ����ļ�����
 * ����.class  �ö����������Class
 * 
 * ��̬��ͬ��������ʹ�õ����Ǹ÷�����������ֽ����ļ�����   ����.class
 * */

public class TicketDemo4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ticket4 t = new Ticket4();//����������̣߳���Ϊ��Thread��û�й�ϵ
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

