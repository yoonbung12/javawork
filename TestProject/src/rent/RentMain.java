package rent;

import java.util.Scanner;

public class RentMain {

	public static void main(String[] args) {
			
		RentManager manager = new RentManager(RentDao.getInstace());
		
		Scanner sc = new Scanner(System.in);
		
		
			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
			while(true) {
				
					System.out.println("렌트관리 프로그램");
					System.out.println("-----------------------------");
					System.out.println("1.렌트 리스트");
					System.out.println("2.렌트 정보 입력");
					System.out.println("3.렌트 정보 수정");
					System.out.println("4.렌트 정보 삭제");
					System.out.println("5.프로그램 종료");
					System.out.println("---------------------------------");
					
					System.out.println("원하시는 기능의 번호를 입력해주세요.");
					int num = Integer.parseInt(sc.nextLine());
					
					switch(num) {
					case 1: 
						manager.rentList();
						break;
					case 2:
						manager.inputData();
						break;
					case 3:
						manager.editRent();
						break;
					case 4:
						manager.delRent();
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
