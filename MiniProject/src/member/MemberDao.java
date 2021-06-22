package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MemberDao {
		
		
		//싱글톤 패턴 : 여러개의 인스턴스를 생성하지 못하도록 하는 디자인 패턴
		// 1.외부 클래스 또는 인스턴스에서 해당 클래스로 인스턴스를 생성하지 못하도록 처리
		private MemberDao() {
		}
		
		// 2.클래스 내부에서 인스턴스를 만들고
		static private MemberDao dao = new MemberDao();
		
		// 3.메소드를 통해서 반환 하도록 처리
		public static MemberDao getInstance() {
			return dao;
		}
		
		
		
		// 1. 전체 데이터 검색 기능
		// 반환 타입 List<Member>
		// 매개변수 -Connection 객체 : Statement
		ArrayList<Member> getMemberList(Connection conn) {
			
			ArrayList<Member> list = null;
			
			// 데이터 베이스의 Member테이블 이용 select 결과를 -> list 저장
			Statement stmt = null;
			ResultSet rs = null;
			
				try {
					stmt = conn.createStatement();
					String sql = "select * from member order by idx";
				
					// 결과 받아오기
					rs = stmt.executeQuery(sql);
					
					list = new ArrayList<>();
					
					//데이터를 Member 객체로 생성 -> list에 저장
					while (rs.next()) {
						Member m = new Member(rs.getInt(1), rs.getString(2),
								rs.getString(3), rs.getString(4),
								rs.getString(5), rs.getString(6), rs.getString(7));
						
						list.add(m);
					}
				
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					
						if(rs != null) {
							 try {
								rs.close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
						if(stmt != null) {
							try {
								stmt.close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
				}
				
				return list;
			
		}
		
		// 2.Member 테이블엣 데이터 저장하는 메소드
		// 사용자로 부터 데이터 받기 -> member 객체
		int insertMember(Connection conn, Member member) {
			
			int result = 0;
			
			//전달받은 Member 객체의 데이터로 Member 테이블에 저장 -> 결과값을 반환
			PreparedStatement pstmt = null;
			
				
				try {
					String sql = "insert into member values(MEMBER_IDX_SEQ.nextval, ?, ?, ?, ?, ?, ?)"; //이부분 확인해야함
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, member.getId());
					pstmt.setString(2, member.getPw());
					pstmt.setString(3, member.getName());
					pstmt.setString(4, member.getCarreg());
					pstmt.setString(5, member.getEmail());
					pstmt.setString(6, member.getAddress());
					
					result = pstmt.executeUpdate();
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					if(pstmt != null) {
						try {
							pstmt.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				return result;
				
		}
		
		
		// 3.Member 테이블의 데이터 수정 메소드
		// 반영된 행의 개수 반환
		// 사용자로부터 데이터를 받아서 처리 -> Member 객체
		int editMember(Connection conn, Member member) {
			
			int result = 0;
			
			// 전달받은 Dept 객체의 데이터로 Dept 테이블에 저장 -> 결과 값을 반환
			PreparedStatement pstmt = null;
			
				
				try {
					String sql = "update member set id=?, pw=?, name=?, carreg=?, email=?, address=? where idx=?"; //idx번호를 입력해서 그 행의 내용을 수정
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, member.getId());
					pstmt.setString(2, member.getPw());
					pstmt.setString(3, member.getName());
					pstmt.setString(4, member.getCarreg());
					pstmt.setString(5, member.getEmail());
					pstmt.setString(6, member.getAddress());
					pstmt.setInt(7, member.getIdx());
					
					result = pstmt.executeUpdate();
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					if(pstmt != null) {
						try {
							pstmt.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				return result;
				
		}
		
		// 4.Member 테이블의 데이터를 삭제
		// 삭제된 행의 개수를 반환
		// 사용자로부터 IDX 받아서 처리
		int deleteMember(Connection conn, int idx) {
			
			int result = 0;
			
			//데이터 베이스 처리 sql
			PreparedStatement pstmt = null;
			String sql = "delete from member where idx =?";	
			
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, idx);
					
					result = pstmt.executeUpdate();
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					
						if(pstmt != null) {
								try {
									pstmt.close();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
						}
				}
				
				return result;
		}
		
		
		
		
		
		
		
}
