<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
	// 현재 세션 객체 소멸
	session.invalidate(); //6교시 start
%>

<!DOCTYPE html>
<html>
<head>
<meta  http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
</style>
<script>
	alert('로그아웃 되었습니다.');
	location.href='<%= request.getContextPath()%>';
</script>
</head>
<body>

</body>
</html>