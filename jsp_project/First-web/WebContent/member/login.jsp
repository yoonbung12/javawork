<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	session.setAttribute("userName", request.getParameter("id"));


	//						/web/member/ mypage/mypage.jsp
	response.sendRedirect(request.getContextPath()+ "/member/mypage/mypage.jsp");
%>
