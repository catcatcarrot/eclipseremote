package highter;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerLockDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ResourceLock res = new ResourceLock();
		
		new Thread(new ProducerLock(res)).start();
		new Thread(new ProducerLock(res)).start();
		new Thread(new ConsumerLock(res)).start();
		new Thread(new ConsumerLock(res)).start();
	}

}

/**
 * JDK1.5���ṩ�˶��߳������������
 * ��ͬ��synchronized�滻����ʾ��Lock����
 * ��object�е�wait notify notifyAll�滻����condition����
 * �ö������Lock����ȡ
 * 
 * ��ʾ���У�ʵ����ֻ���ѶԷ�����
 * */

class  ResourceLock{
	private String name;
	private int count = 1;
	private boolean flag = false;
	private Lock lock = new ReentrantLock();
	private Condition condition_pro = lock.newCondition();
	private Condition condition_con = lock.newCondition();
	
	public void set(String name) throws InterruptedException {
		lock.lock();
		try {
			/*Ϊʲô��while������if�� ���� t1�ȴ���t2û���ڵȴ�������t1��
			  ��t2���ִ��Ȩ������ִ�У�flag��Ϊtrue��t1���ִ��Ȩ����ִ��һ��*/
			while(flag)
				condition_pro.await();//t1 t2�ȴ�
			
			this.name = name+"--"+count++;
			System.out.println(Thread.currentThread().getName()+"...������..."+this.name);
			flag = true;
			condition_con.signal();
		} finally {
			lock.unlock();//�ͷ����Ķ���һ��Ҫִ��
		}
	}
	
	public void out() throws InterruptedException {
		lock.lock();
		try {
			while(flag==false)
				condition_con.await();
				
			System.out.println(Thread.currentThread().getName()+"...������......."+this.name);
			flag=false;
			condition_pro.signal();//���� t1 t2�е�һ��
		}finally {
			lock.unlock();
		}
	}
}

class ProducerLock implements Runnable{
	private ResourceLock res;

	protected ProducerLock(ResourceLock res) {
		super();
		this.res = res;
	}

	@Override
	public void run() {
		while(true) {
			try {
				res.set("+��Ʒ+");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}

class ConsumerLock implements Runnable{
	private ResourceLock res;

	protected ConsumerLock(ResourceLock res) {
		super();
		this.res = res;
	}

	@Override
	public void run() {
		while(true) {
			try {
				res.out();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

