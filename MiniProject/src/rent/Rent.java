package rent;

public class Rent {
	private int rentcode; //대여 번호
	private int pay; //대여 가격
	private int rentperiod; //대여기간 
	private String date; // 대여 날짜
	private int carcode; 
	private int membercode;
	private int managercode;
	
	public Rent (int rentcode, int pay, int rentperiod, String date, int carcode, int membercode, int managercode) {
		this.rentcode = rentcode;
		this.pay = pay;
		this.rentperiod = rentperiod;
		this.date = date;
		this.carcode = carcode;
		this.membercode = membercode;
		this.managercode = managercode;
	}

	public int getRentcode() {
		return rentcode;
	}

	public void setRentcode(int rentcode) {
		this.rentcode = rentcode;
	}

	public int getPay() {
		return pay;
	}

	public void setPay(int pay) {
		this.pay = pay;
	}

	public int getRentperiod() {
		return rentperiod;
	}

	public void setRentperiod(int rentperiod) {
		this.rentperiod = rentperiod;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getCarcode() {
		return carcode;
	}

	public void setCarcode(int carcode) {
		this.carcode = carcode;
	}

	public int getMembercode() {
		return membercode;
	}

	public void setMembercode(int membercode) {
		this.membercode = membercode;
	}

	public int getManagercode() {
		return managercode;
	}

	public void setManagercode(int managercode) {
		this.managercode = managercode;
	}

	@Override
	public String toString() {
		return "Rent [rentcode=" + rentcode + ", pay=" + pay + ", rentperiod=" + rentperiod + ", date=" + date
				+ ", carcode=" + carcode + ", membercode=" + membercode + ", managercode=" + managercode + "]";
	}
	
}
