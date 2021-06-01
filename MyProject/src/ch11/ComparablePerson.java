package ch11;

public class ComparablePerson {

	public static void main(String[] args) {
			

	}

}
class Person implements Comparable<Person>{
	
	String name;
	int age;
	
	Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	public void showData() {
		System.out.printf("%s, %d \n", this.name, this.age);
	}
	@Override
	public int compareTo(Person o) {
		if(this.age > o.age) {
			return 10000;
		} else if(this.age < o.age) {
			return -1;
		} else {
			return 0;
		}
	}
	
}
