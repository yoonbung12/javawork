package algotithm;

import java.util.Scanner;

public class BlackJack {

	public static void main(String[] args) {
		// 완전탐색 알고리즘 문제
		// 1. 블랙잭
		// 첫째 줄에 카드의 개수 N(3 ≤ N ≤ 100)과 M(10 ≤ M ≤ 300,000)이 주어진다. 
		// 둘째 줄에는 카드에 쓰여 있는 수가 주어지며,
		// 이 값은 100,000을 넘지 않는 양의 정수이다.
		// 합이 M을 넘지 않는 카드 3장을 찾을 수 있는 경우만 입력으로 주어진다.
		
		Scanner sc = new Scanner(System.in);
		
		// N값과 M값 입력
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int[] arr = new int[N];
		
		for(int i =0; i< N; i++) {
			arr[i] = sc.nextInt();
		}
		
		int result = search(arr, N, M);
		System.out.println(result);

	}
	// 탐색
	static int search(int[] arr, int N, int M) {
		int result = 0;
		
		// 3개의 카드를 뽑아야 하므로 3중for문 사용
		// 첫 번째 카드는 N-2
		for(int i = 0; i< N-2; i++) {
			// 두번째 카드는 N-1
			for(int j = i+1; j< N-1; j++) {
				// 세번째 카드는 N
				for(int k = j+1; k< N; k++ ) {
					
					// 3개 카드의 합 = A
					int A = arr[i]+ arr[j] + arr[k];
					
					//확인을 if문 사용
					if(M == A) {
						return A;
					} 
					if(result < A && A < M) {
						result = A;
					}
					
				}
			}
		}
		return result;
		
	}

}
