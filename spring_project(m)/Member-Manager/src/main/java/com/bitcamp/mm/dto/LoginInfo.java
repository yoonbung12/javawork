package com.bitcamp.mm.dto;

public class LoginInfo {

	private int idx;
	private String memberid;
	private String membername;
	private String memberphoto;
	
	public LoginInfo(int idx, String memberid, String membername, String memberphoto) {
		this.idx = idx;
		this.memberid = memberid;
		this.membername = membername;
		this.memberphoto = memberphoto;
	}

	public int getIdx() {
		return idx;
	}

	public String getMemberid() {
		return memberid;
	}

	public String getMembername() {
		return membername;
	}

	public String getMemberphoto() {
		return memberphoto;
	}

	@Override
	public String toString() {
		return "LoginInfo [idx=" + idx + ", memberid=" + memberid + ", membername=" + membername + ", memberphoto="
				+ memberphoto + "]";
	}
	
	
	
}
