package com.bitcamp.mm.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitcamp.mm.dao.MemberDao;
import com.bitcamp.mm.jdbc.JdbcUtil;

@Service
public class DelService {

	@Autowired
	MemberDao dao;
	
	public void delMember(HttpServletRequest request, String id) {
		Connection conn = null;
		
		int resultCnt = 0;
		
			try {
				conn = ConnectionProvider.getConnection();
				
				resultCnt = dao.deleteMemberById(conn, id);
	
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				JdbcUtil.close(conn);
			}
			request.setAttribute("result", resultCnt);
	}
}
