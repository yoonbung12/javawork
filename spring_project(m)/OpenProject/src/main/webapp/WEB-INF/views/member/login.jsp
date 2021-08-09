<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%-- <c:if test="${loginInfo eq null }"> --%>
<c:if test="${not loginChk }">
<script>
	alert("아이디 또는 비밀번호가 일치하지 않습니다.");
	history.go(-1);
</script>   

</c:if>

<c:if test="${not loginChk }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Open Project :로그인</title>
<%@ include file="/WEB-INF/" %>

</head>
<body>

</body>
</html>