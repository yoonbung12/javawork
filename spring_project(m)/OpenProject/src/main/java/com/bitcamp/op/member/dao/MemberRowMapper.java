package com.bitcamp.op.member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bitcamp.op.member.domain.Member;

public class MemberRowMapper implements RowMapper<Member> {

	@Override
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
		Member member = new Member();
		member.setIdx(rs.getInt("idx"));
		member.setMemberid(rs.getString("memberid"));
		member.setPassword(rs.getString("password"));
		member.setMembername(rs.getString("membername"));
		member.setRegdate(rs.getTimestamp("regdate"));
		
		return null;
	}

	
	
}
