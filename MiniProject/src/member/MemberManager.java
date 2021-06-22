package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MemberManager {
		
		
		private MemberDao dao;
		private Scanner sc;
		
		public MemberManager(MemberDao dao) {
			this.dao = dao;
			sc = new Scanner(System.in);
		}
		
		// 멤버 전체 리스트 출력 메소드
		// Dao 에서 데이터 리스트를 받고 출력 처리
		void memberList() {
			
			//Connection 객체 생성 -> 트렌젝션 처리
			Connection conn = null;
			
			// 2.연결
			String jdbcUrl = "jdbc:oracle:this@localhost:1521:xe";
			String user = "hr";
			String pw = "tiger";
			
				try {
					conn = DriverManager.getConnection(jdbcUrl, user, pw);
				
					List<Member> list = dao.getMemberList(conn);
					
					System.out.println("멤버 정보 리스트");
					System.out.println("----------------------");
					System.out.println("name \t carreg \t email \t address");
					System.out.println("--------------------------------");
					
					
					for(Member member : list) {
						System.out.printf("%s \t %s \t %s \t %s \t %s \n ", member.getName(), member.getCarreg(), member.getEmail(), member.getAddress());
					}
					System.out.println("-----------------------");
					
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		
		/*
		// 데이터 입력
		// 사용자에게 Scanner 클래스로 입력 받아 -> dao insertMember 메소드로 저장
		void inputData() {
			
				//Connection 객체 생성 ->  트렌젝션 처리
				Connection conn = null;
				
				// 2.연결
				String jdbcUrl = "jdbc:oracle:this@localhost:1521:xe";
				String user = "hr";
				String pw = "tiger";
				
				try {
					conn = DriverManager.getConnection(jdbcUrl, user, pw);
				
					System.out.println("멤버 정보를 입력합니다.");
					System.out.println("이름 운전면허 이메일 주소 형식으로 입력해주세요.");
					System.out.println("에시) name 감");
					String inputData = sc.nextLine();
					String[] memberdata = inputData.split(" ");
					
					Member member = new Member(0, memberdata[0], memberdata[1], memberdata[2], memberdata[3], memberdata[4],memberdata[5]);
					
					int result = dao.insertMember(conn, member);
					
					if(result > 0) {
						System.out.println("입력되었습니다.");
					} else {
						System.out.println("입력 실패!!!!");
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} */
		//} */
		//데이터 수정
		//사용자로부터 데이터를 받아 수정 -> dao editMember()메소드 이용
		void editMember() {
			//Connection 객체 생성 -> 트렌 젝션 처리
			Connection conn = null;
			
			// 2.연결
			String jdbcUrl = "jdbc:oracle:thin@localhost:1521:xe";
			String user = "hr";
			String pw = "tiger";
			
			
				try {
					conn = DriverManager.getConnection(jdbcUrl, user, pw);
				
					System.out.println("멤버 정보를 수정합니다.");
					System.out.println("IDX 멤버이름 운전면허 멤버이메일 멤버주소    형식으로 입력해주세요.");
					System.out.println("입력된 IDX의 멤버이름 운전면허 멤버이메일 멤버주소가 수정됩니다.");
					String editData = sc.nextLine();
					String[] eData = editData.split(" ");
					
					Member member = new Member(Integer.parseInt(eData[0]), eData[1] ,eData[2], eData[3], eData[4], eData[5], eData[6]);
					
					int result = dao.editMember(conn, member);
					
					if(result > 0) {
						System.out.println("수정되었습니다.");
					} else {
						System.out.println("수정실패 !!!!");
					}
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		//데이터 삭제
		// 사용자로부터 IDX를 입력 받아 부서 정보를 삭제
		void delMember() {
			
			Connection conn = null;
			
			// 2.연결
			String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "hr";
			String pw = "tiger";
			
				try {
					conn = DriverManager.getConnection(jdbcUrl, user, pw);
				
					memberList();
					System.out.println("삭제를 원하시는 IDX를 입력해주세요.");
					int idx = Integer.parseInt(sc.nextLine());
					
					int result = dao.deleteMember(conn, idx);
					
					if(result > 0) {
						System.out.println("삭제되었습니다.");
					} else {
						System.out.println("해당 부서의 정보가 없습니다.");
					}
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

}
