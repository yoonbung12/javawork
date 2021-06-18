package dept;

import java.sql.SQLException;

public class DeptMain {

	public static void main(String[] args) {
		
		//Connection conn = null;
		
		DeptManager manager = new DeptManager(DeptDao.getInstance());
		
		//1.드라이버 로드
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
//			System.out.println("드라이버 로드 성골!");
			manager.deptList();
			
			//2.연결 //DeptManager에서 사용하므로 없에도 괜찮음
			
			//String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe"; //몇번씩 해볼것
			//String user = "hr";
			//String pw = "tiger";
			
			//conn = DriverManager.getConnection(jdbcUrl, user, pw);
			//System.out.println("데이터베이스 연결 성공!!!");
		
			//DeptDao dao = DeptDao.getInstance();
			
//			List<Dept> list = dao.getDeptList(conn);
//			
//			for(Dept dept : list) {
//				System.out.println(dept);
//			}
			
			//Scanner sc = new Scanner(System.in);
			
//			System.out.println("부서입력을 시작합니다.");
//			System.out.println("부서번호 부서이름 부서위치 순으로 입력해주세요.");
//			System.out.println("예) 50 dev seoul");
//			String deptData = sc.nextLine();
//			String[] deptDatas = deptData.split(" ");
//			
			//Dept 객체 생성 -> Dao 메소드 호출
			//Dept dept = new Dept(Integer.parseInt(deptDatas[0]), deptDatas[1], deptDatas[2]);
			
			//int result = dao.insertDept(conn, dept);
//			if(result > 0) {
//				System.out.println("입력성공!");
//				
//			} else {
//				System.out.println("입력 실패");
//			}
			/*
			//Delete
			//System.out.println("부서 정보를 삭제합니다.");
			//System.out.println("삭제할 부서번호를 입력해주세요.");
			//String deptno = sc.nextLine();
			//int result = dao.deleteDept(conn, Integer.parseInt(deptno));
			
			//if(result > 0) {
				//System.out.println("삭제 완료!!");
			//} else {
				//System.out.println("삭제 실패 : 해당 번호의 부서가 존재하지 않습니다.");
			//}
			 */
		
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		
		

	}

}
