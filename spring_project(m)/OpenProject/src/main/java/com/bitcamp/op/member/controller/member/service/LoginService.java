package com.bitcamp.op.member.controller.member.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitcamp.op.jdbc.ConnectionProvider;
import com.bitcamp.op.member.controller.member.dao.MemberDao;
import com.bitcamp.op.member.domain.Member;

@Service
public class LoginService {

	@Autowired
	MemberDao dao;
	
	public boolean login(
			String id,
			String pw,
			HttpSession session,
			HttpServlet response) {
		
		boolean loginChk = false;
		
		
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			
			// 전달받은 id와 pw 로 DB에서 검색
			// => 있다면 로그인 처리 true return ,
			// =>없다면 false return
			
			Member member = dao.selectByIdPw(conn, id, pw);
			
			if(member != null) {
				// 로그인 처리
				session.setAttribute("loginInfo", member.toLoginInfo());
				
				loginChk = true;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		// 아이디 저장을 위한 Cookie 설정
		if(reid != null && reid.length() > 0) {
			Cookie cookie = new Cookie("reid", reid);
			cookie.setPath("/");
			cookie.setMaxAge(60 * 60 * 24* 365);
			
			response.addCookie(cookie);
		} else {
			Cookie cookie = new Cookie("reid", reid);
			cookie.setPath("/");
			cookie.setMaxAge(0);
			
			response.addCookie(cookie);
		}
		
	
		
		
		return loginChk;
	}
}
