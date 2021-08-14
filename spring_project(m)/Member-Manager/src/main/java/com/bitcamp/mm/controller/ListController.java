package com.bitcamp.mm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bitcamp.mm.service.ListService;

@Controller
public class ListController {
	
	@Autowired
	ListService listService;
	
	@RequestMapping("/member/list")
	public String dispatchListView(HttpServletRequest request) {
		listService.listMember(request);
		
		return "/member/listView";
	}
}
