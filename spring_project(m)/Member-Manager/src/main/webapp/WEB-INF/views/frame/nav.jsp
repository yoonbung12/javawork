<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com.jsp/jstl/core" %>

<nav>
		<ul>
					<li><a href="<c:url value='/member/reg'/>"> 회원가입 </a></li>
					<c:if test="${isLogin == null || isLogin == false }">
								<li><a href="<c:url value='/member/login'/>">로그인</a></li>
					</c:if>
					<c:if test="${isLogin == true }">
								<li><a href="<c:url value='/member/logout'/>"> 로그아웃</a></li>
					</c:if>
					<li><a href="<c:url value='/member/list'/>">회원리스트</a></li>
					<li><a href="<c:url value='/member/mypage'/>">MyPage</a></li>							
		
		
		</ul>

</nav>