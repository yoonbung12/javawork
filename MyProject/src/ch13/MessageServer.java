package ch13;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MessageServer {

	public static void main(String[] args) {
		
		ServerSocket ss = null;
		Socket s = null;
		
		DataInputStream din = null;
		DataOutputStream dout = null;
		
		
		try {
			
			
			
			
			Scanner sc = new Scanner(System.in);
			
			String str1 = null;  //받는 메시지
			String str2 = null;  //보내는 메시지
			
			while(!str2.equals("exit")) {
			str1 = din.readUTF();
			System.out.println(str1);
			
			str2 = sc.nextLine();
			dout.writeUTF(str2);
			dout.flush();
			
			}
			din.close();
			dout.close();
			s.close();
			ss.close();
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}

	}

}
