package com.bitcamp.firstSpring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HeaderController {

	@RequestMapping("/header/header")
	public String getHeader(
			
			@RequestHeader("referer") String reString,
			Model model
			
			) {
		
		System.out.println(reString);
		model.addAttribute("re", reString);
		
		//return "header/headerInfo"; //headerInfo.jsp / 뷰를 만들어야 함.
		return "redirect:" + reString;
		//return "redirect:/member/login";
	
	}
}
