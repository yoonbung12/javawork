<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta  http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value='/css/default.css'/>">

<style>
</style>
<script>
</script>
</head>
<body>

	<%@ include file="/WEB-INF/frame/header.jsp" %>
	
	<%@ include file="/WEB-INF/frame/nav.jsp" %>
	
	<div class="contents">
		
		<h2>Login</h2>
		<hr>
		
		<form action="<c:url value='/login.jsp'/>" method="post">
		
				<table>
						<tr>
							<th>ID</th> <%-- =reid --%>
							<td><input type="text" name="memberid" value="${cookie.reid.value}"></td>
						</tr>
						<tr>
							<th>PW</th>
							<td><input type="password" name="password"></td>
						</tr>
						<tr>
							<th></th><%-- =checked --%>
							<td>
							<input type="checkbox" name="reid" value="on" ${cookie.reid ne null	? 'checked' : ''}>
							아이디 기억하기
							</td>
						</tr>
						<tr>
							<th></th>
							<td><input type="submit"></td>
						</tr>
				</table>
		
		
		</form>
	
	</div>
</body>
</html>