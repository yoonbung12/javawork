package ch12;

public class ShowThread extends Thread { //스레드 클래스를 상속한다.!!!!
	
	String tName;
	
	ShowThread(String name) {
		this.tName = name;
	}
	
	@Override
	public void run() {
		
		for(int i = 0; i < 100; i++) {
			System.out.println("안녕하세요~!!" + tName + "입니다.");
			
			try {
				sleep(100);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		
		}
	}

	
}
