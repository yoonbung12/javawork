package absimple;

public abstract class AAA {
	void methodOne() {}
	abstract void methodTwo();
}
class BBB extends AAA {
	void methodTwo() {}	//abstract 를 상속하려면 추상메서드를 오버라이딩 해야함.
}
