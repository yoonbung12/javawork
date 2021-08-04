<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %> 
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
	<h1>회원 가입</h1>
	<hr>		 <!-- /web/member/login.jsp -->	

		<!-- action="/contextPath/member/login" --> 
			<table>
				<tr>
					<td>아이디</td>
					<td>${id}, ${uid}, ${loginRequest}</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td>${pw}, ${upw }, ${liginRequest.pw}</td>
				</tr>
				<tr>
					<td>이름</td>
					<td></td>
				</tr>
				
			</table>
		
	
</body>
</html>