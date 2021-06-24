package member;

import java.io.Serializable;

public class Order implements Serializable{
	
	private int num; //index
	private int  membercode;
	private String id;
	private String carname;
	private String date;
	private String time; // 몇시 까지 예약 (하루 단위로 24h)로 할것
	
	public Order() {}
	public Order(String id, String carname, String date, String time) {
		this.id = id;
		this.carname = carname;
		this.date =date;
		this.time = time;
	}
	
	public Order(int num, int membercode, String carname, String date, String time) {
		this.num = num;
		this.membercode = membercode;
		this.carname = carname;
		this.date = date;
		this.time = time;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getMembercode() {
		return membercode;
	}

	public void setMembercode(int membercode) {
		this.membercode = membercode;
	}

	public String getCarname() {
		return carname;
	}

	public void setCarname(String carname) {
		this.carname = carname;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "주문정보\n [주문번호=" + num + ", 멤버코드=" + membercode + ", id=" + id + ", carname=" + carname + ", date="
				+ date + ", time=" + time + "]";
	}

	
	
}
