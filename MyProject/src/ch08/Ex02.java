package ch08;

public class Ex02 {
	public static void addOneDArr(int[] arr, int add) {
		for(int i = 0; i < arr.length; i++) {
			arr[i] += add; 
		}
	}
	public static void addTwoDArr(int[][] arr, int add) {
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j< arr.length; j++) {
				arr[i][j] += add;
				System.out.print(arr[i][j] +add);
			}
		}
	}
	public static void main(String[] args) {
		//초기화
		int add = 1;
		int[][] arr = {
				{3,6,4,3,6,},
				{2,5,3,2,1,},
				{14,14,6,3,7},
				{13,16,21,46,5}
		};
		//기본 출력
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + "\t"); //열 출력
			}
			System.out.println(); //행출력
		}
		System.out.println("=================");
		//증가 출력
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				arr[i][j] += add;
				
				System.out.print(arr[i][j] + "\t");
			}
			System.out.println();
		}
		

	}

}
