package ch07;

public class Person {
	String name;
	String regisNumber;
	int age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRegisNumber() {
		return regisNumber;
	}
	public void setRegisNumber(String regisNumber) {
		this.regisNumber = regisNumber;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	Person(String name, String regisNumber, int age) {
		this.name = name;
		this.regisNumber = regisNumber;
		this.age = age;
	}
	public void hi() {
		System.out.println("안녕하세요. 저는" + this.name + " 입니다." + this.age
				+ " 살 입니다.");
	}
	public static void main(String[] args) {
		

	}

}
