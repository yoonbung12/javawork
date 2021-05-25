package ch01;

public class Calculator {
	static void add(int a, int b) {
		System.out.println(a + b);
	}
	static void minus(int a, int b) {
		System.out.println(a - b);
	}
	static void multi(int a, int b) {
		System.out.println(a * b);
	}
	static void divide(int a, int b) {
		System.out.println(a / b);
	}
	static int r = 10;
	static double PI = 3.1459;
	static void round(int a, double PI , int r) {
		System.out.println(a * PI * r);
	}
	static int r1 = 10;
	static double PI1 = 3.1459;
	static void extent(double PI1, int r1, int r) {
		System.out.println(PI1 * r * r1);
	}
	
	public static void main(String[] args) {
		Calculator cal = new Calculator();
		cal.add(2, 2);
		cal.minus(10, 2);
		cal.multi(10, 2);
		cal.divide(10, 2);
		cal.round(2, PI, r);
		cal.extent(PI1, r, r1);
	}

}
