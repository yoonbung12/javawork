<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Manager - MyPage</title>
</head>
<body>
				<h1>My Page</h1>
				<hr>
				
				<h3><c:out value="${loginInfo }"/></h3>
				<a href="<c:url value='/'/>">홈으로 가기</a>
			
</body>
</html>