package service;

import javax.servlet.http.HttpServletRequest;

public class LoginCommandImpl implements Command {

	@Override
	public String getPage(HttpServletRequest request) {
		
		request.setAttribute("result", "로그인 페이지 입니다.");
		
		return "/WEB-INF/views/login.jsp";
	}

}
