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
	<form action="memberReg.jsp" method="post" enctype="multipart/form-data"> 
			<table>
				<tr>
					<td>아이디</td>
					<td>
						<input type="text" name="memberid">
						<span id="msg" class="display_none"></span>
						<img id="loading" class="display_none" alt="loading" src="<c:url value="/image/loading.gif"/>">					
					</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="password"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="file" name="membername"></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<input type="submit">
						<input type="reset" >
					</td>
				</tr>
			</table>
		</form>
	
</body>
</html>