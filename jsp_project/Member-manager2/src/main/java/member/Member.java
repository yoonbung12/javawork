package member;

public class Member {
	
	private int idx;
	private String memberId;
	private String password;
	private String memberName;
	private String memberPhoto;
	private String regDate;
	
	public Member(int idx, String memberId, String password, String memberName, String memberPhoto, String regDate) {
		this.idx = idx;
		this.memberId = memberId;
		this.password = password;
		this.memberName = memberName;
		this.memberPhoto = memberPhoto;
		this.regDate = regDate;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberPhoto() {
		return memberPhoto;
	}

	public void setMemberPhoto(String memberPhoto) {
		this.memberPhoto = memberPhoto;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Member [idx=" + idx + ", memberId=" + memberId + ", password=" + password + ", memberName=" + memberName
				+ ", memberPhoto=" + memberPhoto + ", regDate=" + regDate + "]";
	}
	
	
	
	
}
