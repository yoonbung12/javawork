<%@page import="java.sql.SQLException"%>
<%@page import="util.ConnectionProvider"%>
<%@page import="dao.MemberDao"%>
<%@page import="java.sql.Connection"%>
<%@page import="service.MemberRegService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
<%
	int result = MemberRegService.getInstance().regMember(request)(request)(request);
	//request.setAttribute();

%>
<jsp:forward page="reg_view.jsp"></jsp:forward>