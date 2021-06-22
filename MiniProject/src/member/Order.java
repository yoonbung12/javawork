package member;

import java.io.Serializable;

public class Order implements Serializable{
	private int num;
	private String id;
	private String carname;
	private String date; //하루, 이틀, 삼일만 예약가능
	private int flag; //결제가 되었는지 아닌지 판단(1- 결제 완료, 0-결제 안함)
	
	public Order() {
		
	}
	public Order(String id, String carname, String date) {
		this.id = id;
		this.carname = carname;
		this.date = date;
		
	}
	public Order(int num, String id, String carname, String date, int flag) {
		this.num = num;
		this.id = id;
		this.carname = carname;
		this.date = date;
		this.flag = flag;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "Order [num=" + num + ", id=" + id + ", carname=" + carname + ", date=" + date + ", flag=" + flag + "]";
	}
	
	
}
