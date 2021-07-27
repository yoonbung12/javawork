package guest.exception;

public class MessageNotFoundException extends Exception{

	public MessageNotFoundException(int mid) {
		super(mid + "번 게시물은 존재하지않습니다.");
		
	}


	
}
