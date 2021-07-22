package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import member.domain.Member;

public class MemberDao {

	// 싱글톤 패턴
	// 1.인스턴스 생성을 막는다.
	private MemberDao() {}
	// 2.클래스 내부에서 인스턴스 생성
	private static MemberDao dao = new MemberDao();
	// 3.외부에서 참조값을 반환 받을 수 있는 메서드
	public static MemberDao getInstance() {
		return dao == null? new MemberDao() : dao;
	}
	
	// member 정보 조회
	public List<Member> getMemberList(Connection conn){
		List<Member> memberList = null;
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			stmt = conn.createStatement();
			String sqlSelect = "select * from project.member order by idx";
			
			rs = stmt.executeQuery(sqlSelect);
			
			memberList = new ArrayList<Member>();
			
			while(rs.next()) {
				memberList.add(makeMember(rs));
				
			}
			
		} catch (SQLException e) {
				e.printStackTrace();
		
		} finally {
			
				JdbcUtil.close(rs);
				JdbcUtil.close(stmt);
		}
		return memberList;
		
	}
	
	// member등록
	public int joinMember(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sqlInsert = "insert into project.member(id, pw, name) values(?, ?, ?)";
		
			try {
				pstmt = conn.prepareStatement(sqlInsert);
				pstmt.setString(1, member.getId());
				pstmt.setString(2, member.getPw());
				pstmt.setString(3, member.getName());
				
				result = pstmt.executeUpdate();
				
			
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
					JdbcUtil.close(pstmt);
			}
			return result;
	}
	
	public Member selectByIdx(Connection conn, int idx) {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sqlSel = "select * from project.member where idx=?";
		
			try {
				pstmt = conn.prepareStatement(sqlSel);
				pstmt.setInt(1, idx);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					member = makeMember(rs);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
					JdbcUtil.close(rs);
					JdbcUtil.close(pstmt);
			}
			return member;
	}
	
	// 회원 정보 수정
	public int updateMember(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sqlUpdate = "update member set pw=?, name=? where idx=?";
		
			try {
				pstmt = conn.prepareStatement(sqlUpdate);
				pstmt.setString(1, member.getPw());
				pstmt.setString(2, member.getName());
				pstmt.setInt(3, member.getIdx());
				
				result = pstmt.executeUpdate();
			} catch (SQLException e) {

				e.printStackTrace();
			} finally {
					JdbcUtil.close(pstmt);
			}
			return result;
	}
	
	public int deleteMember(Connection conn, int idx) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String delSql = "delete from project.member where idx=?";
		
			try {
				pstmt = conn.prepareStatement(delSql);
				pstmt.setInt(1, idx);
				
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				jdbcUtil.close(pstmt);
			}
			return result;
	}
	
	private Member makeMember(ResultSet rs) throws SQLException {
		Member member = new Member();
		member.setIdx(rs.getInt("idx"));
		member.setId(rs.getString("id"));
		member.setPw(rs.getString("pw"));
		member.setName(rs.getString("name"));
		member.setRegDate(rs.getString("regDate"));
		return member;
	}
	
}
