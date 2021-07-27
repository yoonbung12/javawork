package util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieBox {

		
		// reqiest 객체를 이용해서 모든 Cookie객체를 Map에 저장
		// Map<k,v> -> (쿠키이름, 쿠키객체)
		private Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		
		// 생성자를 이용해서 Map저장할 데이터 초기화
		public CookieBox(HttpServletRequest request) {
				Cookie[] cookies = request.getCookies();
				if(cookies != null && cookies.length> 0) {
					for(int i=0; i<cookies.length; i++) {
						// cookieMap에 쿠키 정보를 저장
						cookieMap.put(cookies[i].getName(), cookies[i]);
					}
				}
		}
		
		// 객체생성 -> 쿠키 검색(존재유무), 쿠키이름으로 쿠키 객체 반환, 쿠키이름으로 쿠키값을 반환
		
		// 이름으로 쿠키 객체를 반환하는 메소드
		public Cookie getCookie(String name) {
			return cookieMap.get(name);
		}
		
		// 이름으로 쿠키와 값을 반환하는 메소드
		public String getValue(String name) throws UnsupportedEncodingException {
				Cookie c= cookieMap.get(name);
				if(c == null) {
					return null;
				}
				return URLDecoder.decode(c.getValue(), "utf-8");
		}
		
		// 이름으로 쿠키가 존재하는지 확인하는 메소드
		public boolean exists(String name) {
			return cookieMap.get(name) != null;
		}
		
		
		
		
		// Cookie 객체 생성하는 메소드 : 오버로딩, static
		// 쿠키 이름, 쿠키 값
		public static Cookie makeCookie(String name, String value) throws UnsupportedEncodingException {
				Cookie c = new Cookie(name, URLEncoder.encode(value,"utf-8"));
				return c;
		}
		
		// 쿠키 이름, 쿠키 값, 경로, 유지 시간
		public static Cookie makeCookie(String name, String value, String path, int maxAge) throws UnsupportedEncodingException {
				Cookie c = new Cookie(name, URLEncoder.encode(value, "utf-8"));
				c.setPath(path); // 	/
				c.setMaxAge(maxAge);
				return c;
		}
		
		// 쿠키 이름, 쿠키 값, 경로, 유지 시간, 도메인
		public static Cookie makeCookie(String name, String value, String path, int maxAge, String domain) throws UnsupportedEncodingException {
				Cookie c = new Cookie(name, URLEncoder.encode(value, "utf-8"));
				c.setPath(path);
				c.setMaxAge(maxAge);
				c.setDomain(domain);
				return c;
		}
	
}
