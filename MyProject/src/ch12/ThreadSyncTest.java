package ch12;

public class ThreadSyncTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Increment inc = new Increment();
		
		//스레드 생성
		IncThread it1 = new IncThread(inc);
		IncThread it2 = new IncThread(inc);
		IncThread it3 = new IncThread(inc);
		
		
		it1.start();
		it2.start();
		it3.start();
		
		try {
			it1.join();
			it2.join();
			it3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(inc.getNum());
		
	}

}

class IncThread extends Thread {
	
	Increment inc;
	
	public IncThread(Increment inc) {
		this.inc = inc;
	}
	
	public void run() {
		for(int i = 0; i< 10000; i++) {
			inc.Increment();
		}
	}
}

class Increment {
	
	int num = 0;
	public synchronized void Increment() { //이 메서드에synchronized 써야함.
		num++; // num = num + 1;
	}
	public int getNum() {
		return num;
	}
}
