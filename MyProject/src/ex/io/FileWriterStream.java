package ex.io;

import java.io.*;

public class FileWriterStream {

	public static void main(String[] args) {
		
		//문자 단위 저장을 위한 출력 스트림
		//Writer -> FileWriter : 파일에 문자 쓰기
		
		try {
			
			System.out.println("문자 스트림 생성");
			
			Writer out = new FileWriter("hyper.txt");
			
			char c1 = 'A';
			char c2 = 'B';
			String str = "CD";
			
			//쓰기
			out.write(c1);
			out.write(c2);
			out.write(str);
			out.write('E');
			out.write("FG");
			
			out.close();
			System.out.println("글쓰기 완성");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
