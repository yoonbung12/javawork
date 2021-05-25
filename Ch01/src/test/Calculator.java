package test;

import java.util.*;

public class Calculator {
	final double PI = 3.14d;
	long add(int a, int b) {
		return (long)a + b;
	}
	long minus(int a, int b) {
		return(long)a - b;
	}
	long multi(int a, int b) {
		return(long)a * b;
	}
	long divide(int a, int b) {
		return(long) a / b;
	}
	void calcuResert(int a, int b) {
		System.out.println("더하기 연산 =" + add(a ,b));
		System.out.println("빼기 연산 =" + minus(a, b));
		System.out.println("곱하기 연산 =" + multi(a, b));
		System.out.println("나누기 연산 =" + divide(a, b));
	}
	public void roundLength(double r) {
		System.out.println(2 * PI * r);
	}
	public void roundWidth(double r) {
		System.out.println(PI * r * r);
	}
	public static void main(String[] args) {
		Calculator cal = new Calculator();
		Scanner scanner = new Scanner(System.in);
		System.out.println("a의 값을 넣으시오 =" );
		int a = scanner.nextInt();
		System.out.println("b의 값을 넣으시오 =" );
		int b = scanner.nextInt();
		cal.calcuResert(a, b);
		
		System.out.println("반지를 r의 길이를 입력하세요.");
		double r = scanner.nextDouble();
		cal.roundLength(r);
		cal.roundWidth(r);
		

	}

}
