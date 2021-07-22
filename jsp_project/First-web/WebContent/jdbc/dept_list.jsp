<%@page import="java.util.ArrayList"%>
<%@page import="dept.domain.Dept"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// dept_list.jsp ->요청을 받고 처리	

	// 1. 드라이버 로드
	Class.forName("com.mysql.cj.jdbc.Driver");
	// 2. DB 연결
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	// jdbcUrl
	String jdbcUrl = "jdbc:mysql://localhost:3306/project?serverTimezone=UTC";
	String user = "bit";
	String pw = "bit";
	
	conn = DriverManager.getConnection(jdbcUrl, user, pw);
	
	// 3. Statement
	stmt = conn.createStatement();
	
	// sql 
	String sqlSelect = "select * from dept";
	
	// 4. ResultSet
	rs = stmt.executeQuery(sqlSelect);
	
	// 5.List<Dept> <- 결과
	List<Dept> deptList = new ArrayList<Dept>();
	
	while(rs.next()){
		// List에 객체 추가
		deptList.add(new Dept(
				rs.getInt("deptno"),
				rs.getString("dname"),
				rs.getString("loc")
				)
			);
	}
	//확인 
	out.println(deptList);
	
	// 6. 결과 데이터 request 의 속성에 저장 -> 데이터를 공유(전달)
	request.setAttribute("result", deptList);
	
	
%>	
  <jsp: forward page="List_view.jsp"/>  
   <!-- list_view.js : view 의 역할만!!!! -->

  
  