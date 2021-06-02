package ex.io; //writer, reader 다시 한번 해봐야함.

import java.io.*;

public class StringReader {

	public static void main(String[] args) {
		
		try {
			
			BufferedReader in = new BufferedReader(new FileReader("String.txt"));
			
			// 임시 변수
			String str = null;
			
			while(true) {
				
				try {
					str = in.readLine();
					
					if(str == null) {
						break;
					}
					System.out.println(str);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
