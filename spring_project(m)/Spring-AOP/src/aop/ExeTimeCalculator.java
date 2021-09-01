package aop;

//플록시 구현
public class ExeTimeCalculator implements Calculator {
	private Calculator delegate;

	public ExeTimeCalculator(Calculator delegate) {
		this.delegate = delegate;
	}

	@Override
	public long factorial(long num) {
		//가상머신의 기준 시점에서 경과 시간을 측정하는데 사용(ns 단위)
		//시스템 시간과 무관
		long start = System.nanoTime();
		long result = delegate.factorial(num);
		long end = System.nanoTime();
		System.out.println("factorial(" + num + ") 실행시간 :" + (end - start));
		return result;
	}
}
