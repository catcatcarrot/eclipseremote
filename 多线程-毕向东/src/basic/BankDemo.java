package basic;

//bbb+aaa
/**
* ����
* ������һ�����
* �����������ֱ��300Ԫ��ÿ�δ�100����3�Ρ�
* 
* Ŀ�ģ��ó����Ƿ��а�ȫ���⣬����У�����ν����
* 
* ����ҵ����⣿
* 1.��ȷ��Щ�����Ƕ��߳����д���
* 2.��ȷ��������
* 3.��ȷ���߳����д�������Щ����ǲ����������ݵ�
* 
* */


public class BankDemo {

	public static void main(String[] args) {
		Cus c = new Cus();
		Thread t1 = new Thread(c);
		Thread t2 = new Thread(c);
		t1.start();
		t2.start();

	}

}

class Bank{
	private int sum;
	private Object obj = new Object();
	
	public synchronized void add(int n) {
		//synchronized(obj) {
			sum=sum+n;
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"sum="+sum);
		//}
	}
}

class Cus implements Runnable{
	private Bank b = new Bank();

	@Override
	public void run() {
		for(int i=0;i<3;i++) {//每个线程自己都有
			b.add(100);
		}
		
	}
	
}
