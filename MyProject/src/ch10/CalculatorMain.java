package ch10; 

public class CalculatorMain {

	public static void main(String[] args) {
		Calculator cal = new CalImplement();
		int a = 10;
		int b = 5;
		System.out.println(cal.add(a, b));
		System.out.println(cal.substract(a, b));
		System.out.println(cal.multiply(a, b));
		System.out.println(cal.divide(a, b));
	}

}
