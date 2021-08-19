package com.bitcamp.firstSpring;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CookieController {
	// 쿠키 생성해주는 요청 처리
	
	@RequestMapping(value="/cookie/make")
	 public String makeCookie(HttpServletResponse response) {
		
		Cookie c = new Cookie("userName", "KING");
		response.addCookie(c);
		return "cookie/make";	// make = make.jsp 
	 
		// Service.res(response)  특정 서비스.특정메소드()
		
	}
	
	@RequestMapping("/cookie/view")
	public String viewCookie(
			
			//@CookieValue(value = "usernames", required = true) String userName,
			@CookieValue(value = "usernames", defaultValue = "SCOTT") String userName,
			Model model
			) {
		
		
		
		model.addAttribute("userName", userName);
		System.out.println(userName);
		// ....Cookie 	정보를 이용해서정보 처리
			return "cookie/view";
			
	}
		
		
	

	
}
