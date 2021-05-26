package ch07;

public class Female  extends Person{
	private int height;
	
	public Female(String name, int birthYear, int height) {
		super("파이", 1988);
		this.height = height;
	}
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	public void hi() {
		System.out.println("안녕하세요. 저는" + this.name + " 입니다." + this.ageCal(birthYear)
				+ " 살 입니다.");
		
	}
	public void introduce() {
		System.out.println("이번 머니게임에 참가한" + this.name  + " 이고 " + " 키는" + this.height + "입니다.");
	}

	
}
