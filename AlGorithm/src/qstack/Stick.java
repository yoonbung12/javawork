package qstack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Stick {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 17608번 막대기
		// 높이만 다르고 모양이 같은 막대기를 일렬로 세움(배열)생각할것
		// 왼쪽부터 차례로 번호 붙임
		// 보는 방향은 오른쪽(작은 막대기가 큰막대기 뒤에 있으면 보이지 않음)
		
		// N개의 막대기에 대한 높이 정보가 주어질 때, 오른쪽에서 보았을때 몇개가 보이는지 알아내소.
		
		// 배열의 막대기 개수: N개
		
		
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
		
		int N =  Integer.parseInt(br.readLine()); // Stick 개수
		// 배열 사용
		int[] sticks = new int[N];
		
		// 왼쪽에서 봐라밨을때
		for(int i=0; i<sticks.length; i++) {
			sticks[i] = Integer.parseInt(br.readLine());
		}
		
		// 맥스값을 초기에 0으로 설정 다음 인덱스부터 하나씩 탐색 -> max값보다 크면 결과값은 ++
		int big = sticks[0];
		int cnt = 1; // 처음 보자마자 하나 보이니까 1로 설정
		for(int i=1; i<sticks.length; i++) {
			if(big < sticks[i]) { // 앞의 막대기보다 큰 막대기가 나오면cnt++
				cnt++;
				big = sticks[i];
			}
		}
		System.out.println(cnt); // 왼쪽에서 봐라밨을때
		
		// 오른쪽에서 봐라봤을때(배열의 순서를 마지막 순서부터 탐색하도록)
		big = sticks[sticks.length-1];
		int cnt2 = 1;
		for(int i=sticks.length-1; i>=0; i--) {
			if(big < sticks[i]) {
				cnt2++;
				big=sticks[i];
			}
		}
		System.out.println(cnt2);
	}

}
