package com.bitcamp.mm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bitcamp.mm.service.LoginService;

@Controller
@RequestMapping("/member/login")
public class LoginController {

	
	@Autowired
	LoginService loginService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String dispatchLoginForm(
			@CookieValue(value = "remreId", required = false) String rememId,
			Model model) {
		model.addAttribute("rememId", rememId);
		return "member/loginForm";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String dispatchLoginView(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
			loginService.loginMember(request, response, session);
			
			return "member/loginView";
	}

}
