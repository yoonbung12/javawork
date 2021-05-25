package ch02;

import java.util.*;

// java.lang.*
public class Calculator {
	// 클래스 이름 -> 대문자 시작 케멀 케이스
		// 변수 이름 -> 소문자 시작 케밀 케이스
		// 상수-> 모두 대문자
		double pi1 = 3.14d;
		float pi2 = 3.14f;
		final float PI = 3.14f; //값을 변경할수 없게 만듬(상수).
		
	long plus(int num1, int num2) {
//		long result = num1 + num2;
//		return result;
		return (long)num1 + num2;
	}
	long minus(int num1, int num2) {
		return (long)num1 - num2;
	}
	long multi(int num1, int num2) {
		return(long)num1 * num2;
	}
	float div(int num1, int num2) {
		return (float)num1/num2;
	}
	
	float circum(float r) {
		return 2 * PI * r;	//int * float * float
	}
	float cirArea(float r) {
		return PI * r * r;
	}
	public static void main(String[] args) {
		
		Calculator cal = new Calculator();
		System.out.println(cal.PI);
		
		System.out.println("1 + 2" + cal.plus(1, 2));
		System.out.println("2 - 1" + cal.minus(2, 1));
		System.out.println("1 * 2" + cal.multi(1, 2));
		System.out.println("10 / 2" + cal.plus(10, 2));
		
		//Scanner : 자원을 프로그램 으로 받아오는 역할.
		Scanner scanner = new Scanner(System.in); //ctrl+shift +o
		System.out.println("덧셈을 합니다. \n숫자1을 입력해주세요. ");
		int num1 = scanner.nextInt();
		System.out.println("숫자2를 입력해주세요.");
		int num2 = scanner.nextInt();
		
		System.out.println(num1 + "+" + num2 + "=" + cal.plus(num1, num2));
		
		System.out.println("----------------------");
		System.out.println("반지름을 입력해주세요.");
		float r = scanner.nextFloat();
		
		System.out.println("입력 받은 반지름의 길이: " + r);
		System.out.println("원의 둘레: " + cal.circum(r));
		System.out.println("원의 넓이: " + cal.cirArea(r));
		
	}

}
