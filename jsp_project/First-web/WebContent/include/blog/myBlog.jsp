<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta  charset="UTF-8">
<title>My Blog</title>
<link rel="stylesheet" href="css/myblog.css">
<style>
</style>
<script>
</script>
</head>
<body>
	<div id="total_wrap">
	 	<!-- header -->
	 	<%@ include file="header.jsp" %>
	 	<!-- header -->
	 	
	 	<!-- nav -->
	 	<%@ include file="nav.jsp" %>
	 	
	 	<!-- 컨텐츠 영역 -->
	 	<div >
	 		<!-- section영역 -->
	 			<%@ include file="article.jsp" %>
	 			
	 		<!-- aside영역 -->
	 			<%@ include file="aside.jsp" %>	
	 	</div>
	 		<!-- footer -->
	 		<%@ include file="footer.jsp' %>
	</div>
</body>
</html>