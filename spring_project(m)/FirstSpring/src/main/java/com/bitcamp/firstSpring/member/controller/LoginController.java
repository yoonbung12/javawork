package com.bitcamp.firstSpring.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bitcamp.firstSpring.member.domain.LoginRequest;

@Controller
@RequestMapping("/member/login")	// 브라우저서 사용하는 URL http://localhost:8080/mvc/member/login
public class LoginController {

	//@RequestMapping("/member/loginform")
	//@RequestMapping(value = "/member/login", method = RequestMethod.GET)
	@RequestMapping(method = RequestMethod.GET)
	public String getLoginForm(
			
			@RequestParam(value = "page", defaultValue = "1") int page
			
			
			
			) {
		//System.out.println(page+1);
		System.out.println(page);
		return "member/loginForm";	// 	/WEB-INF/views/member/loginForm.jsp
		
	}
	
	//	/member/login/member/login
	//@RequestMapping(value="/member/login", method = RequestMethod.POST)
	@RequestMapping(method = RequestMethod.POST)
	public String login(
			
			@RequestParam("id") String id,
			@RequestParam("pw") String pw,
			HttpServletRequest request,
			@ModelAttribute("loginReq") LoginRequest loginRequest,
			Model model
			
			
			) {
		
		// 사용자의 파라미터 데이터를 받는 방법
		// 1. HttpServletRequest 이용
		// 2. @RequestParam("param name")
		// 3. 커멘드 객체(Beans) 이용
		
		
		System.out.println(id + " : " + pw);
		
		// view에 전달할 데이터 저장 : Model 객체를 이용
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		
		
		
		String uid = request.getParameter("id");
		String upw = request.getParameter("pw");
		
		System.out.println(uid + " : " + upw);
		
		model.addAttribute("uid", uid);
		model.addAttribute("upw", upw);
		
		System.out.println(loginRequest);
		
		return "member/login"; // 	/WEB-INF/views/member/login.jsp
	}
	
}
