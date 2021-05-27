package ch08;

public class Ex03 {
	
	public static void main(String[] args) {
		int[][] arr = {	//3행 3열
				{1, 2, 3},
				{4, 5, 6},
				{7, 8, 9}
		};
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("========================");	
		for(int i = arr.length -1; i > 0; i--) {
			int[] temp = arr[i];
			arr[i] = arr[i -1];
			arr[i -1] = temp;
		}
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + "\t");
			}
			System.out.println();
		}
	}
		

}
