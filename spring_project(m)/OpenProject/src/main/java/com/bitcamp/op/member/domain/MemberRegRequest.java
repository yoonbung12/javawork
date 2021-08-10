package com.bitcamp.op.member.domain;

import org.springframework.web.multipart.MultipartFile;

public class MemberRegRequest {

	private String memberid;
	private String password;
	private String membername;
	private MultipartFile photo;
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMembername() {
		return membername;
	}
	public void setMembername(String membername) {
		this.membername = membername;
	}
	public MultipartFile getPhoto() {
		return photo;
	}
	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}
	@Override
	public String toString() {
		return "MemberRegRequest [memberid=" + memberid + ", password=" + password + ", membername=" + membername
				+ ", photo=" + photo.getOriginalFilename() + "]";
	}
	
	// MemberRegRequest -> Member
	public Member toMember() {
		return new Member(
				0,
				memberid,
				password,
				membername,
				photo.getOriginalFilename(),
				null);
	}

}
