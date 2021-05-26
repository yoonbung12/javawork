package ch07;

public class Female  extends Person{
	private int height;
	
	public Female(String name, String regisNumber, int age, int height) {
		super("파이", "880000-2323", 30);
		this.height = height;
	}
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	public void hi() {
		System.out.println("안녕하세요. 저는" + this.name + " 입니다." + this.age
				+ " 살 입니다.");
		System.out.println("이번 머니게임에서 나락으로간" + this.name  + "입니다.");
	}

	
}
