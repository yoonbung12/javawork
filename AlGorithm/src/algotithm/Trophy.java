package algotithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Trophy {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException {
		//1668번
		//높이가 큰 트로피가 작은 트로피 왼쪽에 진열되어있으면 작은 트로피는 큰트로피에 가려짐
		//트로피는 자기의 앞에 자기보다 높이가 낮은 트로피가 있을때만 보임
		//선반 180도 회전 가능, 보이는 개수 변화 가능(보는 각도의 차이?)
		//첫째 줄에 트로피 개수N(1<=N <= 50)이 주어짐. 둘째 줄부터 N개의 줄에 왼쪽의 트로피부터 
		//차례대로 높이가 주어진다. 트로피의 높이는 100보다 작거나 같은 자연수이다.
		//선반위에 올려져 있는 트로피의 높이가 주어졌을 때,!!! 
		//왼쪽에서 봤을 때 보이는 개수와, 오른쪽에서 봤을 때 보이는 개수!!!!
	
		
//		//배열 트로피의 개수 = N
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); //트로피 개수
		int[] trophies = new int[N];
		
		for(int i=0; i<trophies.length; i++) {
			trophies[i] = Integer.parseInt(br.readLine());
		}
		// 맥스값을 초기에 0으로 설정후 다음 인덱스를 하나씩 탐색-> max값보다 크면 결과값++
		int  big = trophies[0];
		int cnt = 1;
		for(int i=1; i<trophies.length; i++) {
			if(big<trophies[i]) { //앞의트로피보다 높이가 큰 트로피가 나오면 cnt++
				cnt++;
				big = trophies[i];
			}
		}
		System.out.println(cnt);
		
		//반대방향 확인할 차례(배열의 순서를 마지막 순서부터 탐색하도록)
		big = trophies[trophies.length-1]; 
		//원본 배열의trophies의 마지막 인덱스(int i=trophies.length-1)부터 탐색
		int cnt2 =1;
		for(int i=trophies.length-1; i>=0; i--) {
			if(big <trophies[i]) {
				cnt2++;
				big = trophies[i];
			}
		}
		System.out.println(cnt2);
	}

}
