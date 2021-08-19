<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

			<!-- http://localhost:8080/mvc/cookie/make -->
			
	<h1>쿠키가 생성되었습니다.</h1>
	
	<h3>username =  ${useerName }</h3>
	
	<a href="<c:url value='/' />">홈으로 가기 </a> <br>
	<a href="view">쿠기 정보 보기</a>
</body>
</html>