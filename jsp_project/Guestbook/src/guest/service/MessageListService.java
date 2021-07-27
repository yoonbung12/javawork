package guest.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import guest.dao.MessageDao;
import guest.domain.Message;
import guest.domain.MessageListView;
import guest.jdbc.ConnectionProvider;

public class MessageListService {

	// 한 페이지에 표현할 메시지의 개수
	private final int messageCountPerPage = 3;
	
	private MessageListService() {}	
	private static MessageListService service = new MessageListService();
	public static MessageListService getInstance() {
		return service;
	}
	
	public MessageListView getMessageList(int pageNumber) {
		
		MessageListView listView = null;
		
		Connection conn = null;
		MessageDao dao = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			dao = MessageDao.getInstance();
			
			// 전체 게시물의 개수
			int totalMessageCount = dao.selectAllCount(conn);
			System.out.println("totalCount : " + totalMessageCount);
			
			// 페이지에 표현할 메세지 객체들 List<Message>
			List<Message> messageList = null;
			
			// 시작행의 위치값 limit index
			int firstRow = (pageNumber-1)* messageCountPerPage; // firstRow = 0부터 시작
			
			messageList = dao.selectMessageList(conn, firstRow, messageCountPerPage);
			System.out.println("message List : " + messageList);
			
			listView = new MessageListView(
					messageList,
					totalMessageCount,
					pageNumber,
					messageCountPerPage,
					firstRow,
					0);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return listView;
	}
}
