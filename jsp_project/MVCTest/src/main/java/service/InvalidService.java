package service;

import javax.servlet.http.HttpServletRequest;

public class InvalidService {

	public String getPage(HttpServletRequest request) {
		
		// ....핵심처리
		
		return "/WEB-INF/views/default.jsp";
		
	}
}
