package member;



public class Member  { //implements Serializable 내일 확인후 지울지 말지
	
	private int membercode;
	private String id;
	private String pw;
	private String name;
	private String carreg;
	private String email;
	private String address;
	
	public Member(int membercode, String id, String pw, String name,  String carreg, String email, String address) {
		this.membercode = membercode;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.carreg = carreg;
		this.email = email;
		this.address = address;
	}
	

	public int getMembercode() {
		return membercode;
	}

	public void setMembercode(int membercode) {
		this.membercode = membercode;
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
		return "Member [membercode=" + membercode + ", id=" + id + ", pw=" + pw + ", name=" + name + ", carreg=" + carreg + ", email="
				+ email + ", address=" + address + "]";
	}
	
	
	
	
	
	
}
