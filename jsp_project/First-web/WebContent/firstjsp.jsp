<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 스크립트 영역
	java.util.Date now = new Date();
	//여기서 관리자 db사용해서 시간 넣어도됨.
%>

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
	<!-- 날짜와 시간을 출력  -->
	<%= now %>

</body>
</html>