package guest.service;

import java.sql.Connection;
import java.sql.SQLException;

import guest.dao.MessageDao;
import guest.domain.Message;
import guest.exception.InvalidPasswordException;
import guest.exception.MessageNotFoundException;
import guest.jdbc.ConnectionProvider;
import guest.jdbc.JdbcUtil;


public class DeleteMessageService {
	
	// 싱글톤 처리
	private DeleteMessageService() {}
	private static DeleteMessageService service = new DeleteMessageService();
	public static DeleteMessageService getInstance() {
		return service;
	}
	
	public int deleteMessage(int mid, String pw) 
				throws SQLException, MessageNotFoundException, InvalidPasswordException {
		
		int resultCnt = 0;
		
		Connection conn = null;
		MessageDao dao = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			dao = MessageDao.getInstance();
			
			// 트렌젝션 시작
			conn.setAutoCommit(false);
			
			
			// 전달받은 mid로 게시물을 검색
			Message message =  dao.selectByMid(conn, mid);
			
			if(message == null) {
				//throw new Exception("게시물이 존재하지 않습니다.");
				throw new MessageNotFoundException(mid);
			} else {
				
				if(message.getPassword().equals(pw)) {
					// 삭제
					resultCnt = dao.deleteMessage(conn, mid);
					// 트렌젝션 성공
					conn.commit();
				} else {
					// 비밀번호가 일치하지 않음으로 예외 발생!!!
					throw new InvalidPasswordException();
				}
			}
			
			
		} catch(SQLException e) {	
				e.printStackTrace();
				JdbcUtil.rollback(conn);
				throw e; 
		} catch(MessageNotFoundException e) {
				e.printStackTrace();
				JdbcUtil.rollback(conn);
				throw e;
		
		} catch(InvalidPasswordException e) {		
				e.printStackTrace();
				JdbcUtil.rollback(conn);
				throw e;			
		}finally {
				JdbcUtil.close(conn);
		}
		
		return resultCnt;
		
	}
}
