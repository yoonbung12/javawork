package guest.domain;

public class MessageRequest { //beans

	private String guestname;
	private String password;
	private String message;
	
	
	public String getGuestname() {
		return guestname;
	}
	
	public void setGuestname(String guestname) {
		this.guestname = guestname;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	// MessageRequest => Message
	public Message toMessage() {
		return new Message(guestname, password, message);
	}

	@Override
	public String toString() {
		return "MessageRequest [guestname=" + guestname + ", password=" + password + ", message=" + message + "]";
	}
	
	
	
}
