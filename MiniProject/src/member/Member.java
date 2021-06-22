package member;



public class Member  { //implements Serializable 내일 확인후 지울지 말지
	
	private int idx;
	private String id;
	private String pw;
	private String name;
	private String carreg;
	private String email;
	private String address;
	
	public Member(int idx, String id, String pw, String name,  String carreg, String email, String address) {
		this.idx = idx;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.carreg = carreg;
		this.email = email;
		this.address = address;
	}
	

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCarreg() {
		return carreg;
	}

	public void setCarreg(String carreg) {
		this.carreg = carreg;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Member [idx=" + idx + ", id=" + id + ", pw=" + pw + ", name=" + name + ", carreg=" + carreg + ", email="
				+ email + ", address=" + address + "]";
	}
	
	
	
	
	
	
}
