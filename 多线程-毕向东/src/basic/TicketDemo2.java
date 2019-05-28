package basic;

/**
 * ���󣺼򵥵���Ʊ����
 * �������ͬʱ��Ʊ
 *  
 * �����̵߳ĵڶ��ַ�ʽ��ʵ��Runnable�ӿ�
 * 
 * ���裺
 * 1.������ʵ��Runnable�ӿ�
 * 2.����Runnable�ӿ��е�run����
 * 		���߳�Ҫ���еĴ������ڸ�run������
 * 
 * 3.ͨ��Thread�ཨ���̶߳���
 * 4.��Runnable�ӿ��е����������Ϊʵ�ʲ������ݸ�Thread��Ĺ��캯��
 * 		ΪʲôҪ��Runnable�ӿڵ�������󴫵ݸ�Thread��Ĺ��캯��
 * 		��Ϊ�Զ����run���������Ķ�����Runnable�ӿڵ��������
 * 		����Ҫ���߳�ȥִ��ָ�������run������������ȷ��run���������Ķ���
 * 
 * 5.����Thread���start������ʼ�̲߳�����Runnable�ӿ������run����
 * 
 * ʵ�ַ�ʽ�ͼ̳з�ʽ��ʲô����
 * 
 * ʵ�ַ�ʽ�ô��������˵��̳еľ�����
 * �ڶ����߳�ʱ������ʹ��ʵ�ַ�ʽ
 * 
 * ���ַ�ʽ����
 * �̳�Thread���̴߳�������Thread�����run������
 * ʵ��Runnable���̴߳�����ڽӿ������run������
 * 
 * 
 */

/*
 * ͨ�����������ִ�ӡ��0��-1��-2�ȴ�Ʊ
 * 
 * ���̵߳����г����˰�ȫ���⡣	
 * 
 * �����ԭ��
 * 		����������ڲ���ͬһ���̹߳�������ʱ��һ���̶߳Զ������ִֻ����һ���֣���û��ִ����
 * 		��һ���̲߳������ִ�У������˹������ݵĴ���
 * 
 * ����취��
 * 		�Զ��������������ݵ���䣬ֻ����һ���̶߳�ִ���꣬��ִ�й����У������̲߳����Բ������С�
 * 
 * java���ڶ��̵߳İ�ȫ�����ṩ��רҵ�Ľ����ʽ
 * 
 * ���� ͬ�������
 * 
 * synchronized(����)
 * {
 * 		��Ҫ��ͬ���Ĵ���
 * }
 * 
 * ������ͬ�������������߳̿�����ͬ����ִ�У�
 * û�г��������̼߳�ʹ��ȡCPU��ִ��Ȩ��Ҳ����ȥ����Ϊû�л�ȡ��
 * 
 * ���ϵ�������---����
 * 
 * ͬ����ǰ�᣺
 * 1.����Ҫ�����������������ϵ��߳�
 * 2.�����Ƕ���߳�ʹ��ͬһ����
 * 
 * ���뱣֤ͬ����ֻ����һ���߳�������
 * 
 * �ô�������˶��̵߳İ�ȫ����
 * 
 * �׶ˣ�����߳���Ҫ�ж�������Ϊ������Դ
 * 
 * */

public class TicketDemo2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ticket2 t = new Ticket2();//����������̣߳���Ϊ��Thread��û�й�ϵ
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

