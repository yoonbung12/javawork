<%@page import="guest.exception.InvalidPasswordException"%>
<%@page import="guest.exception.MessageNotFoundException"%>
<%@page import="java.sql.SQLException"%>
<%@page import="guest.service.DeleteMessageService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	// 데이터 받고 -> Service에 요청 -> Dao 
	String mid = request.getParameter("mid");
	String pw = request.getParameter("password");
	
	String msg = "정상적으로 삭제되었습니다.";
	
	try {
		DeleteMessageService.getInstance().deleteMessage(Integer.parseInt(mid), pw);
	} catch(SQLException e){
		msg = "삭제하는 도중 문제가 발생했습니다. 다시 시도해주세요.";
	} catch(MessageNotFoundException e){
		msg = e.getMessage();
	} catch(InvalidPasswordException e){
		msg = e.getMessage();
	}
	
	request.setAttribute("msg", msg);
%>
<jsp:forward page="delete_view.jsp" />