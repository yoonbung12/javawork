<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page isErrorPage="true" %>


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

	<h1>요청하신 기능을 수행하는중 에러가 발생했습니다.<br>
		잠시후에 접속해주세요.</h1>
	<h3>
		<%= exception.getMessage() %> <br>
		<%= exception.getClass().getName().equals("java.lang.ArithmeticException") %>
		
	</h3>


</body>
</html>