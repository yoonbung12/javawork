package com.bitcamp.mm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bitcamp.mm.service.RegService;

@Controller
@RequestMapping("/member/reg")
public class RegController {

	@Autowired
	RegService regService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String dispatchRegForm() {
		return "/member/regForm";
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String dispatchRegView(HttpServletRequest request) {
		try {
				regService.regMember(request);
				
		} catch(FileUploadException e) {
			e.printStackTrace();
			request.setAttribute("result", 0);
		}
		return "/member/regView";
		
	}
}
