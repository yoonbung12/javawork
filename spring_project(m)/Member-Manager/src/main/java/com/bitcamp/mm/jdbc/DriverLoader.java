package com.bitcamp.mm.jdbc;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class DriverLoader extends HttpServlet{
	private static final long serialVersionUID = -1658668375376163313L;
	
	@Override
	public void init(ServletConfig config) throws ServletException{
		
		String params = config.getInitParameter("driver");
		
		try {
				if(params != null) {
					Class.forName(params);
					System.out.println("드라이버 로드 성공");
				} else {
					Class.forName("com.mysql.jdbc.Driver");
					System.out.println("드라이버 로드 성공");
				}	
		}  catch(ClassNotFoundException e) {
			System.out.println("드라이버 로드 실패");
			e.printStackTrace();
		}
		
	}
	
}
