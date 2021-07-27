<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<h1>
		<c:if test="${result == 0 }">
			메시지가 입력이 되지 않았습니다. 다시 시도해주세요.
		</c:if>
		<c:if test="${result == 1 }"> <!-- == 대신 eq 써도됨 -->
			메시지가 입력되었습니다.
		</c:if>
	</h1>
	<a href="list.jsp">리스트 보기</a>

</body>
</html>