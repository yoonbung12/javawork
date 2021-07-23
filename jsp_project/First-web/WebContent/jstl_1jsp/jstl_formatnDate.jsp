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

<%
	request.setAttribute("now", new java.util.Date());
%>

	${now} <br>
	
<%-- 	<fmtm:timeZone value="HongKong"> --%>
	<fmt:formatDate value="${now}" /> <br>
	<fmt:formatDate value="${now}" type="date" /> <br>
	<fmt:formatDate value="${now}" type="date" dateStyle="full"/> <br>
	<fmt:formatDate value="${now}" type="date" dateStyle="short"/> <br>
	
	<h3>시간 </h3>
	<fmt:formatDate value="${now}" type="time" /> <br>
	<fmt:formatDate value="${now}" type="time" timeStyle="full"/> <br>
	<fmt:formatDate value="${now}" type="time" timeStyle="short"/> <br>
	
	<h3>both</h3>
	<fmt:formatDate value="${now}" type="both" /> <br>
	<fmt:formatDate value="${now}" type="both" /> <br>
	<fmt:formatDate value="${now}" type="both" dateStyle="full" timeStyle="full"/> <br>
	
	<h3>패</h3>
	<fmt:formatDate value="${now}" pattern="z a h:mm" /> <br>
	<fmt:formatDate value="${now}" pattern="hh:mm:ss" /> <br>
	<fmt:formatDate value="${now}" pattern="YYYY-MM-dd h:mm" /> <br>
	<fmt:formatDate value="${now}" pattern="YYYY.MM.dd h:mm" /> <br>
	<fmt:formatDate value="${now}" pattern="YYYY.MM.dd h:mm" timeZone="HongKong"/> <br>
	
<%-- 	</fmtm:timeZone> --%>	
	



</body>
</html>