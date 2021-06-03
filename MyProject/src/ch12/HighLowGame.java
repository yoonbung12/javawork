package ch12;

import java.util.Scanner;

public class HighLowGame {
	//③ 10초 카운팅은 스레드를 이용해서 처리해봅시다.
	//④ 10초 이전에 맞추면 미션 성공, 10초가 지나면 프로그램 종료하는 흐름으로 만들어봅시다.

	public static boolean chk1 = false;
	public static void main(String[] args) {
		
		CountDownThread1 ct1 = new CountDownThread1();
		ct1.start();

		
		//② 사용자에게 숫자를 입력 받고 
		Scanner s = new Scanner(System.in);
		
		System.out.println("사용자 숫자 입력: ");
		
		
		
		//1. 랜덤 숫자 뽑기
		RandomNumber r1 = new RandomNumber(0);
		r1.randNum(); //확인차 넣어봄
		//랜덤 숫자와 비교하고, 높은 숫자인지 낮은 숫자인지 출력
		while(true) {
			int num = s.nextInt();
			
		if(num == r1.getNumber()) {
			System.out.println("정답!! 프로그램종료합니다." );
			s.close();
			System.exit(0);
		}else if(num < r1.getNumber()) {
			System.out.println("랜덤 숫자보다 낮습니다." );
			continue;
		} else if(num > r1.getNumber()){
			System.out.println("랜덤 숫자보다 높습니다."); 
			continue;
			
		} 
		
		}
				
	}

}
class CountDownThread1 extends Thread {
	public void run() {
		System.out.println("게임을 시작합니다.10초안에 값을 입력하세요.");
		for(int i = 10; i >0; i--) {
			if(HighLowGame.chk1) {
				return;
			}
			System.out.println(i);
			try {
				sleep(10000);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("입력시간이 초과되어 프로그램을 종료합니다.");
			System.exit(0);
		}
	}
}
//① 1~100 사이의 랜덤 한 숫자를 추출합니다.
//랜덤 숫자 클래스를 만듯것!!
class RandomNumber {
	int number;
	
	//생성자
	public RandomNumber(int number) { 
		this.number = number;
	}
	//랜덤뽑기 메소드
	public void randNum() {
		number = (int)((Math.random() * 100) + 1);
		System.out.println("랜덤숫자를 뽑습니다. :" + number);	
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
}
