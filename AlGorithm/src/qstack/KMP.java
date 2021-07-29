package qstack;

import java.util.Scanner;

public class KMP {

	public static void main(String[] args) {
		// 첫번째는 성을 모두 쓰고, 이를 하이픈(-)으로 이어 붙인 것이다. 예를 들면,Knuth-Morris-Pratt이다. 이것을 긴 형태라고 부른다.
		// 두 번째로 짧은 형태는 만든 사람의 성의 첫 글자만 따서 부르는 것이다. 예를 들면 KMP이다.
		// 긴 형태의 알고리즘 이름이 주어졌을때, 이를 짧은 형태로 바꾸어 출력하는 프로그램 출력
		//알파벳 대,소 문자 그리고 하이픈('-')으로 구성, 첫글자는 무조건 대,하이픈 뒤도 무조건 대
		
		// 1. 글자를 입력 받아야 하므로 Scanner 사용
		// 2.글자를 짤라내야 하므로 split 활용 해야함.
		
		Scanner sc = new Scanner(System.in);
		
		String[] lName = sc.next().split("-"); // "-" 이부분을 잘라내야함
		sc.close();
		String answer = "";
		
		
		for(String name : lName) {
			// System.out.println(name); 이렇게 하면 "-" 이부분을 제외한 나머지 남는다. 차례대로호출
			answer += String.valueOf(name.charAt(0)); //인덱스의 첫번째 것을 뽑아내기 위해 CharAt(0)사용.
			  
		}
			System.out.println(answer);
		
	}

}
