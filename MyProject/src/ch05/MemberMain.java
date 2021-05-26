package ch05;

public class MemberMain {

	public static void main(String[] args) {
		Member member1 = new Member("철수", "010-0000-0000", "짱구친구", 4, "diem@gmail.com", "2000-10-10", "서울");
		//member.name = "영희";
		member1.setName("영희");
		member1.showInfo();
//		System.out.println(member1.getName());
	}

}
