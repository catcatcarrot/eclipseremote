package basic;

/**
 * 练习：
 * 创建两个线程，和主线程交替运行
 * 
 * 原来线程都有自己默认的名称
 * Thread-编号 该编号从0开始
 * 
 * static Thread  currentThread():获取当前线程对象
 * getName():获取线程名称
 * 
 * 设置线程名称：setName()或者构造函数
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
		//局部变量在每一个线程区域中都有独立的一份
		for(int i = 0;i<60;i++) {
			System.out.println((Thread.currentThread()==this)+"..."+this.getName()+" run..."+i);
		}
	}
	
}
