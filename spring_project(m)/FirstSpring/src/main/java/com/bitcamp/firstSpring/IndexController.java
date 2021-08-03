package com.bitcamp.firstSpring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String index() {
		return "index";		// /WEB_INF/views/index.jsp
	}
}
