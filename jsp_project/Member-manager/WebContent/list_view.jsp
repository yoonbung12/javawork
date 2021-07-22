<%@page import="domain.Member"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<Member> list = (List<Member>) request.getAttribute("result");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	td {
		text-align: center;
		padding: 5px 10px;
	}
	
	button {
		margin: 5px 0;
	}
</style>
<script>
</script>
</head>
<body>
	
	<h1>부서 리스트</h1>
	<hr>
	
	<!-- <button onclick="location.href='dept_regForm.jsp';">부서정보 등록</button> -->
	
	<table border=1>
		<tr>
			<th>idx</th>			
			<th>아이디</th>
			<th>비밀번호</th>
			<th>이름</th>
			<th>가입일</th>
			<th>관리</th>
		</tr>
		
	<%
		if(list!=null && !list.isEmpty()){
			for(int i=0; i<list.size(); i++){
				%>
		<tr>
			<td><%= list.get(i).getIdx() %></td>			
			<td><%= list.get(i).getMemberid() %></td>
			<td><%= list.get(i).getPassword() %></td>
			<td><%= list.get(i).getMembername() %></td>
			<td><%= list.get(i).getRegdate() %></td>
			<td>
				<a href="#">수정</a> 
				<a href="#">삭제</a>
			</td>
		</tr>
				<%
			}
		}
	%>
		
		
	</table>
	<script>
		
		function delDept(idx){
			
			if(confirm('정말 삭제하시겠습니까?')){
				//location.href = ''+idx;
			}
			
		}
	
	</script>

	<%--
		out.println(list);
	--%>










</body>
</html>