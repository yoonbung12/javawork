package ch13;

import java.io.IOException;
import java.net.ServerSocket;

public class Util {
	
	public static void close(ServerSocket ss) {
		
		if(ss != null) {
			try {
				ss.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}



