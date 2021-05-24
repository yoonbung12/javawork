package ch01;

public class Calculator {
	//메소드 -> 기능(재료-> 처리-> 반환)
	//반환타입 메소드이름(매겨변수들)	 {처리구문}
	void plus(int a, int b) {
		System.out.println(a + b);
	}
	void minus(int a, int b) {
		System.out.println(a - b);
	}
	void multi(int a, int b) {
		System.out.println(a * b);
	}
	void div(int a, int b) {
		System.out.println(a / b);
	}
	
	public static void main(String[] args) {
		//인스턴스 생성 -> 클래스의 정의가 필요(필요한 변수, 필요한 메서드)
		//클래스이름 참조변수 = new 클래스이름
		Calculator cal = new Calculator();
		
		cal.plus(10, 2);
		cal.minus(10, 2);
		cal.multi(10, 2);
		cal.div(10, 2);

	}

}
