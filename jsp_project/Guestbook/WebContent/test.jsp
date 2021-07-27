<%@page import="java.sql.Connection"%>
<%@page import="guest.jdbc.ConnectionProvider"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta  http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
</style>
<script>
</script>
</head>
<body>
	<%
		Connection con = ConnectionProvider.getConnection();
		out.print(con);
	%>
</body>
</html>