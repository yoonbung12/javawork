package ch07;

public class Male extends Person{	
	private String gender;
	
	public Male(String name, String regisNumber, int age, String gender) {
		super("엄준식", "993939-99939", 29);
		this.gender = gender;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	//오버라이딩
	public void hi() {
		System.out.println("안녕하세요. 저는" + this.name + " 입니다." + this.age
				+ " 살 입니다.");
		System.out.println("저는 " + this.name + " 이고 " + "성은" + this.gender + "입니다.");
		System.out.println(this.name + "은 살아 있다.");
	}
	
	public static void main(String[] args) {
		
		

	}

}
