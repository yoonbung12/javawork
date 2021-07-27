package guest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import guest.domain.Message;
import guest.jdbc.JdbcUtil;

public class MessageDao {
	
	//싱글톤 처리
	private MessageDao() {} //접근 제어자
	
	private static MessageDao dao = new MessageDao(); //전역 변수
	
	public static MessageDao getInstance() {
		return dao;
	}

	public int writeMessage(Connection conn, Message message) throws SQLException {
		
		int resultCnt = 0;
		PreparedStatement pstmt = null;
		
		String sql ="insert into project.guestbook_message (guestname, password, message)\r\n"
				+ "values (?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, message.getGuestname());
			pstmt.setString(2, message.getPassword());
			pstmt.setString(3, message.getMessage());
			resultCnt = pstmt.executeUpdate();
			
		} finally {
			JdbcUtil.close(pstmt); //다형성 가능
			// close
		}
		
		
		return resultCnt;
	}

}
