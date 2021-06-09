package threadTest;

import java.util.Scanner;

public class HighLowGame {
	
	public static boolean check = false;
	public static void main(String[] args) {
		
		
		//②사용자에게 숫자를 입력 받고 
		Scanner scanner = new Scanner(System.in);
		CountDownThread ct = new CountDownThread();
		int userNum = -1;
		
		
		//①1~100 사이의 랜덤 한 숫자를 추출합니다.
		int num = (int)(Math.random() * 100 + 1);
		System.out.println(num);
		//10초 카운트 시작
		ct.start();
		
		//②사용자가 고를 숫자(반복문 사용)
		
		while(userNum != num) {
			System.out.println("정답을 입력해주세요.");
			userNum = scanner.nextInt();
		
		//②랜덤 숫자와 비교하고(if 문을 사용할것), 높은 숫자인지 낮은 숫자인지 출력
		if(userNum == num) {
			System.out.println("프로그램을 종료합니다.");
		} else if(userNum > num) {
			System.out.println("랜덤숫자보다 높습니다. 다시 입력하세요.");
			
		} else {
			System.out.println("랜덤숫자보다 작습니다. 다시 입력하세요.");
		
		} 
		
		}
		check = true;
		System.out.println("정답입니다.");
		System.out.println("정답은" + num + "입니다.");
		scanner.close();
		
	}
  

}




//

//
//④10초 이전에 맞추면 미션 성공, 10초가 지나면 프로그램 종료하는 흐름으로 만들어봅시다.