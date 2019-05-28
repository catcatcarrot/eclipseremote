package basic;

/**
 * java VM 启动的时候会有一个进程java.exe.
 * 该进程中至少有一个线程负责java程序的执行，
 * 而且这个线程运行的代码存在于main方法中
 * 该线程称之为主线程
 * 
 * 扩展：其实更细节说明虚拟机。jvm启动不止一个线程，还有负责垃圾回收机制的线程
 * 
 * 1.如何在自定义代码中，自定义一个线程？
 * 
 * 创建线程的第一种方式：继承Thread类
 * 步骤：
 * 1.定义类继承Thread
 * 2.复写Thread类中的run方法
 * 	          目的：将自定义的代码存储在run方法中让线程运行
 * 3.调用线程的start方法，
 * 		该方法有两个作用，启动线程，调用run方法
 * 
 * 发现运行结果每一次都不同
 * 因为多个线程都获取cpu的执行权，cpu执行到谁，谁就运行
 * 明确一点，在某一个时刻，只能有一个程序在运行(多核除外)
 * cpu在做着快速的切换，以达到看上去是同时运行的效果
 * 我们可以形象的把多线程的运行形容为在互相抢夺cpu的执行权
 * 
 * 这就是多线程的一个特性：随机性，谁抢到谁执行，至于执行多长，cpu说了算
 * 
 * 为什么要覆盖run方法？
 * 
 * Thread类用于描述线程
 * 该类就定义了一个功能，用于存储线程要运行的代码，该存储功能就是run方法
 * 
 * 也就是说Thread类中的run方法，用于存储线程要运行的代码
 * 
 * */

public class ThreadDemo {

	public static void main(String[] args) {//jvm-->主线程
		// TODO Auto-generated method stub
		Demo d = new Demo();   //创建一个这种对象就是创建一个线程
		d.start();             //开启线程并执行该线程的run方法
		//d.run();             仅仅是对象调用方法，而线程创建了，并没有运行
		
		for(int i = 0;i<50;i++) {
			System.out.println("Hello World!---"+i);
		}
	}

}

class Demo extends Thread {

	public void run() {
		for(int i = 0;i<50;i++) {
			System.out.println("demo run---"+i);
		}
	}
	
}
