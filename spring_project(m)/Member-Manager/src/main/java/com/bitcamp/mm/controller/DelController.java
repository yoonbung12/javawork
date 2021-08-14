package com.bitcamp.mm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bitcamp.mm.service.DelService;

@Controller
public class DelController {

	@Autowired
	DelService delService;
	
	@RequestMapping(value = "/member/del/{id}")
	public String dispatchDelView(HttpServletRequest request, @PathVariable String id) {
			delService.delMember(request, id);
			
			return "member/delView";
	}
	
}
