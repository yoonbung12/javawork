package ch13;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLConnectionTest {

	public static void main(String[] args)  {
		
		try {
			URL url = new URL("http://www.google.com"); //www.naver.com
			
			//URLConnection : url.openConnection()
			URLConnection con = url.openConnection();
			
			InputStream in = con.getInputStream();
			
			int i = 0;
			
			while(true) {
				i = in.read();
				if(i == -1) {
					break;
				}
				System.out.print((char)i);
				
			}
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
