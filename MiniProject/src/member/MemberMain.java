package member;

import java.util.Scanner;

public class MemberMain{

	public static void main(String[] args) {
			
		
			MemberManager manager = new MemberManager(MemberDao.getInstance());
			
			Scanner sc = new Scanner(System.in);
			
				
			// 1.드라이버 로드
			try {
				
				Class.forName("oracle.jdbc.driver.OracleDriver");
			
			while(true) {
				System.out.println("멤버관리 프로그램");
				System.out.println("----------------------------");
				System.out.println("1. 멤버 리스트");
				System.out.println("2. 멤버 정보 입력");
				System.out.println("3. 멤버 정보 수정");
				System.out.println("4. 멤버 정보 삭제");
				System.out.println("5. 프로그램 종료");
				System.out.println("------------------------------");
				System.out.println("원하시는 기능의 번호를 입력해주세요.");
				int num = Integer.parseInt(sc.nextLine());
				
				
				switch(num) {
				case 1:
						manager.memberList();
						break;
				case 2:
						
						break;
				case 3:
						manager.editMember();
						break;
				case 4:
						manager.delMember();
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
