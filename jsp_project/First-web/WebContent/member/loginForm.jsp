<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
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
	<h1>LoginForm</h1>
	<hr>		 <!-- /web/member/login.jsp -->	
	<form action="<c:url value="/member/Login.jsp"/>">	<!-- /web/member/mypage/mypage.jsp -->
		<table>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="id">
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pw">
			</tr>
			<tr>
				<td></td>
				<td><input type="submit">
			</tr>
			
		</table>
	
	</form>
</body>
</html>