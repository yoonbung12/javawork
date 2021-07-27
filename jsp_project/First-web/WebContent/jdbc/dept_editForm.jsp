<%@page import="java.sql.SQLException"%>
<%@page import="dept.dao.DeptDao"%>
<%@page import="jdbc.util.ConnectionProvider"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	// 사용자가 전달하는 부서번호 받기
	String deptno = request.getParameter("deptno");
	
	//out.println(deptno);
	
	// 전달받은 부서번호로 부서정보를 가져온다 -> 처리 -> Dept -> 공유
	// 1. 드라이버 로드 : 서블릿클래스 Loader에서 드라이버 로드

	// 2. DB 연결
	Connection conn = null;
	DeptDao dao = null;
	
	try{
		conn = ConnectionProvider.getConnection();
		dao = DeptDao.getInstance();
	
		// 부서정보를 form_view.jsp 전달(공유)
		request.setAttribute("dept", dao.selectByDeptno(conn, Integer.parseInt(deptno)));
	} catch(SQLException e){
		e.printStackTrace();
	}
	
	

	
%>
<jsp:forward page="form_view.jsp"/>