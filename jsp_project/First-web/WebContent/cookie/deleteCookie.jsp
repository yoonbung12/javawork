<%@ page import = "util.CookieBox" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 쿠키의 삭제 : 쿠키 유지시간을 0으로 설정
	//Cookie c = new Cookie("cname", "");
	
	//c.setMaxAge(60 * 60 * 24 * 7);	//초 단위, 분, 시간, 일
	// 쿠키가 바로 소멸 : 삭제
	// c.setMaxAge(0);
	
	response.addCookie(CookieBox.makeCookie("name", "", "/", 0));
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
	<h1>쿠키가 삭제되었습니다.</h1>
</body>
</html>