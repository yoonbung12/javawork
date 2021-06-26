package pay;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class PayManager {
	
	
	private PayDao dao;
	private Scanner sc;
	
	public PayManager(PayDao dao) {
		this.dao = dao;
		sc = new Scanner(System.in);
	}
	
	
	//페이 전체 출력 메소드
	void payList() {
		Connection conn = null;
		
		//연결
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String pw = "tiger";
		
			try {
				conn = DriverManager.getConnection(jdbcUrl, user, pw);
			
				List<Pay> list = dao.getPayList(conn);
			
				System.out.println("결제 정보");
				System.out.println("----------------------");
				System.out.println("페이번호 \t 페이머니 \t 결제통과인지 \t렌트코드");
				System.out.println("-----------------------");
				
				for(Pay pay : list) {
					System.out.printf("%d \t %d \t %s \t %d",
						pay.getPaycode(),
						pay.getPaymoney(),
						pay.getPaysucc(),
						pay.getRentcode()
						);
					System.out.println();
				}
				System.out.println();
				System.out.println("------------------------------------");
			
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//이게 있을 필요가 있나??
		//데이터 입력 
		void inputData() {
			
			Connection conn = null;
			
			String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "hr";
			String pw = "tiger";
			
				try {
					conn = DriverManager.getConnection(jdbcUrl, user, pw);
				
					System.out.println("결제 정보를 입력합니다.");
					System.out.println("결제가격 결제완료 렌트코드");
					
					String inputData = sc.nextLine();
					String[] payData = inputData.split(" ");
					
					Pay pay = new Pay(0,Integer.parseInt(payData[0]),payData[1], Integer.parseInt(payData[2]));
					
					int result = dao.insertPay(conn, pay);
					
					if(result > 0) {
						System.out.println("입력되었습니다.");
					} else {
						System.out.println("입력실패");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		}	//결제 확인
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
									
									System.out.println("렌트비는:" + rentperiod * price);
									
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
				void inputData2() {
						
					Connection conn = null;
					
					String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
					String user = "hr";
					
					
				}
				
		
}		

