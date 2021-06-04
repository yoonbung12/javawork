package ch13;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServerSocket {

	public static void main(String[] args) {
		
		try {
			ServerSocket serverSocket = new ServerSocket(9999);
			
			System.out.println("서버 실행 !");
			
			Socket socket = serverSocket.accept(); //Socket에서 연결 요청이 올때 까지 대기
			
			if(socket != null) {
				System.out.println("클라이언트의 접속 요청 : socket 생성");
			}
			
			DataInputStream din = new DataInputStream(socket.getInputStream());
			
			String msg = din.readUTF();
			System.out.println(msg);
			
			din.close();
			socket.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		

	}

}
