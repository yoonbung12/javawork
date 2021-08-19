package com.bitcamp.firstSpring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rest/simple")
public class RestTestController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String getForm() {
		return "rest/form";
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public String simple(
			@RequestBody String body 
			) {
		
		// name=cool&age=11
		// JSON 데이터를 받을 때 이용
		// GSON 라이브러리 : JSON -> Java Object
		
		System.out.println("body : " + body);
		
		return body;
		//return "@ResponseBody 를 이용한 응답";
	}

	
}
