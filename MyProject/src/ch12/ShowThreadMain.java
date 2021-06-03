package ch12;

public class ShowThreadMain {

	public static void main(String[] args) {
		
		//스레드는 동시에 작업을 하지는 못함 그러나 시분할로 인해 동시에 하는것처럼 보인다!!
		
		//스레드 인스턴스 생성
		ShowThread t1 = new ShowThread("스레드1");
		ShowThread t2 = new ShowThread("         스레드2");
		
		//스레드의 실행
		t1.start();
		t2.start();

	}

}
