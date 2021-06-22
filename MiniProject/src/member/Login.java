package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
	private Connection conn; //Connection : 데이터베이스 접근하게 해주는 하나의 객체
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public Login() {
			
			try {
				
				// 1.드라이버 로드
				Class.forName("oracle.jdbc.driver.OracleDriver");
				System.out.println("드라이버 로드 성공");
			
				String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe"; //내일 다같이 모였을때 바꿀것.
				String user = "hr"; //""안에 들어있는 계정에 접근할 수있도록함
				String pw = "tiger"; //내일 다같이 모여서 넣어봐야할듯
				
				conn = DriverManager.getConnection(jdbcUrl, user, pw); //conn : getConnection(db URL, dbID, dbPassword)를 이용하여 DB에 접속하고 접속이 완료가 되면 conn 객체안에 접속된 정보가 담긴다
				System.out.println("데이터베이스 연결 성공");
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	}
}
