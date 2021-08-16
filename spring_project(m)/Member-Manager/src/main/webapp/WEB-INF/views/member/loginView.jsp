<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<c:if test="${isLogin == true }">
				<script>
									location.href = '<c:url value="/"/>';
									
				</script>
				
</c:if>

<c:if test="${isLogin == false }"	>
				<script>
									alert('아이디 혹은 비밀번호가 일치하지 않습니다.');
									history.go(-1);
				</script>

</c:if>