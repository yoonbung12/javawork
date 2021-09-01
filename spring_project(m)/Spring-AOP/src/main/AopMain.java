package main;

import aop.CalculatorImpl;
import aop.ExeTimeCalculator;
import aop.RecCalculator;

public class AopMain {

	public static void main(String[] args) {
		RecCalculator recCalcurator = new RecCalculator();
		CalculatorImpl calcuratorImp = new CalculatorImpl();

		System.out.println("플록시를 이용한 처리");

		System.out.println("======================");

		System.out.println("1. factorial(100) 결과 : " + new ExeTimeCalculator(recCalcurator).factorial(10));

		System.out.println("======================");

		System.out.println("2. factorial(100) 결과 : " + new ExeTimeCalculator(calcuratorImp).factorial(10));

		/*
		 * long resultTime1 = new RecCalcurator().factorial(100);
		 * System.out.println("factorial(100) 결과 : " + resultTime1);
		 * 
		 * System.out.println("===============================");
		 * 
		 * long resultTime2 = new CalcuratorImp().factorial(100);
		 * System.out.println("factorial(100) 결과 : " + resultTime2);
		 */

	}

}
