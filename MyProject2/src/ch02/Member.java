package ch02;

public class Member {
	String name;
	String phoneNo;
	String major;
	int grade;
	String email;
	String address;
	
	Member() {}
	
	Member(String name, String phoneNo, String major, int grade, String email, String address) {
		 this.name = "홍길동";
		 this.phoneNo = "010-1010-000";
		 this.major = "도적";
		 this.grade = 100;
		 this.email = "ninga@lekde.com";
		 this.address = "한양 깊은산속";
	}
	public void MemberInfo() {
		System.out.println("모든 정보를 출력합니다.");
	}
	public static void main(String[] args) {
		Member member = new Member();
		
		
		
	}

}
