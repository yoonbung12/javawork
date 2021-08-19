<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/sql" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Open Project</title>

<%@ include file="/WEB-INF/views/frame/metaheader.jsp" %>


<c:if test="${param.result == 1 }">
	<script>
			alert('회원가입을 축하합니다.');
	</script>
</c:if>

</head>
<body>
	
	<%@ include file="/WEB-INF/views/frame/nav.jsp" %>

	<%@ include file="/WEB-INF/views/frame/header.jsp" %>
	
	
	<div id="content">
			<h1>Content : Index</h1>
		
	</div>
	

</body>
</html>