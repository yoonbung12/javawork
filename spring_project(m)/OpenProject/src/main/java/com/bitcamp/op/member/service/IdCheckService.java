package com.bitcamp.op.member.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitcamp.op.jdbc.ConnectionProvider;
import com.bitcamp.op.member.dao.JdbcTemplateMemberDao;
import com.bitcamp.op.member.dao.MemberDao;

@Service
public class IdCheckService {

//	@Autowired
//	MemberDao dao;
	
	private JdbcTemplateMemberDao dao;
	
	public String idCheck(String id) {
		
		String result = "Y";
		//Connection conn = null;
		
			try {
				// conn = ConnectionProvider.getConnection();
				if(dao.selectById(id) >0) {
					result="N";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		return result;
	}
}
