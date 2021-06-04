package ch13;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlTest {

	public static void main(String[] args) {
		
		String urlStr = "https://sports.news.naver.com/news.nhn?oid=410&aid=0000791096";
		
		try {
			URL url = new URL(urlStr);
			
			System.out.println("Protocol : " + url.getProtocol());
			System.out.println("Host Name : " + url.getHost());
			System.out.println("Port Num : " + url.getPort());
			System.out.println("Default Port Num : " + url.getDefaultPort());
			System.out.println("Query : " + url.getQuery());
			System.out.println("Path : " + url.getPath());
			System.out.println("File : " + url.getFile());
			
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
