package threadTest;

public class CountDownThread extends Thread{
	//③10초 카운팅은 스레드를 이용해서 처리해봅시다.
	@Override
	public void run() {
		for(int i = 10; i > 0; i--) {
			if(HighLowGame.check) {
				return;
			}
			try {
				sleep(1000);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println();
		System.out.println("시관초과입니다.");
		System.out.println("프로그램을 종료합니다.");
		System.exit(0);
	}
	
}
