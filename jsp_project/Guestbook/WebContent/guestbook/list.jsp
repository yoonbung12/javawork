<%@page import="guest.service.MessageListService"%>
<%@page import="guest.domain.MessageListView"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	// 사용자로부터 페이지 번호를 받는다.  list.jsp?page=3,   list.jsp
	String pageNumStr = request.getParameter("page");
	int pageNum = 1;
	if(pageNumStr != null){
		pageNum = Integer.parseInt(pageNumStr);
	}
	
	MessageListView listView = MessageListService.getInstance().getMessageList(pageNum);
	
	request.setAttribute("listView", listView);
	
%>
<jsp:forward page="list_view.jsp"/>