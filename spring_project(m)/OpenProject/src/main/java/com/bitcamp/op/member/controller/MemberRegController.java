package com.bitcamp.op.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bitcamp.op.member.domain.MemberRegRequest;

@Controller
@RequestMapping("/member/memberReg")
public class MemberRegController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String regForm() {
		return "member/regForm";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String reg(
			
			@ModelAttribute("regReqeust")MemberRegRequest regRequest
			) {
		System.out.println(regRequest);
		
		return "member/reg";
	}
	
}
