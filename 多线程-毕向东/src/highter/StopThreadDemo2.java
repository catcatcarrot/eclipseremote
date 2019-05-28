package highter;

import java.util.concurrent.TimeUnit;

/*
stop�����Ѿ���ʱ

���ֹͣ�̣߳�
ֻ��һ�֣�run��������
�������߳����У����д���ͨ����ѭ���ṹ��

ֻҪ����סѭ�����Ϳ�����run����������Ҳ�����߳̽���

���������
���̴߳��ڶ���״̬
�Ͳ����ȡ����ǣ���ô�߳̾Ͳ������

��û��ָ���ķ�ʽ�ö�����ָ̻߳�������״̬ʱ����ʱ��Ҫ�Զ�����������
ǿ�����ָ̻߳�������״̬�У������Ϳ��Բ�����ǣ����߳̽��� 

Thread ���ṩ�÷��� interrupt()

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
				//Thread.sleep(10);//��ͣ���߳�(�����ͷ���)   �����߳̿��Ի�������ʸ�
				this.wait();   //����״̬�����뱻����  ��ͣ���߳�(�ͷ���)
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
