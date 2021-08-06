package com.bitcamp.firstSpring.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bitcamp.firstSpring.member.domain.MemberRegRequest;

// 1. Controller 에서 get/post 구분하기
// 2. post에서 데이터 받는 코드 작성(3가지 방법)
// 3. view로 데이터 전달

@Controller
@RequestMapping("/member/memberReg")
public class MemberRegController {

	@RequestMapping(method = RequestMethod.GET)
	public String getRegForm() {
		return "member/memberRegForm";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String memeberReg(
			
			@RequestParam(value = "memberid", required = false) String memberid,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "membername", required = false) String membername,
			HttpServletRequest request,
			@ModelAttribute("regRequest") MemberRegRequest regRequest,
			Model model
			
			
			)	{
		
		model.addAttribute("memberid", memberid);
		model.addAttribute("password", password);
		model.addAttribute("membername", membername);
	
		model.addAttribute("memberName", request.getParameter("membername"));
		model.addAttribute("memberPassword", request.getParameter("password"));
		model.addAttribute("memberId", request.getParameter("memberid"));
		
		// 회원가입을 위한 Service 객체를 이용한 핵심 처리....
		
		return "member/memberReg";
		
	}
	
}
