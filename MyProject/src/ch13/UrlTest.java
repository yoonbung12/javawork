package ch13;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlTest {

	public static void main(String[] args) {
		
		String urlStr = "https://section.blog.naver.com/BlogHome.naver?directoryNo=0&currentPage=1&groupId=0";
		
		try {
			URL url = new URL(urlStr); //규격에 맞는 주소가 맞는지 확인해야함.Url만들어 놓으면 안전하게 할수 있음.
			
			System.out.println("Protocol : " + url.getProtocol());
			System.out.println("Host Name : " + url.getHost());
			System.out.println("Port Num : " + url.getPort());
			System.out.println("Default Port Num : " + url.getDefaultPort());
			System.out.println("Query : " + url.getQuery()); //?뒤쪽으로 오는 애들이 파라미터 또는 쿼리라고함.
			System.out.println("Path : " + url.getPath());
			System.out.println("File : " + url.getFile());
			
			
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
