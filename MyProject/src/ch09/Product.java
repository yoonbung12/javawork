package ch09;

public class Product {
	
	final int price;
	int bonusPoint;
	
	Product(int price) {
		this.price = price;
		bonusPoint = (int)(price/10.0);
	}
	public static void main(String[] args) {
		

	}

}
