package com.bitcamp.firstSpring.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberRegController {

	@RequestMapping("/member/regform")
	public String getRegForm() {
		return "member/memberRegForm";
	}
	
}
