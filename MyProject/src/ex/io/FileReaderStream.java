package ex.io;

import java.io.*;

public class FileReaderStream {

	public static void main(String[] args) {
		
		//문자 단위로 읽어올 스트림 생성
		//Reader -> FileReader
		try {
			Reader in = new FileReader("hyper.txt");
			
			char[] cbuf = new char[10];
			int readLen = 0; //초기화
			
			
			
			
			
			try {
				readLen = in.read(cbuf, 0, cbuf.length);
				
//			for(int i = 0; i < readLen; i++) {
//					System.out.println(cbuf[i]);
//				}
				//System.out.println(new String(cbuf)); //toString()
				System.out.println(cbuf);
				
				
				in.close();
				System.out.println("읽기 완료");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		

	}

}
