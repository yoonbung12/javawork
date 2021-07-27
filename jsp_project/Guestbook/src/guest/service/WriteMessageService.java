package guest.service;

import java.sql.Connection;
import java.sql.SQLException;

import guest.dao.MessageDao;
import guest.domain.Message;
import guest.domain.MessageRequest;
import guest.jdbc.ConnectionProvider;

public class WriteMessageService {
	
	// 메시지를 DB에 쓰고 처리된 결과 생성
	public int writeMessage(MessageRequest requestMessage) {
		
		int resultCnt = 0;
		
		// 트랜젝션 처리를 위해서 생성
		Connection conn = null;
		MessageDao dao = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			dao = new MessageDao();
			
			Message message = requestMessage.toMessage();
			// 데이터 전처리가 필요한 부분은 처리
			//..........
			//..........
			
			resultCnt = dao.writeMessage(conn, message);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return resultCnt;
	}
	
}
