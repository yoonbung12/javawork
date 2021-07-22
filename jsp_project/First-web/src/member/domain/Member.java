package member.domain;

public class Member {
	
	private int idx;
	private String id;
	private String pw;
	private String name;
	private String regDate;
	
	public Member(){}
	
	//member table에 등록하기 위한 생성자
	public Member(String id, String pw, String name) {
		this.id = id;
		this.pw = pw;
		this.name = name;
	}
	
	public Member(int idx, String id, String pw, String name) {
		this.idx = idx;
		this.id = id;
		this.pw = pw;
		this.name = name;
	}
	
	//member table에 리스틀 가져오기 위한 생성자
	public Member(int idx, String id, String pw, String name, String regDate) {
		this.idx = idx;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.regDate = regDate;
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

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Member [idx=" + idx + ", id=" + id + ", pw=" + pw + ", name=" + name + ", regDate=" + regDate + "]";
	}
	
	
	
}
