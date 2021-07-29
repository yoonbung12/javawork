package service;

import javax.servlet.http.HttpServletRequest;

public class InvalidCommandImpl implements Command {

	@Override
	public String getPage(HttpServletRequest request) {
		
		//..... 핵심처리
		
		return "/WEB-INF/views/default.jsp";

		
	}

}
