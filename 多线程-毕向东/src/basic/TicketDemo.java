package basic;

/**
 * 需求：简单的卖票程序
 * 多个窗口同时卖票
 *  
 * */

public class TicketDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ticket t1 = new Ticket();
		Ticket t2 = new Ticket();
		Ticket t3 = new Ticket();
		Ticket t4 = new Ticket();
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}

}

class Ticket extends Thread{
	private int ticket = 100;
	
	public void run() {
		while(true) {
			if(ticket>0) {
				System.out.println(currentThread().getName()+"sale:"+ticket--);
			}
		}
	}
}
