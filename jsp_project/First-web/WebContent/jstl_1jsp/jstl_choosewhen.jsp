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

	<select>
		<option ${param.sel eq 'a'?'selected':'' }>a-----</option>
		<option ${param.sel eq 'b'?'selected':'' }>b-----</option>
		<option ${param.sel eq 'c'?'selected':'' }>c-----</option>
	</select>

	<c: choose>
		<c:when test ="${param.sel == 'a' }">
		<h3>a 를 선택했습니다</h3>
		</c:when>
		
		<c:when test ="${param.sel == 'b' }">
		<h3>b 를 선택했습니다</h3>
		</c:when>	
		
		<c:when test ="${param.sel == 'c' }">
		<h3>c 를 선택했습니다</h3>
		</c:when>
		
		<c:otherwise>
		a, b, c 가 아닌데이터를 선택했습니다.
		</c:otherwise>
	</c: choose>
	
	<c:set var="num" value="10"/>
	
	<br>
	
	${num }
	<c:choose>
		<c:when test="${num > 0 }">양수</c:when>
		<c:when test="${num < 0 }">음</c:when>
		<c:otherwise>0</c:otherwise>
	</c:choose>
	
	${num}	
	
	
	
</body>
</html>