package com.bitcamp.op.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bitcamp.op.member.domain.MemberRegRequest;
import com.bitcamp.op.member.service.MemberRegService;

@Controller
@RequestMapping("/member/memberReg")
public class MemberRegController {
	
	@Autowired
	private MemberRegService regService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String regForm() {
		return "member/regForm";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String reg(
			
			@ModelAttribute("regReqeust")MemberRegRequest regRequest,
			HttpServletRequest request,
			Model model
			) {
//		System.out.println(regRequest);
		
		int result = regService.memberReg(regRequest, request);
		
		model.addAttribute("result", result);
		
		String view = "member/reg";
		
		if(result == 1) {
			// 인덱스 페이지로 리다이렉트
			view = "redirect:/index";
		}
		
		return view;
	}
	
}
