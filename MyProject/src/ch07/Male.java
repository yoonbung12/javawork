package ch07;

public class Male extends Person{	
	private String gender;
	
	public Male(String name, int birthYear, String gender) {
		super("엄준식", 1999);
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
		System.out.println("안녕하세요. 저는" + this.name + " 입니다." + this.ageCal(birthYear)
				+ " 살 입니다.");
		System.out.println(this.name + "은 살아있습니다..");
	}
	public void introduce() {
		System.out.println("저는 " + this.name + " 이고 " + "성은" + this.gender + "입니다.");
	}
	
	public static void main(String[] args) {
		
		

	}

}
