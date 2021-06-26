package rent;

import java.util.Scanner;

public class TestMain {

	public static void main(String[] args) {
		
		
		RentManager manager1 = new RentManager(RentDao.getInstace());
		
		Scanner sc = new Scanner(System.in);
		
		
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			
			while(true) {
				System.out.println("테스트 시작해보자");
				System.out.println("1.렌트 가격을 구해라");
				
				
				System.out.println("기능의 번호를 눌러라");
				int num = Integer.parseInt(sc.nextLine());
				
				switch(num) {
				case 1:
					manager1.rentPay();
					break;
				case 5:
					System.out.println("시스템 종료");
					return;
				}
				
				
			}
			
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

}
