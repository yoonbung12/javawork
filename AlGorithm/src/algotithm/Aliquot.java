package algotithm;

import java.util.Scanner;

public class Aliquot {

	public static void main(String[] args) {
		// 두개의 자연수 N과 K가 주어졌을때, N의 약수들 중 K번째로 작은 수를 출력하는 프로그램을 작성해라
		// 첫째 줄에 N과 K가 빈칸을 사이에 두고 주어진다. 
		//N은 1 이상 10,000 이하이다. K는 1 이상 N 이하이다.(1<= N <= 10,000), (1<=k <=N)
		Scanner sc = new Scanner(System.in);
		
		// N과 M값 입력
		int N = sc.nextInt();
		int K = sc.nextInt();
	
		// 배열의 시작은 0일수 있기때문에 N+1을 사용
		int[] arr = new int[N+1];
		
		for(int i = 1; i <= N; i++) {
			if(N % i == 0) { //1부터 N까지의 약수를 모두 찾는다
				arr[i] = i;
				System.out.println(K);
			}
		}
		
//		System.out.println(arr[K]);
		
	}

}
