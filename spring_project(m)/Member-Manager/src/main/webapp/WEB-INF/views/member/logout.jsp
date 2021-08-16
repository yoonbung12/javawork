<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% 
		session.invalidate();
%>

<script>
				alert('로그아웃 됐습니다.');
				location.href = '<c:url value="/"/> ';
</script>