package domain;

public class LoginInfo {
	//6교시
	private int idx;
	private String memberId;
	private String memberName;
	
	public LoginInfo(int idx, String memberId, String memberName) {
		super();
		this.idx = idx;
		this.memberId = memberId;
		this.memberName = memberName;
	}

	public int getIdx() {
		return idx;
	}

	public String getMemberId() {
		return memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	@Override
	public String toString() {
		return "LoginInfo [idx=" + idx + ", memberId=" + memberId + ", memberName=" + memberName + "]";
	}
	
	
	
	
	

}

