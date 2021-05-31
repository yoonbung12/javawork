package ch10;

//Calculator 인터페이스를 상속하는 추상 클래스를 정의해봅시다.
public abstract class CalculatorAbs implements Calculator{
	public long add(long n1, long n2) {
		return (n1 +  n2);
	}
	public long substract(long n1, long n2) {
		return(n1 - n2);
	}
	public long multiply(long n1, long n2) {
		return(n1 * n2);
	}
	public double divide(double n1, double n2) {
		return(n1 / n2);
	}
}
