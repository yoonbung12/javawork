package ch09; ///****이거 아주 중요함 !!!!!

public class FriendMain {
	
	
	public static void main(String[] args) { 
	Friend f1 = new Friend("손흥민");
	Friend f2 = new Friend("이강인");
	Friend f3 = new Friend("손흥민");
	
	
	
	System.out.println(f1); //f1.toString()
	System.out.println(f2);
	System.out.println(f3);
	
	System.out.println(f1 == f3); //서로의 주소값이 다름.
	System.out.println(f1.equals(f3));
	
	
	}
}
class Friend {
	String myName;
	
	Friend(String name) {
		myName = name;
	}
	
	public String toString() {
		return "나의 이름은 " + myName + "입니다.";
	}
	//이거를 오버라이딩
	public boolean equals(Object o) {
		
		boolean result = false;
		
		if(o != null && o instanceof Friend) { //중요********
			Friend f = (Friend) o;
			if(myName.equals(f.myName)) {
				result = true;
			}
		} 
		return result;
	}
	
	
	
	
}