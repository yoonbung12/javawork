package pay;

import java.util.Scanner;

public class PayMain {

	public static void main(String[] args) {
		
		PayManager manager = new PayManager(PayDao.getInstance());
		
		Scanner sc = new Scanner(System.in);
		
		
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			
			while(true) {
					
					System.out.println("결제관리 프로그램");
					System.out.println("-------------------------");
					System.out.println("1. 결제 리스트");
					System.out.println("2. 결제 정보 입력");
					System.out.println("3.결제를 시작하세요");
					
					
					System.out.println("원하시는 기능의 번호를 입력해주세요.");
					int num = Integer.parseInt(sc.nextLine());
					
					switch(num) {
					case 1:
							manager.payList();
							break;
					case 2:
							manager.inputData();
							break;
					case 3:
							manager.rentPay();
							
							break;
							
					case 5:
							System.out.println("프로그램을 종료합니다.");
							return;
					}
			}
			
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

}
