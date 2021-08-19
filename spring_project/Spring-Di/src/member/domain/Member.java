package member.domain;

import java.sql.Date;

public class Member {
	
	private int id;
	private String email;
	private String password;
	private String name;
	private Date registDate;
	
	
	public Member(int id, String email, String password, String name, Date registDate) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.registDate = registDate;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}





	public String getPassword() {
		return password;
	}


	


	public String getName() {
		return name;
	}


	


	public Date getRegistDate() {
		return registDate;
	}


	
	
	// 비밀번호 변경 메소드
	public void changePassword(String oldPw, String newPw) throws Exception {
		
		if(password.equals(oldPw)) {
			password = newPw;
			
		} else {
			throw new Exception("비밀번호 불일치!");
		}
		
	}
	
	
}
