package com.bitcamp.mm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyPageController {

	@RequestMapping("member/mypage")
	public String dispatchMyPage(HttpSession session) {
		
		if(session.getAttribute("isLogin") == null) {
			return "member/loginForm";
			
		}
		return "member/myPage";
	}
}
