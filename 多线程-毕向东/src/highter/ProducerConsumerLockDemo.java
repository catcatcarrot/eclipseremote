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
 * JDK1.5中提供了多线程升级解决方案
 * 把同步synchronized替换成显示的Lock操作
 * 将object中的wait notify notifyAll替换成了condition对象
 * 该对象可以Lock锁获取
 * 
 * 该示例中，实现了只唤醒对方操作
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
			/*为什么用while，不用if？ 假设 t1等待，t2没有在等待，唤醒t1，
			  但t2获得执行权，进来执行，flag变为true，t1获得执行权会再执行一次*/
			while(flag)
				condition_pro.await();//t1 t2等待
			
			this.name = name+"--"+count++;
			System.out.println(Thread.currentThread().getName()+"...生产者..."+this.name);
			flag = true;
			condition_con.signal();
		} finally {
			lock.unlock();//释放锁的动作一定要执行
		}
	}
	
	public void out() throws InterruptedException {
		lock.lock();
		try {
			while(flag==false)
				condition_con.await();
				
			System.out.println(Thread.currentThread().getName()+"...消费者......."+this.name);
			flag=false;
			condition_pro.signal();//唤醒 t1 t2中的一个
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
				res.set("+商品+");
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

