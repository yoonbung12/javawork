package com.bitcamp.op.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthCheckInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(
			HttpServletRequest request,
			HttpServletResponse response,
			Object handler)
			throws Exception {
		
		System.out.println("AuthCheckInterceptor preHandle() 진입");
		
		// 1. 로그인 여부 확인
		// 2. 로그인 상태에 따라 true / false 로 return
		// 3. 로그인 상태가 아니라면 로그인 페이지로 redirect
		
		// 로그인 상태 확인 
		HttpSession session = request.getSession(false);
		
		if(session != null && session.getAttribute("loginInfo") != null) {
			return true;
		}
		
		// header의 referer 없이 이전 페이지로 이동하도록 처리
		// parameter 로 이전 uri 전
		response.sendRedirect(request.getContextPath() + "/member/login?referer=" +request.getRequestURI());
		return false;
		
	}

}
