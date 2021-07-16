<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta  http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/First-web/blog/css/myblog.css">
</head>
<body>
	<div id="main_wrap">
		<%@ include file="module/header.jsp" %>
		<%@ include file="module/nav.jsp" %>
		
		<!-- 컨텐츠 영역 시작 -->
		<div id="content_wrap">
			<%@ include file="module/section.jsp" %>
			<%@ include file="module/aside.jsp" %>
		
		</div>
		<!-- 컨텐츠 영역 끝 -->
			<%@ include file="module/footer.jsp" %>
	
	</div>
</body>
</html>