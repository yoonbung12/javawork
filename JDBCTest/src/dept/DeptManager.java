package dept;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DeptManager {
	
	private DeptDao dao;
	
	public DeptManager(DeptDao dao) {
		this.dao = dao;
	}
	
	// 전체 리스트 출력 메소드
	// DAO 에서 데이터 리스트를 받고 출력 처리
	void deptList() {
		// Connection 객체 생성 -> 트렌 젝션 처리
		Connection conn = null;
		
		//2.연결
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe"; //몇번씩 해볼것
		String user = "hr";
		String pw = "tiger";
		
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, pw);
			
			List<Dept> list = dao.getDeptList(conn);
			
			System.out.println("부서 정보 리스트");
			System.out.println("-----------------------------");
			System.out.println("부서번호 \t 부서이름 \t 위치");
			System.out.println("-----------------------------");
			
			for(Dept dept : list) {
				System.out.printf("%d \t %s \t %s \n",
						dept.getDeptno(),
						dept.getDname(),
						dept.getLoc());
			}
			
			System.out.println("-----------------------------");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("데이터베이스 연결 성공!!!");
	}
	
	// 데이터 입력
	
	// 데이터 수정
	
	// 데이터 삭제
	
	
}
