package ch02;

public class Member {
	String name;
	String phoneNo;
	String major;
	int grade;
	String email;
	String address;
	String birthday;
	
	Member(String name, String phoneNo, String major, int grade, String email, String address) {
		this.name = name;
		this.phoneNo = phoneNo;
		 this.major = major;
		 this.grade = 100;
		 this.email = email;
		 this.address = address;
		 
	}
	
	Member(String name, String phoneNo, String major, int grade, String email, String address, String birthday) {
		this.name = name;
		this.phoneNo = phoneNo;
		 this.major = major;
		 this.grade = 100;
		 this.email = email;
		 this.address = address;
		 this.birthday = birthday;
	}
	public void MemberInfo() {
		System.out.println("모든 정보를 출력합니다.");
		System.out.println("이름 :" + name);
		System.out.println("전화번호" + phoneNo);
		System.out.println("학년 :" + grade);
		System.out.println("이메일 주소 : " + email);
		System.out.println("주소 :" + address);
		System.out.println("생일 :" + birthday);
	}
	public static void main(String[] args) {
		Member member = new Member("연개소문", "000-0000-0000", "도적", 100, "sdfow@adf.com", "평양 어딘가");
		member.MemberInfo();
		
		System.out.println();
		Member member1 = new Member("홍길동", "010-1010-000", "도적", 100, "sdie@diene.com", "한양깊은산속", "07월01일");
		member1.MemberInfo();
		
		
	}

}
