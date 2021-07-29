package service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class DateCommandImpl implements Command {

	@Override
	public String getPage(HttpServletRequest request) {
		
		//..... 핵심처리
		
		request.setAttribute("result", new Date());
		
		return "/WEB-INF/views/date.jsp";
	}

}
