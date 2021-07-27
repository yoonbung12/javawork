<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta  http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
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
</body>
</html>