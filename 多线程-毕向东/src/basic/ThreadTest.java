package basic;

/**
 * ��ϰ��
 * ���������̣߳������߳̽�������
 * 
 * ԭ���̶߳����Լ�Ĭ�ϵ�����
 * Thread-��� �ñ�Ŵ�0��ʼ
 * 
 * static Thread  currentThread():��ȡ��ǰ�̶߳���
 * getName():��ȡ�߳�����
 * 
 * �����߳����ƣ�setName()���߹��캯��
 * 
 * */

public class ThreadTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test t1 = new Test("one");
		Test t2 = new Test("two");
		t1.start();
		t2.start();
		
		for(int i = 0;i<60;i++) {
			System.out.println("main..."+i);
		}
		
	}

}

class Test extends Thread{
	//private String name;
	
	public Test(String name) {
		//this.name = name;
		super(name);
	}
	
	public void run() {
		//�ֲ�������ÿһ���߳������ж��ж�����һ��
		for(int i = 0;i<60;i++) {
			System.out.println((Thread.currentThread()==this)+"..."+this.getName()+" run..."+i);
		}
	}
	
}
