package ch05;

public class Student {
	
	private String name;
	private int koScore;
	private int enScore;
	private int matScore;
	
	Student(String name, int koScore, int enScore, int matScore) {
		this.name = name;
		this.koScore = koScore;
		this.enScore = enScore;
		this.matScore = matScore;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int koScore() {
		return koScore;
	}
	public void setKoScore(int koScore) {
		this.koScore = koScore;
	}
	public int getEnScore() {
		return enScore;
	}
	public void setEnScore(int enScore) {
		this.enScore = enScore;
	}
	public int getMatScore() {
		return matScore;
	}
	public void setMatScore(int matScore) {
		this.matScore = matScore;
	}
	public int totalScore() {
		return koScore + enScore + matScore;		
	}
	public double avg() {
		return (koScore + enScore + matScore)/3;
	}
	public void personInfo() {
		System.out.println("이름" + name);
		System.out.println("국어" + koScore );
		System.out.println("영어" + enScore);
		System.out.println("총점" + totalScore());
		System.out.println("평균" + avg());
		System.out.println("&&&&&&&&&&");
		
	}
	
	public static void main(String[] args) {
		//국어, 영어, 수학점수 10개씩 저장하는 배열을 정의
		Student[] stu = new Student[10];
		stu[0] = new Student("김재", 40, 30, 20);
		stu[1] = new Student("1", 50, 20, 40);
		stu[2] = new Student("2", 30, 40, 50);
		stu[3] = new Student("3", 40, 40, 60);
		stu[4] = new Student("4", 50, 20, 10);
		stu[5] = new Student("5", 60, 70, 90);
		stu[6] = new Student("6", 60, 90, 90);
		stu[7] = new Student("7", 80, 90 ,90);
		stu[8] = new Student("8", 10, 20, 50);
		stu[9] = new Student("9", 40, 30, 20);
		
		for(int inx = 0; inx <stu.length; inx++) {
			stu[inx].personInfo();
		}

	}

}
