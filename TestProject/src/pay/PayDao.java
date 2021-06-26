package pay;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PayDao {
	
	private PayDao() {
		
	}
	
	static private PayDao dao = new PayDao();
	
	public static PayDao getInstance() {
		return dao;
	}
	
	ArrayList<Pay> getPayList(Connection conn) {
		
		ArrayList<Pay> list = null;
		
		Statement stmt = null;
		ResultSet rs = null;
		
			try {
				stmt = conn.createStatement();
				String sql = "select * from pay_view";
				
				rs = stmt.executeQuery(sql);
				
				list = new ArrayList<>();
				
				//데이터 pay 객체로 생성
				while(rs.next()) {
					Pay p = new Pay(rs.getInt(1),
							rs.getInt(2),
							rs.getString(3),
							rs.getInt(4));
					
					list.add(p);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				
				if(rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(stmt != null) {
						try {
							stmt.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
			}
			return list;
		
	}
	
	
	int insertPay(Connection conn, Pay pay) {
		
		int result = 0;
		
		//전달 받은 pay 객체의 데이터로 Pay 테이블에 저장 -> 결과값을 반환
		PreparedStatement pstmt = null;
		
			
						try {
							String sql = "insert into pay_view values(Pay_paycode_SEQ.nextval,?,?" 
									+ "?"
									+ "1)";
							pstmt = conn.prepareStatement(sql);
							pstmt.setInt(1, pay.getPaymoney());
							pstmt.setString(2,  pay.getPaysucc());
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} finally {
							if(pstmt != null) {
									try {
										pstmt.close();
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
							}
						}
						return result;
			
		}
		
	int editPay(Connection conn, Pay pay) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
				
				try {
					String sql = "update pay set paymoney=?, paysucc=? where paycode=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, pay.getPaymoney());
					pstmt.setString(2, pay.getPaysucc());
					pstmt.setInt(3, pay.getPaycode());
					
					result = pstmt.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
						if(pstmt != null) {
								try {
									pstmt.close();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
						}
				}
				return result;	
		}
		int deletePay(Connection conn, int paycode) {
			
			int result = 0;
			
			PreparedStatement pstmt = null;
			
					
					try {
						String sql = "delete from pay where paycode=?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, paycode);
						
						result = pstmt.executeUpdate();
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
							if(pstmt != null) {
								
								try {
									pstmt.close();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
					}
					return result;
		} ////여기 서 어떻게 결제를 확일할지 어떤걸 써야 할지.
		// 렌트테이블의 결제를 true, false로 ? 
		int PayTest(Connection conn,int paymoney ,int rentperiod,int pay ) { //렌트에 페이 있는것사용? 
			int result = 0;
			
			//전달 받은 Rent 객체의 데이터로 테이블에 저장 -> 결과값 반환
			PreparedStatement Rstmt = null;
			
				
				
				
				
				try {
					String sql = "select paymoney=? from pay where paymoney= (select rentperiod=? * pay=? from rent";
					Rstmt = conn.prepareStatement(sql);
					Rstmt.setInt(1, rentperiod);
					Rstmt.setInt(2, pay);
					
					result = Rstmt.executeUpdate();
					
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
						if(Rstmt != null) {
							try {
								Rstmt.close();
							} catch(SQLException e) {
								e.printStackTrace();
							}
						}
				}
				return result;
		
		}
}
