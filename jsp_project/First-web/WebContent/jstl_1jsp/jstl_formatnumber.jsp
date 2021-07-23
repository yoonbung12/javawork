<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt " %>
<%@ taglib prefix="c" uri="http://java." %>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:set var="number" value="1000000"/>

	<fmt:formatNumber value="1000000"/> <br>
	<fmt:formatNumber value="1000000" groupingUsed="false"/> <br>
	
	<fmt:formatNumber value="${number} " /> <br>
	숫자 : <fmt:formatNumber value="${number} " type="number"/> <br>
	퍼센트 : <fmt:formatNumber value="${number/4000000000} " type="percent"/> <br>
	통화 : <fmt:formatNumber value="${number} " type="currency" currencySymbol="$"/> <br>

	패턴 : <fmt:formatNumber value="${number*10000} " pattern="000,000.00" /> <br> 

</body>
</html>