package ex.io; //이것도 다시 한번!!!!

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class ObjectSerializable {
	
	public static void main(String[] args) {
		
		//인스턴스를 저장
		try {
			
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Object.ser"));
			
			out.writeObject(new Circle(1, 1, 2.4));
			out.writeObject(new Circle(3, 5, 7.9));
			out.writeObject("Hello");
			out.close();
			
			System.out.println("인스턴스 저장 완료");
			
			//인스턴스 복원
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("Object.ser"));
			
			//복원 순서는 저장순서에 맞게 복원
			try {
				Circle c1 = (Circle)in.readObject();
				Circle c2 = (Circle)in.readObject();
				String str = (String)in.readObject();
				
//				ArrayList<Circle> list2 = (ArrayList<Circle>) in.
				
				ArrayList<Circle> list = new ArrayList<>();
				list.add(new Circle(1, 2, 3.4));
				list.add(new Circle(5, 6, 7.8));
				list.add(new Circle(9, 12, 13.14));
				list.add(new Circle(21, 22, 23.24));
				list.add(new Circle(31, 32, 33.34));
				out.writeObject(list);
				
				System.out.println("복원된 인스턴스의 데이터를 출력");
				
				c1.showData();
				c2.showData();
				System.out.println(str);
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		} 
	}
}

class Circle implements Serializable {
	
	//implements Serializable : 직렬화 대상임을 의미
	int x;
	int y;
	double r;
	
	public Circle(int x, int y, double r) {
		this.x = x;
		this.y = y;
		this.r = r;
	}
	public void showData() {
		System.out.println("원점["+x+","+y+"]");
		System.out.println("반지름: " + r);
	}
}