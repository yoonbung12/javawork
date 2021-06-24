package member;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Service {
	
	Member member;
	Order order;
	
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	
	public Order addOrder(Scanner sc) { // 차 이름, 대여날짜 입력
		System.out.println("<주문>");
		System.out.println("대혀하고 싶은 차의 이름을 입력하세요\n 종류");
		String carname = sc.next();
		
		//car 체크
		if(carCheck(carname) == null) {
			System.out.println("잘못된 차 이름입니다. 다시 입력하세요.");
			addOrder(sc);
		}
		System.out.println("대여하고 싶은 날짜를 입력하세요.");
		String date = sc.next();
		
		order = new Order(1, member.getMembercode(), carname, date, "" 	);
		return order;
	
	}
	
	public String addTime(Scanner sc, String time) { //대여시간 선택
		int start = 0, end = 0;
		
		while(start <0 || end> 23 || start>= end) {
				
				System.out.println("대여시간 선택");
				System.out.println("현재 대여가능 시간:" + time);
				System.out.println("대여 희망시간:");
				System.out.println("대여시작 시간을 입력하세요.");
				start = sc.nextInt();
				System.out.println("차량 반납 시간을 입력하세요.");
				end = sc.nextInt();
				
				
		}
			String time2  = "";
			time2 += start;
			for(int i = start +1; i < end; i++) {
				time2 += "," + i;
			}
			return time2;
		
	}
	
	//차 체크
	public String carCheck(String carname) {
		String[] cars = {"Ray"};
		for(int i =0; i< cars.length; i++) {
			if(carname.equals(cars[i])) {
				return carname;
			}
		}
		return null;
	}
	public void orderCheck(Scanner sc, String time) {
		if(time == null) {
			System.out.println("잘못된 주문을 입력하였습니다.");
			addOrder(sc);
		}
	}
	
	public boolean payCheck(Scanner sc) { //바로 결제 확인
		System.out.println("결제 창으로 넘어가겠습니까?");
		System.out.println("1.YES 2.NO");
		int op = sc.nextInt();
		while(true) {
			switch(op) {
			case 1: 
				return true;
			case 2:
				return false;
				default:
					System.out.println("잘못된 번호를 입력했습니다.");
			}
		}
	}
	public void PrintAll() { //주문 목록 출력
		ArrayList<Order> list;
		Iterator<Order> iterator;
		System.out.println("주문 목록 출력");
		
		
		
		
	}
}
