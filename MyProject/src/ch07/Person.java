package ch07;

public class Person {
	String name;
	int birthYear;
	
	Person(String name, int birthYear) {
		this.name = name;
		this.birthYear = birthYear;
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}
	public int ageCal(int birthYear) {
		int curentYear = 2021;
		int age = curentYear - birthYear +1;
		return age;
		
	}
	
	
	public void hi() {
		System.out.println("안녕하세요. 저는" + this.name + " 입니다." + ageCal(birthYear)
				+ " 살 입니다.");
	}
	public static void main(String[] args) {
		

	}

}
