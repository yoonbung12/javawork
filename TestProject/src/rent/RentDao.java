package rent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class RentDao {
		
		private RentDao() {
			
		}
		
		static private RentDao dao = new RentDao();
		
		public static RentDao getInstace() {
			return dao;
		}
		
		
		ArrayList<Rent> getRentList(Connection conn) {
			
			
			ArrayList<Rent> list = null;
			
			Statement stmt = null;
			ResultSet rs = null;
			
			
					try {
						stmt = conn.createStatement();
						String sql = "select * from rent_view order by rentcode";
						
						rs = stmt.executeQuery(sql);
						
						list = new ArrayList<>();
					
						//데이터를 Rent 객체로 생성 -> list에 저장
						while(rs.next()) {
							Rent r = new Rent(rs.getInt(1),
									rs.getInt(2),
									rs.getInt(3),
									rs.getString(4),
									rs.getInt(5),
									rs.getInt(6),
									rs.getInt(7));
							
							list.add(r);
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
		
		int insertRent(Connection conn, Rent rent) {
			
			int result = 0;
			
			//전달 받은 Rent 객체의 데이터로 Rent 테이블에 저장 -> 결과값을 반환
			PreparedStatement pstmt = null;
			
					
					try {
						String sql = "insert into rent_view values(Rent_rentcode_SEQ.nextval,"
								+ "?,?,?,"
								+ "1,1,1)";
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, rent.getPay());
						pstmt.setInt(2, rent.getRentperiod());
						pstmt.setString(3, rent.getSysDate());
						
						
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
		int editRent(Connection conn, Rent rent) {
			
			int result = 0;
			
			PreparedStatement pstmt = null;
			
					
					try {
						String sql = "update rent set pay=?, rentperiod=?, date=? where rentcode=?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, rent.getPay());
						pstmt.setInt(2, rent.getRentperiod());
						pstmt.setString(3, rent.getSysDate());
						pstmt.setInt(4, rent.getRentcode());
						
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
		
		int deleteRent(Connection conn, int rentcode) {
			
			int result = 0;
			
			PreparedStatement pstmt = null;
			
			
					try {
						String sql = "delete from rent where rentcode=?"; //pay
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, rentcode);
						
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
		//렌트 가격 지불
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
		//int PayTest2(Connection conn, int paymoney, int rentperiod,int pay) {
			
		
		
}
