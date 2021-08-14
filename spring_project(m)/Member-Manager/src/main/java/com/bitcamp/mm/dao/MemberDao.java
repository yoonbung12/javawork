package com.bitcamp.mm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bitcamp.mm.dto.Member;
import com.bitcamp.mm.jdbc.JdbcUtil;

@Repository
public class MemberDao {
	public List<Member> getMembers(Connection conn){
		List<Member> members = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "select *from member order by regdate";
		
				try {
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
					
					members = new ArrayList<Member>();
					
					while(rs.next()) {
							members.add(new Member(
									rs.getInt("idx"),
									rs.getString("memberid"),
									rs.getString("password"),
									rs.getString("membername"),
									rs.getString("memberphoto"),
									rs.getTimestamp("regdate")));
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					JdbcUtil.close(rs);
					JdbcUtil.close(stmt);
				}
				
				return members;
		
	}
	
	public int insertMember(Connection conn, Member member) throws SQLException{
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql1 = "insert into member (memberid, password, membername) values(?,?,?)";
		String sql2 = "insert into member(memberid, password, membername, memberphoto) values(?,?,?,?)";
		
		try {
			
			if(member.getMemberphoto() == null) {
				pstmt = conn.prepareStatement(sql1);
				pstmt.setString(1, member.getMemberid());
				pstmt.setString(2, member.getPassword());
				pstmt.setString(3, member.getMembername());

				
				
			} else {
					pstmt = conn.prepareStatement(sql2);
					pstmt.setString(1, member.getMemberid());
					pstmt.setString(2,  member.getPassword());
					pstmt.setString(3, member.getMembername());
					pstmt.setString(4, member.getMemberPhoto());
					
			}
			
			result = pstmt.executeUpdate();
			
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return result;
				
	}
	
	public int deleteMemberById(Connection connn, String memberId) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = "delete from member where memberid=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			
		}
		return result;
		
	}
	
	public int updateMember(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = "update member set password=?, membername=?, memberphoto=? where memberid=?";
		
		try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,  member.getPassword());
				pstmt.setString(2, member.getMembername());
				pstmt.setString(3, member.getMemberphoto());
				pstmt.setString(4, member.getMemberid());
				
				result = pstmt.executeUpdate();
				
		} catch(SQLException e) {
			e.printStackTrace();
			
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return result;
		
		
	}
	
	public Member getMemberByIdPw(Connection conn, String memberId, String password) {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from member where memberid=? and password=?";
		
		try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memberId);
				pstmt.setString(2, password);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
						member = new Member();
						member.setIdx(rs.getInt("idx"));
						member.setMemberid(rs.getString("memberid"));
						member.setPassword(rs.getString("password"));
						member.setMembername(rs.getString("membername"));
						member.setMemberphoto(rs.getString("memberphoto"));
						member.setRegdate(rs.getTimestamp("regdate"));
				}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return member;
		
	}
	
	public int getMemberById(Connection conn, String memberId) {
		int resultCnt = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select count(*) from member where memberid=?";
		
		try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memberId);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					resultCnt = rs.getInt(1);
				}
				
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);;
			JdbcUtil.close(pstmt);
		}
		
		return resultCnt;
		
	}
	
	
}
