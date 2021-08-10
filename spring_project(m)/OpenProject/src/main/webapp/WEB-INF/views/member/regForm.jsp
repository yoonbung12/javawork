<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib perfix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Open Project : 회원가입</title>

<%@ include file="/WEB-INF/views/frame/metaheader.jsp" %>


</head>
<body>

	<%@ include file="/WEB-INF/views/frame/header.jsp" %>
	
	<%@ include file="/WEB-INF/views/frame/nav.jsp" %>
	
	<div id="content">
			<h2>회원가입</h2>
			<hr>
		
			<form method="post" enctype="multipart/form-data">
			
					<table>
						<tr>
							<td>아이디</td>
							<td>
								<input type="text" name="memberid">
								<span id="msg" class="display_none"></span>
								<img id="loading" class="display_none" alt="loading" src="<c:url value="/image/loading.gif"/>">					
							</td>
						</tr>
						<tr>
							<td>비밀번호</td>
							<td><input type="password" name="password"></td>
						</tr>
						<tr>
							<td>이름</td>
							<td><input type="file" name="membername"></td>
						</tr>
						<tr>
							<td></td>
							<td>
								<input type="submit">
								<input type="reset" >
							</td>
						</tr>
					</table>				
			</form>	
	
	</div>

</body>
</html>