<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Manager - Register</title>
<%@ include file="/WEB-INF/view/frame/metaheader.jsp" %>
<script src="/mm/js/reg.js"></script>
</head>
<body>


				<%@ include file="/WEB-INF/views/frame/header.jsp" %>
				<%@ include file="/WEB-INF/views/frame/nav.jsp" %>
				
				<h3>회원 가입</h3>
				
				<form method="post" enctype="multipart/form-data">
								<table>
													<tr>
																	<td>
																					<label for="memberid"><b>ID</b></label>
																	</td>
																	<td>
																					<input type="text" name="memberid" id="memberid">
																					<span id="msg" class="display_none"></span>
																					<img id="loadingimg"	alt="loading" src="<c:url value="/images/loading.gif"/>" class= "display_none">
																					
																	</td>
													</tr>
													<tr>
																	<td>
																				<label for="password"><b>PW</b></label>
																	</td>
																	<td>
																				<input type="password" name="password" id="password">
																	</td>
													</tr>
													<tr>
																	<td>
																				<label for="membername"><b>이름</b></label>
																	</td>
																	<td>
																				<input type="text" name="membername" id="membername">
																	</td>
													</tr>
													<tr>
																	<td>
																				<label for="photo"><b>사진</b></label>
																	</td>
																	<td>
																				<input type="file" name="photo" id="photo">
																	</td>
													</tr>
													<tr>
																	<td>
																				<input type="submit" value="등록">
																	</td>
																	<td id="bnt_reset">
																				<input type="reset" value="초기화">
																	</td>
													</tr>
								</table>
				</form>

</body>
</html>