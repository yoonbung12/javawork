package dept;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DeptDao {
	
	// 1. 전체 데이터 검색 기능
	// 반환 타입 List<Dept> 
	// 매개변수 - Connection 객체 : Statement
	ArrayList<Dept> getDeptList(Connection conn) {
		
		ArrayList<Dept> list = null;
		
		//데이터 베이스의 Dept테이블 이용 select 결과를 -> list에 저장
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.createStatement();
			String sql = "select * from dept order by deptno";
			
			//결과 받아오기
			rs = stmt.executeQuery(sql);
			
			list = new ArrayList<>();
			
			//데이터 Dept 객체로 생성 -> list에 저장
			while(rs.next()) {
				list.add(new Dept(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
}
