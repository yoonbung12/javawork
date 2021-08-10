package com.bitcamp.mm.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IdCheckService {

	@Autowired
	MemberDao dao;
	
	public String idCheck(String memberId) {
		String result = "N";
		
		Connection conn = null;
		
		try {
				conn = ConnectionProvider.getConnection();
				
				result = dao.getMemberById(conn, memberId)>0 ? "N" : "Y";
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		
		return result;
	}
}
