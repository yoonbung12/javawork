package com.bitcamp.app.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bitcamp.app.member.service.LoginService;


@Controller
@RequestMapping("/member/login")
public class LoginController {
	
	@Autowired()
	private LoginService loginService;
	
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String loginForm(
			@RequestHeader(value="referer", required=false) String redirectUri,
			Model model
			
			) {
		
		model.addAttribute("redirectUri", redirectUri);
		return "member/loginForm";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String login(
			
			@RequestParam("memberid") String memberid,
			@RequestParam("password") String password,
			@RequestParam(value = "redirectUri", required = false) String redirectUri,
			@RequestParam(value = "reid", required=false) String reid,			
			HttpSession session,
			HttpServletResponse response,
			HttpServletRequest request,
			Model model
			) {
		
		// 사용자가 입력한 id, pw 서비스에 전달해서 로그인 처리
		boolean loginChk = loginService.login(memberid, password, reid, session, response);
		model.addAttribute("loginChk", loginChk);
		
		String view = "member/login";
		
		if(chkURI(redirectUri) && loginChk) {
			
			redirectUri = redirectUri.substring(request.getContextPath().length());
			view = "redirect:" + redirectUri;
		}
		
		
		return view;
	}
	
	private boolean chkURI(String uri) {
		boolean chk = true;
		
		if(!uri.startsWith("/op/")) {
			chk = false;
		}
		
		return chk;
	}
	
}
