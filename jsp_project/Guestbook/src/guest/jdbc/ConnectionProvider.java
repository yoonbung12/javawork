package guest.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	
	public static Connection getConnection() throws SQLException { // 일괄적으로 예외처리
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/project?serverTimezone=UTC";
		String user = "bit";
		String pw = "bit";
		
		Connection conn = null;
		
		conn = DriverManager.getConnection(jdbcUrl, user, pw);
		
		return conn;
	}
}
