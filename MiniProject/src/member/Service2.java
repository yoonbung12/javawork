package member;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Service2 {
		
	private Socket socket;
	
	private PrintWriter out;
	private BufferedReader br;
	private ObjectInputStream din;
	private ObjectOutputStream oo;
	
	Member member;
	Order order;
	
	public Service2() {

	}
	
	public Service2(Socket socket, PrintWriter out, BufferedReader br, ObjectInputStream din, ObjectOutputStream oo) {
		this.socket = socket;
		this.out = out;
		this.br = br;
		this.din = din;
		this.oo = oo;
	}
	
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	
	public Order addOrder(Scanner sc) {
			System.out.println("주문");
			System.out.println("대여하고 싶은 차의 이름을 입력하세요\n 종류: 모닝, 아반떼, 투산 ");
			String carname = sc.next();
			
			
			//차 체크
			if(carCheck(carname) == null) {
				System.out.println("잘못된 차 이름을 입력하셨습니다. 다시 입력해주세요.");
				addOrder(sc);
			}
				
			
			System.out.println("대여하고 싶은 알짜를 입력하세요, 대여할 날짜를 입력하세요.");
			String date = sc.next();
			
			order = new Order(10, member.getId(), carname, date,  0);
			return order;
			
	
	}
	
	//차 체크
	public String carCheck(String carname) {
		String[] cars = {"morning", "avante", "tucson"};
		for(int i=0; i< cars.length; i++) {
			if(carname.equals(cars[i])) {
				return carname;
			}
		}
		return carname;
	}
	
	public boolean payCheck(Scanner sc) { //바로결제여부 확인
		System.out.println("결제창으로 넘어가시겠습니까?(번호를 입력해주세요.)");
		System.out.println("1.yes 2.No");
		int op = sc.nextInt();
		while (true) {
			switch(op) {
			case 1: //바로 결제 => pay
				return true;
			case 2:
				return false;
				default:
					System.out.println("잘못된 번호를 입력하셨습니다.");
			}
		}
		
	}
	public void PrintAll() { //전체 주문목록 출력
		ArrayList<Order> list;
		Iterator<Order> iterator;
		System.out.println("주문 목록 출력");
		
			
			try {
				out.println("/PrintAll");
				oo.writeObject(member);
				
				list = (ArrayList<Order>) din.readObject();
				if(list.size() != 0) {
					iterator = list.iterator();
					while(iterator.hasNext()) {
						order = (Order) iterator.next();
						System.out.println(order);
					}
					
				} else {
					System.out.println("주문이 없습니다.");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
//	public int selectPay(Scanner sc, int orderNum) { //메뉴에서 결제 선택한 경우
//		boolean flag = printAvailable();
//		if(flag) {
//			System.out.println("결제할 주문번호를 선택하세요.");
//			orderNum = sc.nextInt();
//		}
//		return orderNum;
	//}
	
	public boolean order(Scanner sc) { //주문 전체 메서드
		String time;
		boolean flag = true;
		order = addOrder(sc); //1. 주문정보 입력받기
		
			
			try {
				out.println("/order");
				oo.writeObject(order);
				
				//time = br.readLine(); //가능 시간
				//orderCheck(sc, time); // 3.반환된 String 확인(주문 올바른지 확인)
				
				
				//time = addTime(sc, time); //4대여시간 입력받기
				//order.setTime(time);
				
				Order neworder = new Order(45, order.getId(), order.getCarname(), order.getDate(), 0);
			
				oo.writeObject(neworder); //완성된 객체 보내기
				
				String result = br.readLine();
				if(result.equals("true")) {
					System.out.println("주문이 완료되었습니다.");
				} else {
					System.out.println("주문이 실패했습니다.");
				}
			
				// 4.바로 결제 여부
				flag = payCheck(sc);
				return flag;
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
	}
	
	
	
}
