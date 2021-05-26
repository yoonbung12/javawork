package ch05;

public class Member {
	//생성자 -> 초기화 메소드, 인스턴스 생성할때 단 한번, 인스턴스 변수를 초기화한다.
	
			//바꿀수 없는 데이터가 있으면 final을 써야함.!!!!!!
			//①아래의 데이터를 저장 이름, 전화번호, 전공, 학년, email, 생일, 주소
			//데이터를 저장 -> 변수를 이용해서 메모리에 저장
			//접근제어자 private은 멤버들만 가능, public
			private String name; // 클래스의 영역에서 정의 ->인스턴스 변수, 인스턴스가 생성될때 변수의 메모리공간이 생성된다. 초기화를 생략 가능!
			private String phoneNumber; // 참조형 변수의 기본값 -> null
			private String major;
			private int grade;
			private String email;
			private String birthday; // 2020-10-10 이런식이면 String으로 쓸것.
			private String address;
			
			//접근제어 지시자 private 을 쓴ㄴ 이유
			//클래스 내부의 멤버들만 참조가 가능 : 정보 은닉, 변수의 데이터를 보호
			
			
			//③모든 정보를 저장하도록 하는 생성자와 생일과 주소는 저장하지 않을 수 있는 생성자를 정의
			//생성자의 요구사항 - > 생성자 오버로딩
			//오버로딩 -> 이름이 같은 메소드 정의
				//생성자의 이름(매개변수) { 초기화 코드....}
				//생성자의 이름 -> 클래스 이름
				//생성자는 반드시 하나이상이 있어야 한다.!!!!!
			
			Member(String name, String phoneNumber, String major, int grade, String email, String birthday, String address) {
				this.name = name; //지역변수가 우선이다.
				this.phoneNumber = phoneNumber;
				this.major = major;
				this.grade = grade;
				this.email = email;
				this.birthday = birthday;
				this.address = address;
			}
			Member(String name, String phoneNumber, String major, int grade, String email) {
//				this.name = name; //지역변수가 우선이다.
//				this.phoneNumber = phoneNumber;
//				this.major = major;
//				this.grade = grade;
//				this.email = email;
				this(name, phoneNumber, major, grade, email, null, null); //값이 없으니까 null넣어줘야함.
				
				//오버로딩의 조건:
				//1.메소드의 이름은 같다,
				//2.매개변수의 개수가 틀리거나, 매개변수의 타입이 달라야 오버로딩이 성립
			}
			Member(String name, String phoneNumber, String major) {
				this(name, phoneNumber, major, 0, null, null, null);
			}
			Member() {}
			
			//②위에서 정의한 정보를 출력하는 메소드 정의
			//기능 -> 메소드
			void showInfo() { //안에내용을 쓸경우 아래 this를 써야함.
				System.out.println("이름: " + this.name); //this는 자기자신의 참조값을 가지는 참조변수
				System.out.println("전화번호: " + phoneNumber);
				System.out.println("전공: " + major);
				System.out.println("학년: " + grade);
				System.out.println("이메일: " + email);
				
				if(this.birthday == null) {
					System.out.println("생일 : 입력된 데이터가 없습니다.");
				} else {
				System.out.println("생일: " + birthday);
				
			}
			
			if(this.address != null) {
				System.out.println("주소: " + address);
			} else {
				System.out.println("주소 : 입력된 데이터가 없습니다.");
			}
			}
			public void setName(String name) {
				this.name = name;
			}
			public String getName(String name) {
				return this.name;
			}
//④main() 메소드에서 두 가지 생성자를 이용해서 인스턴스 생성하고 출력 메소드를 통해 저장된 데이터 출력	
	public static void main(String[] args) {
		
		
		Member member = new Member("철수", "010-0000-0000", "짱구친구", 4, "diem@gmail.com", "2000-10-10", "서울");
		member.name ="영희"; 
		member.showInfo();
		//member1.phoneNumber = "00000000";
		
		System.out.println("===============================");
		Member member2 = new Member("손흥민", "010-0000-0000", "축구", 1, "son@dies.naver.com");
		member2.showInfo();
		
		
	}

}
