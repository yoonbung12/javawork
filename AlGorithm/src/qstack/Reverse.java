package qstack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Reverse {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 12605 단어순서 뒤집기
		// 스페이스로 띄어쓰기 된 단어들의 리스트가 주어질때, 단어들을 반대 순서로 뒤집어라.
		// 각 라인은 W개의 영단어로 이루어져 있으며, 총L 개의  알파벳을 가진다.
		// 각 행은 알파벳과 스페이스로 이루어져 있다.
		// 단어 사이에는 하나의 스페이스만 들어간다.
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 행의 개수 = N
		int N = Integer.parseInt(br.readLine()); 
		Stack<String> stack = new Stack<>(); //
		
		for(int i=0; i<N; i++) {
			String sentence = br.readLine();
			
			String[] sentences = sentence.split(" ");
			
			for(int j=0; j<sentences.length; j++) {
			
				stack.push(sentences[j]);
				
			}
			String result ="";
			for(int j=0; j<sentences.length; j++) {
				result += stack.pop() + " ";
			}
			System.out.println("Case #" +(i+1)+ ":" + result.sub );
		}
		// 아직 미해결 문제
		
	
	}

}
