package com.bitcamp.mm.service;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
	
	@Autowired
	MemberDao dao;
	
	public void loginMember(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		boolean isLogin = false;
		
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		String rememId = request.getParameter("rememId");
		
		Connection conn = null;
		
		if(userId != null && userPw != null && userId.trim().length() > 2 && userPw.trim().length() > 2) {
			try {
					conn = ConnectionProvider.getConnection();
					
					Member member = dao.getMemberByIdPw(conn, userId, userPw);
					
					if(member != null) {
						session.setAttribute("loginInfo", member.toLoginInfo());
						isLogin = true;
					}
					
					if(rememId != null && rememId.equals("on")) {
						Cookie cookie = new Cookie("remreId", URLEncoder.encode(userId, "utr-8"));
						cookie.setMaxAge(60*60*24*365);
						response.addCookie(cookie);
					} else {
						Cookie cookie = new Cookie("remreId", URLEncoder.encode(userId, "utf-8"));
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}
				
			} catch(SQLException e) {
				e.printStackTrace();
			} catch(UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		session.setAttribute("isLogin", isLogin);
	}
}
