<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core %>
<!DOCTYPE html>
<html>
<head>
<meta  http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	div.message_box {
		padding : 5px;
		border : 1px solid #AAA;
		margin : 5px;
	}
	
	div.paging {
		padding : 10px;
		margin-left : 10px;
		margin-bottom : 100px;
	}
</style>
<script>
</script>
</head>
<body>
		<form action="writemessage.jsp"method="post">
			
			<table>
				<tr>
					<td>이름</td>
					<td><input type="text" name="guestname" required></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="password" required></td>
				</tr>
				<tr>
					<td>메시지</td>
					<td>
					<textarea name="message" rows="10" cols="10"></textarea>
					</td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="메시지남기기"></td>
				</tr>
			</table>
		</form>
		<hr>
		
		<c:if test="${listView.messageList ne null and not empty listView.messageList}">
		<c:forEach items="${listView.messageList}" var="message">
			<div class="message_box">
				<table>
					<tr>
						<td>메시지 아이디</td>
						<td>${message.messageid}</td>
					</tr>
					<tr>
						<td>메시지</td>
						<td>${message.message}</td>
					</tr>
					<tr>
						<td>작성일</td>
						<td>${message.regdate}</td>
					</tr>
					<tr>
						<td></td>
						<td><a href="confirmDelet.jsp?mid=${message.messageid}">삭제</a></td>
					</tr>
				</table>
			</div>
		</c:forEach>
		
		<!-- 페이징 -->
		<c:if test="${listView.pageTotalCount>0}">
			<div class="paging">
			<c:forEach begin="1" end="${listView.pageTotalCount}" var="num">
			<span>[<a href="list.jsp?page=${num }"> ${num} </a>]</span>
			</c:forEach>
			</div>
		</c:if>
		
		</c:if>
		
		${listView}
</body>
</html>