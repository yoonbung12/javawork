package com.bitcamp.mm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {

	@RequestMapping("member/logout")
	public String dispatchLogout() {
		return "member/logout";
	}
}
