package ex.infomanager;

import java.util.*;

public class FriendBook {

	public static void main(String[] args) {
		
		//Friend friend = new Friend("", "", "");
		
		FriendInfoHandler handler = new FriendInfoHandler(100);
		
		Scanner s = new Scanner(System.in);
		
		while(true) { //아예정리를 끝내놓고 하는게 좋은 방법임...
			System.out.println("** 메뉴선택 *******************");
			System.out.println("1.고교 친구 정보 입력");
			System.out.println("2.대학 친구 정보 입력");
			System.out.println("3.전체 정보 출력");
			System.out.println("4.기본 정보 출력");
			System.out.println("5.프로그램 종료");
			System.out.println("***************	***********");
			
			System.out.println("메뉴를 선택하세요>>");
			int choice = Integer.parseInt(s.nextLine());
			
			switch(choice) {
			case 1:
			case 2:
				handler.addFriend(choice);
				break;
			case 3:
				handler.showAllData();
				break;
			case 4:
				handler.showAllBasicData();
				break;
			case 5:
				System.out.println("프로그램을 종료합니다.");
				return;
			}	
		}
		
		
	}

}
