<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h3>
		
		<c:url value="index.jsp" var="urlIndex"/>
		${urlIndex}	
		<br>
		<c:url value="index.jsp" /> <br>
		
		<a href="<c:url value="index.jsp" />">홈으로 가기</a>
		
		<c:url value="/index.jsp " var="homeUrl">
			<c:param name="type">main</c:param>
			<c:param name="index">1</c:param>
		</c:url>
	
		<a href="${homeUrl}">${homeUrl}</a> 
	
	</h3>
	
	
	
</body>
</html>