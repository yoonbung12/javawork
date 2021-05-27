package ch08;

import java.util.*;

public class Arr {
	
	public static int miniValue(int[] arr) {
		int min = arr[0];
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] < min) {
				min = arr[i];
			}
		}
		return min;
	}
	public static int maxValue(int[] arr) {
		int max = arr[0];
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] > max) {
				max = arr[i];
			}
		}
		return max;
	}
	
	public static void main(String[] args) {
		//배열의 길이는 임의로 결정, 정수는 프로그램 사용자로부터 입력.
		Scanner scanner = new Scanner(System.in);
		
		//배열의 길이 정해주세요.
		System.out.print("배열의 길이를 입력하시오.");
		int num = scanner.nextInt();
		int[] leng = new int[num];
		
		System.out.print("배열을 입력하시오.");
		for(int i = 0; i < leng.length; i++) {
			leng[i] = scanner.nextInt();
		}
		
		System.out.println("최소값은 :" + miniValue(leng));
		System.out.println("최대값은: " + maxValue(leng));
		
	}

}
