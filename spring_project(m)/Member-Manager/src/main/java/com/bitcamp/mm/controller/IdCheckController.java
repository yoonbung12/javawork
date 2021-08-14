package com.bitcamp.mm.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitcamp.mm.service.IdCheckService;

@Controller
public class IdCheckController {

	@Autowired
	IdCheckService idCheckService;
	
	@RequestMapping(value = "/member/idCheck", method = RequestMethod.POST)
	@ResponseBody
	public String dispatchIdCheck(
			@RequestParam Map<String, Object> param,
			HttpServletRequest request) {
		String mid = (String)param.get("mid");
		
		return idCheckService.idCheck(mid);
		
	}
	
}
