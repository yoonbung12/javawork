package com.bitcamp.mm.dto;

import java.sql.Timestamp;

public class Member {

	private int idx;
	private String memberid;
	private String password;
	private String membername;
	private String memberphoto;
	private Timestamp regdate;
	
	public Member() {}
	
	public Member(String memberid, String password, String membername, Timestamp regdate) {
		this.memberid = memberid;
		this.password = password;
		this.membername = membername;
		this.regdate = regdate;
	}
}
