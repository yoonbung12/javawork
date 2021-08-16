<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Manager - Login</title>
<%@ include file="/WEB-INF/views/frame/metaheader.jsp" %>
</head>
<body>

				<%@ include file="/WEB-INF/views/frame/header.jsp" %>
				<%@ include file="/WEB-INF/views/frame/nav.jsp" %>
				
				<h3>멤버 로그인</h3>
				
				<form action="<c:url value='/member/login'/>" method="post">
							<table>
											<tr>
															<td>
																		<label for="userId"><b>ID</b></label>
															</td>
															<td>
																		<input type="text" name="userId" id="userId" value="${rememId }">
															</td>
											</tr>
											<tr>
															<td>
																		<label for="userPw"><b>PW</b></label>
															</td>
															<td>
																		<input type="password	" name="userPw" id="userPw">
																		
															</td>
											</tr>
											<tr>
															<td>
															</td>
															<td class="td_left">
																			<input type="checkbox" name="rememId" id="rememId" value="on" ${rememId eq null ? '' : 'checked' }>
																			<label for="rememId">아이디 기억하기</label>
															</td>
											</tr>
											<tr>
															<td>
															</td>
															<td class="td-left">
																			<input type="submit" value="로그인">
															</td>
											</tr>
							</table>
				
				
				
				</form>

</body>
</html>