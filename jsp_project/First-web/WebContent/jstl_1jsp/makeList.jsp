<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="member.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	List<Member> members = new ArrayList<Member>();
	members.add(new Member("cool00", "111", "COOL"));
	members.add(new Member("cool01", "112", null));
	members.add(new Member("cool02", "113", "COOL"));
	members.add(new Member("cool03", "114", "COOL"));
	members.add(new Member("cool04", "115", null));
	members.add(new Member("cool05", "116", null));
	members.add(new Member("cool06", "117", "COOL"));
	members.add(new Member("cool07", "118", "COOL"));
	members.add(new Member("cool08", "119", "COOL"));
	
	session.setAttribute("members", members);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>