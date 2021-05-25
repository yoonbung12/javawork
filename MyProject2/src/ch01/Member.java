package ch01;

import java.util.Calendar;
import java.util.Scanner;

public class Member {

	// ① 독감예방 접종이 가능한지 여부를 확인하는 메소드를 정의합니다.
	// - 매개변수로 태어난 해(년도)를 전달받습니다.
	// - 15세 미만의 경우와 65세 이상의 경우 “무료예방접종이 가능합니다.” 메시지가 콘솔에 출력하도록 합니다.
	// - 위에서 정의한 조건의 범위가 아닌 나머지의 경우 “무료접종 대상이 아닙니다.”라고 출력하도록 합니다.
	void vaccinCheck(int birthYear) {

		//int age = 2021 - birthYear - 1; // 나이 연산
		int age = ageCal(birthYear);

		boolean check1 = age < 15 || age >= 65;
		boolean check2 = !(age >= 15 && age < 65);

		System.out.println(check1);
		System.out.println(check2);

		if (check2) {
			System.out.println("무료예방접종이 가능합니다.");
		} else {
			System.out.println("무료접종 대상이 아닙니다.");
		}

	}

	// ② 건강검진 대상 여부를 판별하고 어떤 검진이 가능한지 확인하는 메소드를 정의합니다.
	// - 매개변수로 태어난 해(년도)를 전달받습니다.
	// - 대한민국 성인( 20세 )의 경우 무료로 2년마다 건강검진을 받을 수 있습니다.
	// - 짝수 해에 태어난 사람은 올해가 짝수년이라면 검사 대상이 됩니다.
	// - 40 이상의 경우는 암 검사도 무료로 검사를 할 수 있습니다.
	
	void checkup(int birthYear) {

		//int age = 2021-birthYear-1;
		int age = ageCal(birthYear);
		
		boolean check1 = birthYear%2==0 && 2021%2==0 ||  
				birthYear%2==1 && 2021%2==1 ;
		
		boolean check2 = birthYear%2 == 2021%2;
		
		if(age>=20 && check2 ) {
			System.out.println("검진 대상");
			if(age>=40) {
				System.out.println("암검진 대상자");
			}
		} else {
			System.out.println("검진 대상이 아닙니다.");
		}

	}

	// 내어난 년도를 입력하면 나이를 반환하는 메소드
	int ageCal(int year) {
		
		//int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		
		
		return Calendar.getInstance().get(Calendar.YEAR)-year-1;
	}
	
	
	public static void main(String[] args) {
		
		
		System.out.println(Calendar.getInstance().get(Calendar.YEAR));	

		Scanner scanner = new Scanner(System.in);
		System.out.println("태어난 년도를 입력해주세요");
		int birthYear = scanner.nextInt();

		Member member = new Member();

		member.vaccinCheck(birthYear);
		member.checkup(birthYear);

	}

}