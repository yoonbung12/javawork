package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCheckFilter implements Filter {

	@Override
	public void destroy() {
		

	}

	@Override
	public void doFilter(
			ServletRequest request,
			ServletResponse response,
			FilterChain chain)
			throws IOException, ServletException {
		
		// 요청의 필터 처리 : request
		// 회원의 로그인 여부 확인!!! : session의 속성에 회원의 로그인 정보를 가지고 있는지 확인
		
		// 1. Session 객체를 구한다. request.getSession(false)
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpSession session = httpRequest.getSession(false);
		// getSession(true) : 무조건 새로운 세션 객체를 반환
		// getSession(false) : 세션이 객체가 없다면  null 반환
		// getSession()	: 세션이 존재하면 현재 세션을 반환, 세션 객체가 없다면 새로운 세션을 생성해서 반환
		
		// 세션객체에 userName 속성이 있다면 원래 요청 처리를 진행
		// 없다면 로그인폼으로 이동
		if(session != null && session.getAttribute("userName") != null	) {
			// 로그인 상태!!!
			chain.doFilter(request, response); 
			// 다음 필터를 실행, 현재 필터가 마지막 필터이면 실제 요청을 처리
		} else {
			
			String viewPage = "/member/loginForm.jsp";
			
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher(viewPage); 
			dispatcher.forward(request, response);
		}
		
		
		// 필터를 이용한 응답 데이터 처리: response

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
