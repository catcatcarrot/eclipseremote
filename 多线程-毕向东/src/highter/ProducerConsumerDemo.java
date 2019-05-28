package highter;

public class ProducerConsumerDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Resource res = new Resource();
		
		new Thread(new Producer(res)).start();
		new Thread(new Producer(res)).start();
		new Thread(new Consumer(res)).start();
		new Thread(new Consumer(res)).start();
	}

}

class Resource{
	private String name;
	private int count = 1;
	private boolean flag = false;
	//t0 t1
	public synchronized void set(String name) {
		while(flag)
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	
		this.name = name+"--"+count++;
		
		System.out.println(Thread.currentThread().getName()+"...生产者..."+this.name);
		flag = true;
		this.notifyAll();
	}
	//t2 t3
	public synchronized void out() {
		while(!flag)
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		System.out.println(Thread.currentThread().getName()+"...消费者......."+this.name);
		flag=false;
		this.notifyAll();
	}
}

class Producer implements Runnable{
	private Resource res;

	protected Producer(Resource res) {
		super();
		this.res = res;
	}

	@Override
	public void run() {
		while(true) {
			res.set("+商品+");
		}
		
	}
	
}

class Consumer implements Runnable{
	private Resource res;

	protected Consumer(Resource res) {
		super();
		this.res = res;
	}

	@Override
	public void run() {
		while(true) {
			res.out();
		}
		
	}
}

