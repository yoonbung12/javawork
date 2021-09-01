package main;

import org.springframework.context.support.GenericXmlApplicationContext;

import aop.CalculatorImpl;
import aop.RecCalculator;

public class SpringAopMain {
	public static void main(String[] args) {

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:config/aopConfig.xml");

		RecCalculator recCalcurator = ctx.getBean("recCal", RecCalculator.class);

		long calResult1 = recCalcurator.factorial(10);
		System.out.println("factorial(10) 의 결과 : " + calResult1);

		System.out.println("====================");

		CalculatorImpl calcuratorImp = ctx.getBean("calImpl", CalculatorImpl.class);

		long calResult2 = calcuratorImp.factorial(10);
		System.out.println("factorial(10) 의 결과 : " + calResult2);

	}
}
