<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="member.domain.Member" %>
	<!-- member_list.jsp에서 request에 저장한 데이터 블러오기 -->
<%@ List<Member> memberList=(List<Member>)request.getAttribute  %> 
<!DOCTYPE html>
<html>
<head>
<meta  http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 정보 보기</title>
<style>
	button{
		margin: 5px;
	}
</style>
</head>
<body>
	<h1>회원 리스트	</h1>
	<hr>
	
	<button onclick="location.href='member_regForm.jsp'">회원등록하기</button>
	<table border="1">
	
		<tr>
			
			<th>회원코드</th>
			<th>회원 아이디</th>
			<th>회원 비밀번호</th>
			<th>회원 이름</th>
			<th>가입일 </th>
			<th>관리</th>
		
		</tr>
	
		<%
			if(memberList != null) {
				for(int i=0; i<memberList.size(); i++){
					%>
					
		<tr>
		
			<td><%= memberList.get(i).getIdx() %></td>
			<td><%= memberList.get(i).getId() %></td>
			<td><%= memberList.get(i).getPw() %></td>
			<td><%= memberList.get(i).getName() %></td>
			<td><%= memberList.get(i).getRegDate() %></td>
			<td>
				<a href="member_editForm.jsp?idx=<%= memberList.get(i).getIdx() %>">수정</a>
				<a href="javascript:delMember(<%= memberList.get(i).getIdx() %>)">삭제</a>
			
			</td>
			
		</tr>
		
					<% 					
				}
			}
		
		%>
	</table>
	<script>
			function delMember(idx){
				if(confirm('정말 삭제하시겠습니까?')){}
			}
	</script>
</body>
</html>