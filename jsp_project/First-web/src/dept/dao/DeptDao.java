package dept.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dept.domain.Dept;
import jdbc.util.JdbcUtil;

public class DeptDao {
	
	// 싱글톤 패턴
	// 1. 인스턴스 생성을 막는다!!!
	private DeptDao() {}	// Test 하기 위한 잠시동안의 public테스트 완료후 private로 바꿀
	// 2. 클래스 내부에서 인스턴스를 생성!!
	private static DeptDao dao = new DeptDao();
	// 3. 외부에서 참조값을 반환 받을수 있는 메소드
	public static DeptDao getInstance() {
		return dao;		// 다른 방법 return dao == null? new DeptDao() : dao ; 삼항연산자
	}
	
	public List<Dept> getDeptList(Connection conn){
		
		Statement stmt = null;
		ResultSet rs = null;
		
		List<Dept> list = null;
		
		
		try {
			stmt = conn.createStatement();
		
			String sql = "select * from dept";
			
			rs = stmt.executeQuery(sql);
			
			list = new ArrayList<Dept>();
		
			while(rs.next()) {
				list.add(makeDept(rs)); //메소드 사용
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


	public int insertDept(Connection conn, Dept dept) {
		
		int resultCnt = 0;
		PreparedStatement pstmt = null;
		
		String sql = "insert into dept values (?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dept.getDeptno());
			pstmt.setString(2, dept.getDname());
			pstmt.setString(3, dept.getLoc());
			
			resultCnt = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return resultCnt;
	}
	
	public int deleteDept(Connection conn, int deptno) {
		int resultCnt = 0;
		PreparedStatement pstmt = null;
		
		String sql = "delete from dept where deptno=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, deptno);
			
			resultCnt = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return resultCnt;
	}
	
	public Dept selectByDeptno(Connection conn, int deptno) {
		Dept dept = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from dept where deptno=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, deptno);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dept = makeDept(rs); //메소드 사용 
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return dept;
	}
	
	//중첩된것들 메소드 만들수 있음
	private Dept makeDept(ResultSet rs) throws SQLException {
		
		Dept dept = new Dept();
		dept.setDeptno(rs.getInt("deptno"));
		dept.setDname(rs.getString("dname"));
		dept.setLoc(rs.getString("loc"));
		
		return dept;
		
	}
	
	
	
	
	
	
	public int updateDept(Connection conn, Dept dept) {
		
		int resultCnt = 0;
		PreparedStatement pstmt = null;
		
		String sql = "update dept set dname=?, loc=? where deptno=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dept.getDname());
			pstmt.setString(2, dept.getLoc());
			pstmt.setInt(3, dept.getDeptno());
			
			resultCnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		
		return resultCnt;
	}

}
