<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<%@ include file="include/header.jsp" %>
	<%--@ include file="WEB-INF/header.jsp" --%>
	<div id="header">
	<hr>
	<%@ include file="include/nav.jsp" %>
		<h1>include를 이용한 지시어 테스트</h1>
	</div>
	<div id="nav">[게임][쇼핑][뉴스]</div>
	<div id="wrap">
		<%@ include file="include/news.jsp" %>
		<%@ include file="include/shopping.jsp" %>
		<div id="news">
			<h3>[최신 뉴스]</h3>
			<hr>
			<p>코로나 발생 현황</p>
		</div>
		<div id="shopping">
			<h3>[쇼핑정보] 인기상품</h3>
			<p>이것은 아이폰 13 입니다.</p>
		</div>
	</div>
</body>
</html>