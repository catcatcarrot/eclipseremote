package basic;

public class DeadLockTest {

	public static void main(String[] args) {
		Thread t1 = new Thread(new TestDeadLoack(true));
		Thread t2 = new Thread(new TestDeadLoack(false));
		t1.start();
		t2.start();
	}

}

class TestDeadLoack implements Runnable{
	private boolean flag;

	public TestDeadLoack(boolean flag) {
		super();
		this.flag = flag;
	}
	
	public void run() {
		if(flag) {
			synchronized (MyLock.locka) {
				System.out.println(Thread.currentThread().getName()+"if locka");
				synchronized (MyLock.lockb) {
					System.out.println(Thread.currentThread().getName()+"if lockb");
				}
			}
		}else {
			synchronized (MyLock.lockb) {
				System.out.println(Thread.currentThread().getName()+"else lockb");
				synchronized (MyLock.locka) {
					System.out.println(Thread.currentThread().getName()+"else locka");
				}
			}
		}
	}
	
}

class MyLock{
	static Object locka = new Object();
	static Object lockb = new Object();
}
