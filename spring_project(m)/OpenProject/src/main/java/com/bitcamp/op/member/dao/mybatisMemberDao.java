package com.bitcamp.op.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.bitcamp.op.jdbc.JdbcUtil;
import com.bitcamp.op.member.domain.Member;
import com.bitcamp.op.member.domain.SearchIdPw;


@Repository
public class mybatisMemberDao {
	
	

	@Autowired
	private SqlSessionTemplate template;
	
	private final String NAME_SPACE ="com.bitcamp.op.member.dao.memberMapper";
	
	public int insertMember(Member member) throws SQLException {
			return template.update(NAME_SPACE + ".insertMember", member);
	}
	

	public int insertMember1(final Member member) throws SQLException {

		int resultCnt = 0;
		
		
		// 자동 증가한 컬럼의 값을 저장할 객체
//		KeyHolder holder = new GenereatedKeyHolder();
//		
//		resultCnt = template.update(
//						new PreparedStatementCreator() {
//							
//							@Override
//							public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//								String sql2 =  "insert into member(memberid, password,membernamem, memberphoto) values(?,?,?,?)";
//								PreparedStatement pstmt = con.prepareStatement(sql2, new String[] {"idx"});
//								pstmt.setString(1, member.getMemberid());
//								pstmt.setString(2, member.getPassword());
//								pstmt.setString(3, member.getMembername());
//								pstmt.setString(4, member.getMemberphoto());
//								return pstmt;
//							}
//						}
//						, holder);
//		
//		Number idx = holder.getKey();
//		member.setIdx(idx.intValue());
		
		return resultCnt;

			
		

	}

	public List<Member> selectList(Connection conn){
		
		List<Member> list = null;
		
//		try {
//				stmt = conn.createStatement();
//				
//				String sql = "select * from member";
//				
//				rs = stmt.executeQuery(sql);
//				
//				list = new ArrayList<Member>();
//				
//				while(rs.next()) {
//						list.add(new Member(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
//										rs.getTimestamp(6)));	
//				
//				}
//					
//		
//		} catch(SQLException e) {
//			e.printStackTrace();
//			
//		} finally {
//			JdbcUtil.close(rs);
//			JdbcUtil.close(stmt);
//			
//		}
		
		return list;
		
	}
	
	public Member selectByIdPw(String id, String pw) {
			return template.selectOne(NAME_SPACE+".selectByIdPw", new SearchIdPw(id, pw));
	}
	
	// ID 중복 여부 확인을 위한 id 값으로 검색 -> 개수 반환
	public int selectById(String memberId) throws SQLException {
			return template.selectOne(NAME_SPACE+".selectById", memberId);
	
	}
}		

