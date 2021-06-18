package jdbc;

public class JDBCTest {

	public static void main(String[] args) {
		
		//1.드라이버 로드
		try {
			Class.forName("Oracle.jdbc.driver.oracleDriver");
		
			System.out.println("드라이버 로드 성골!");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2.연결
		//3.sql처리
		//4.close

	}

}
