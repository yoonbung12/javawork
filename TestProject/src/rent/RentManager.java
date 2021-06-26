package rent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class RentManager {
		
	
		private RentDao dao;
		private Scanner sc;
		
		public RentManager(RentDao dao) {
			this.dao = dao;
			sc = new Scanner(System.in);
		}
		
		// 렌트 전체 리스트 출력 메소드
		void rentList() {
			
			Connection conn = null;
			
			//연결
			String jdbcUrl  = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "hr";
			String pw = "tiger";
			
			
				try {
					conn = DriverManager.getConnection(jdbcUrl, user, pw);
				
					List<Rent> list = dao.getRentList(conn);
					
					System.out.println("렌트 정보 르시트");
					System.out.println("-------------------------");
					System.out.println("렌트번호 \t 렌트가격 \t 대여기간 \t 대여날짜 \t 차코드 \t 멤버코드 \t 매니저 코드");
					System.out.println("--------------------------------");
					
					for(Rent rent : list) {
						System.out.printf("%d \t %d \t %d \t %s \t %d \t %d \t %d ", 
								rent.getRentcode(), 
								rent.getPay(), 
								rent.getRentperiod(), 
								rent.getSysDate(),
								rent.getCarcode(),
								rent.getMembercode(),
								rent.getManagercode()
								
								);
						System.out.println();
					}
					System.out.println();
					System.out.println("-------------------------------------");
				
				
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		//데이터 입력
		void inputData() {
			
			Connection conn = null;
			
			String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "hr";
			String pw = "tiger";
			
			
					try {
						conn = DriverManager.getConnection(jdbcUrl, user, pw);
						
						
						System.out.println("렌트 정보를 입력합니다.");
						System.out.println(" 렌트가격 대여기간 대여날짜   입력해주세요.");
						
						String inputData = sc.nextLine();
						String[] rentdata = inputData.split(" ");
						
						Rent rent = new Rent(0,Integer.parseInt(rentdata[0]), Integer.parseInt(rentdata[1]), rentdata[2], 0,0,0);
						
						int result = dao.insertRent(conn, rent);
						
						if(result > 0) {
							System.out.println("입력되었습니다.");
						} else {
							System.out.println("입력실패!!!!");
						}
						
						
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		}
		//데이터 수정
		
		void editRent() {
			
			Connection conn = null;
			
			String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "hr";
			String pw = "tiger";
			
			
					try {
						conn = DriverManager.getConnection(jdbcUrl, user, pw);
					
						System.out.println("렌트 정보를 수정합니다.");
						System.out.println("렌트번호 렌트가격 대여기간 대여날짜  반납일     입력해주세요.");
						
						String editData = sc.nextLine();
						String[] eData = editData.split(" ");
						
						Rent rent = new Rent(0, Integer.parseInt(eData[0]), Integer.parseInt(eData[1]), eData[2], 0,0,0);
					
						int result = dao.editRent(conn, rent);
						
						if(result > 0) {
							System.out.println("수정되었습니다.");
						} else {
							System.out.println("수정실패");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		}
		
		void delRent() {
			
			Connection conn = null;
			
			String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "hr";
			String pw = "tiger";
			
			
					try {
						conn = DriverManager.getConnection(jdbcUrl, user, pw);
					
						rentList();
						
						System.out.println("삭제를 원하시는 렌트번호를 입력해주세요.");
						int rentcode = Integer.parseInt(sc.nextLine());
						
						int result = dao.deleteRent(conn, rentcode);
						
						if(result>0) {
							System.out.println("삭제되었습니다.");
						} else {
							System.out.println("해당 부서의 정보가 없습니다.");
						}
					
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		}
		//가격 확인 방법
		void rentPay() {
			
			Connection conn = null;
			
			String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "hr";
			String pw = "tiger";
			
					
						try {
							conn = DriverManager.getConnection(jdbcUrl, user, pw);
							
							System.out.println("대여기간을 입력하세요:");
							int rentperiod = sc.nextInt();
							System.out.println("가격을 입력하세요:");
							int price = sc.nextInt();
							
							System.out.println("결과값은:" + rentperiod * price);
							
							System.out.println("지불할금액을 입력하세요");
							int paymoney = sc.nextInt();
							
							System.out.println("결과값은:" + rentperiod * price);
							if(((rentperiod*price) - paymoney)== 0) {
								System.out.println("결제를 하겠습니다");
							} else {
								System.out.println("결제를 하지 못하였습니다. 다시 결제하세요.");
								return;
							}
							
							
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
			}
		void rentSucces() {
			
		}
}
