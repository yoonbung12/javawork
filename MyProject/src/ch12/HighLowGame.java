package ch12;

public class HighLowGame {

	public static void main(String[] args) {
		
	}

}
//1~100사이의 랜덤 한 숫자를 추출합니다.
//랜덤 숫자 클래스를 만듯것!!
class RandomNumber {
	int number;
	
	//
	public RandomNumber(int number) { 
		this.number = number;
	}
	public void setRandom(){
		System.out.println((int)(Math.random() * 100) + 1);
		
		
	}
}