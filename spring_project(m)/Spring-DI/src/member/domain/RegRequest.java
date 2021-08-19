package member.domain;

public class RegRequest {
	
	private String email;
	private String password;
	private String confirmPassword;
	private String name;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	// 비밀번호와 확인 비밀번호가 일치하는지 여부 확인 메소드
	public boolean isPasswordEqualToConfirmPassword() {
		return password.equals(confirmPassword);
		
	
	}
	
	
}
