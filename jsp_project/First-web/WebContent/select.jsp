<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	//사용자로부터 파리미터를 받아 응답을 분기해주는 처리
	//select.jsp?sel=aa
	String sel = request.getParameter("sel");

	if(sel != null){
		
		if(sel.equals("aa")){
			%>
			<jsp:forward page="aa.jsp"./>
			<% 
		} else if(sel.equals("bb")){
			%>
			<jsp:forward page="bb.jsp"./>
			<%
		} else if(sel.equals("blog")){
			%>
			<jsp:forward page="blog/blog.jsp"./>
			<%
		}
	} 
%>
잘못된 요청입니다.