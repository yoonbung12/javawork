package ch06;

public class StudentMain {

	public static void main(String[] args) {
		Student[] students = new Student[10];
		
		//Student 타입의 인스턴스를 생성하고 배열에 저장하는 코드를 정의해봅시다.
		students[0] = new Student("홍길동", 100, 40, 50);
		students[1] = new Student("엄홍길", 60, 10, 50);
		students[2] = new Student("안동", 70, 40, 50);
		students[3] = new Student("주드", 80, 30, 50);
		students[4] = new Student("홍1", 100, 10, 60);
		students[5] = new Student("홍길동1", 90, 60, 70);
		students[6] = new Student("홍길동2", 10, 80, 10);
		students[7] = new Student("홍길동3", 20, 90, 80);
		students[8] = new Student("홍길동4", 30, 90, 10);
		students[9] = new Student("홍길동5", 40, 10, 30);
		
		
		System.out.println("이름\t\t 국어\t 영어\t 수학\t 총점\t 평균");
		System.out.println("00000000000000000000000000000000");
		for(int i = 0; i < students.length; i++) {
			
			System.out.print(students[0].getKor());
			System.out.print("\t");
			System.out.print(students[0].getEng());
			System.out.print("\t");
			System.out.print(students[i].getMath());
			System.out.print("\t");
			System.out.print(students[i].getAvg());
		}
		
		
		
	}

}
