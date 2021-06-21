package test;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		double c = sc.nextDouble();
		
		for(int i = 0; i < c; i++) {
			double a = sc.nextDouble();
			double b = sc.nextDouble();
			
			System.out.println(a+b);
		}
		sc.close();
		
	}

}
// 2739번
////또다른 방법
//Scanner sc = new Scanner(System.in);
//int a1 = sc.nextInt();
//
//sc.close();
//
//for(int i= 1; i<10; i++) {
//	System.out.println(a +" * " +i+ "=" +(a * i));
//}

//int a = 2;
//
//for(int i =1; i<10; i++) {
//	System.out.println(a + " * " +i +"=" + a * i);


//10950
//Scanner sc = new Scanner(System.in);
//int c = sc.nextInt();
//
//
//for(int i = 0; i < c; i++) {
//	int a = sc.nextInt();
//	int b = sc.nextInt();
//	
//	System.out.println(a+b);
//}
//sc.close();

//8393
//Scanner sc = new Scanner(System.in);
//int n = sc.nextInt();
//int sum = 0;
//
//for(int i =1; i <= n; i++ ) {
//	sum += i; //sum = sum + i;
//}
//System.out.println(sum);