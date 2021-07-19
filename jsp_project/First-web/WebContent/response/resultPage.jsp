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

	<%
		String select = request.getParameter("select");
	
		int selNum = Integer.parseInt(select);
		
		if(selNum >0) {
			
			out.println(selNum);
			
			// sendRedirect(경로) -> 현재 페이지가 응답 처리되고 난 후 경로의 페이지를 요청
			response.sendRedirect("result.jsp");
		} else {
			out.println(selNum);
			%>
			
			<!-- 현재 페이지 resultPage.jsp가 응답이 되는것이 아니라 result.jsp 파일의 결과를 응답처리-->
			<jsp:forward page="result.jsp"/>
			
			<% 
			
		}
	%>

</body>
</html>