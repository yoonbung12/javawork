package ch02;

//ch02.Member(클래스가 가지는 원래 이름)
public class Member {
	int num;	//인스턴스 변수
	public static void main(String[] args) {//main 지역변수
		
		String name = "윤병우";
		int age = 27; //변수선언과 동시에 초기화
		double height = 122.3d;// 키(연산을 할때 Type을 일치)
		//사이즈가 큰거에서 작은것으로 갈때는 명시적 형변환이 필요(type casting)
		float weight = 45.8f; // 몸무게
		
		boolean hasBook = false; //c나 python은 has_Book을 씀(언더바)
		//변수 선언
		//데이터의 타입 식별 이름
		//데이터 타입 -> 내가 다루어야 하는 데이터의 특징에 의해서 결정
		//기본형 타입 8가지 -> 정수형, 실수형, boolean, 문자(유니코드)
		
		System.out.println("이름 :" + name); // String + String
		System.out.println("키 :" + height + "cm 입니다.");
		// String + double + String = (String + String) + String -> String + String
		//왼쪽 부터 계산! String + anyType -> String+ String
		System.out.println("나이 :" + age + "살 입니다.");
		System.out.println("책의 보유 여부 :" + hasBook); // String + boolean
		
		if(hasBook) { //if할때 조건식을 사용
			System.out.println("책 있어요!!!");
		} else {
			System.out.println("책 없어요!!!");
		}
	}

}
