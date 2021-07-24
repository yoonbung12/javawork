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
	
	// 전달받은 부서번호로 부서정보를 가져온다
	// 1. 드라이버 로드 : 서블릿클래스 Loader에서 드라이버 로드

	// 2. DB 연결
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	conn = ConnectionProvider.getConnection();
	
	Dept dept = null;
	
	String  sqlSelect = "select * from dept where deptno = ?";
	pstmt = conn.prepareStatement(sqlSelect);
	pstmt.setInt(1, Integer.parseInt(deptno));
	
	rs = pstmt.executeQuery();
	
	if(rs.next()){
		dept = new Dept();
		dept.setDeptno(rs.getInt("deptno"));
		dept.setDname(rs.getString("dname"));
		dept.setLoc(rs.getString("loc"));
	}
	
	// out.println(dept);
	
	// 부서정보를 form_view.jsp 전달(공유)
	request.setAttribute("dept", dept);

	
%>
<jsp:forward page="form_view.jsp"/>