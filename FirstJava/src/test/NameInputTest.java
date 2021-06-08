package test;

import java.util.Scanner;

public class NameInputTest {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("이름을 입력하세요.");
		String name = scanner.nextLine();
		
		if(name != null && !name.trim().isEmpty()) {
			
			if(checkChar(name)) {
				System.out.println("입력하신 이름은 :" + name);
			} else {
				
					try {
						
							throw new BadIdInputException("잘못된 이름 입력 오류");
					} catch(Exception e) {
						
					}
			}
		
		} else {
			System.out.println("이름이 입력되지 않았습니다.");
		}

	}

	private static boolean checkChar(String name) {
		
		boolean result = true;
		
		for(int i = 0; i < name.length(); i++ ) {
			char c = name.charAt(i);
			if(!(c >= 'a' && c <='z' || c>= 'A' && c <= 'z' || c>= '0' && c <= '9')) {
				result = false;
				break;
			}
		}
		
		return result;
	}

}
