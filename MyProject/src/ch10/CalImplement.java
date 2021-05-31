package ch10;

public class CalImplement  implements Calculator{
	public long add(long n1, long n2) {
		return n1 + n2;
	}

	public long substract(long n1, long n2) {
		
		return n1 - n2;
	}

	
	public long multiply(long n1, long n2) {
		
		return n1 * n2;
	}

	
	public double divide(double n1, long n2) {
		
		return n1 / n2;
	}
	
}
