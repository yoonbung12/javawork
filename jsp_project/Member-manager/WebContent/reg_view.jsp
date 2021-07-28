<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test = "${result==1 }">
<script>
	alert('등록되었습니다.');
	location.href = '<c:url value="/index.jsp"/>';
</script>
</c:if>
<c:if test="${result==0 }">
<script>
	alert('오류 발생으로 등록되지 않았습니다.\n입력페이지로 다시 이동합니다.');
	//location.href = 'dept_regForm.jsp';
	window.history.go(-1);
</script>
</c:if>