package com.bitcamp.op.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.bitcamp.op.jdbc.JdbcUtil;
import com.bitcamp.op.member.domain.Member;


@Repository
public class JdbcTemplateMemberDao {
	
	@Autowired
	private JdbcTemplate template;



	public int insertMember(Connection conn, Member member) throws SQLException {

//		template.
//		
//		
//		int resultCnt = 0;
//
//		PreparedStatement pstmt = null;

		

//		try {
//			
//			if(member.getMemberphoto() == null) {
//				pstmt = conn.prepareStatement(sql1);
//				pstmt.setString(1, member.getMemberid());
//				pstmt.setString(2, member.getPassword());
//				pstmt.setString(3, member.getMembername());
//			} else {
//				pstmt = conn.prepareStatement(sql2);
//				pstmt.setString(1, member.getMemberid());
//				pstmt.setString(2, member.getPassword());
//				pstmt.setString(3, member.getMembername());
//				pstmt.setString(4, member.getMemberphoto());
//			}
//			
//			resultCnt = pstmt.executeUpdate();
//
//		}  finally {
//			JdbcUtil.close(pstmt);
//			
//		}
		
		

		int resultCnt = 0;
		String sql1 = "insert into member (memberid,password,membername) values (?, ?, ?)";
		String sql2 = "insert into member (memberid,password,membername, memberphoto) values (?, ?, ?,?)";
		
		if(member.getMemberphoto() != null	) {
			resultCnt = template.update(sql2,
										member.getMemberid(),
										member.getPassword(),
										member.getMembername(),
										member.getMemberphoto()
										);
		} else {
			resultCnt = template.update(sql1,
						member.getMemberid(),
						member.getPassword(),
						member.getMembername()
					);
			
		}
		return resultCnt;

	}

	public int insertMember1(final Member member) throws SQLException {

		

		int resultCnt = 0;
		String sql1 = "insert into member (memberid,password,membername) values (?, ?, ?)";
		final String sql2 = "insert into member (memberid,password,membername, memberphoto) values (?, ?, ?,?)";
		
		
		
		// 자동 증가한 컬럼의 값을 저장할 객체
		KeyHolder holder = new GeneratedKeyHolder();
		
		template.update(
				new PreparedStatementCreator() {
					
					@Override
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

						
						PreparedStatement pstmt = con.prepareStatement(sql2, new String[] {"idx"});
						pstmt.setString(1, member.getMemberid());
						pstmt.setString(2, member.getPassword());
						pstmt.setString(3, member.getMembername());
						pstmt.setString(4, member.getMemberphoto());
						return null;
					}
				}
				, holder);
		
		Number idx = holder.getKey();
		
		
		
		if(member.getMemberphoto() != null	) {
			resultCnt = template.update(sql2,
										member.getMemberid(),
										member.getPassword(),
										member.getMembername(),
										member.getMemberphoto()
										);
		} else {
			resultCnt = template.update(sql1,
						member.getMemberid(),
						member.getPassword(),
						member.getMembername()
					);
			
		}
		return resultCnt;

	}
	
	
	public List<Member> selectList(Connection conn) {

		List<Member> list = null;

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();

			String sql = "select * from member";

			rs = stmt.executeQuery(sql);

			list = new ArrayList<Member>();

			while (rs.next()) {
				list.add(new Member(
						rs.getInt(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getString(4),
						rs.getString(5),
						rs.getTimestamp(6)));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}

		return list;

	}
	
	
	public Member selectByIdPw(Connection conn, String id, String pw) {
		
//		Member member = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		String sql = "select * from member where memberid=? and password=?";
//		
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, id);
//			pstmt.setString(2, pw);
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {
//				member = new Member();
//				member.setIdx(rs.getInt("idx"));
//				member.setMemberid(rs.getString("memberid"));
//				member.setPassword(rs.getString("password"));
//				member.setMembername(rs.getString("membername"));
//				member.setRegdate(rs.getTimestamp("regdate"));
//			}
//			
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			JdbcUtil.close(rs);
//			JdbcUtil.close(pstmt);
//		}
//		
		//jdbc 템플릿 이용해서 만든 코드
		Member member = null;
		
		
		String sql = "select * from member where memberid=? and password=?";
		List<Member> list = template.query(sql, new MemberRowMapper(), id, pw );
		//Member = list.isEmpty()	? null : list.get(0);
		
		return list.isEmpty()	? null : list.get(0);
	}
	
	 // ID 중목여부 확인을 위한 id 값으로 검색 -> 개수 반
	public int selectByIdPw(Connection conn, String memberId) throws SQLException {
		//8/11
//		String sql ="select count(*) from member where memberid=?"
//		int cnt = template.queryForObject(sql, Integer.class, memberId);
//		return cnt;
//		return template.queryForObject("select count(*) from member where memberid=?", null);
	}
	
	// ID 중복여부 확인을 위한 id 값으로 검색 -> 개수 반환
	public int selectById(Connection conn, String memberId) throws SQLException {
		
		int cnt = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select count(*) from member where memberid=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
			
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return cnt;
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
