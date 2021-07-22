<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.domain.Member" %>
<%@ page import="jdbc.util.ConnectionProvider" %>
<%@ page import="member.dao.MemberDao" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.Connection" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>    
 <%
 		request.setCharacterEncoding("utf-8");
 
 		String id = request.getParameter("id");
 		String pw = request.getParameter("pw");
 		String name = request.getParameter("name");
 		
 		int result = 0;
 		
 		Connection conn = null;
 		MemberDao dao = MemberDao.getInstance();
 		try{
 		conn = ConnectionProvider.getConnection();	
 		
 		result = dao.joinMember(conn, new Member(id,pw,name));
 		
 		} catch(Exception e) {
 			e.printStackTrace();
 		}
 		
 		if(result> 0){
 			
 		 
 		%>
 		
 			<script>
 				alert('들록되었습니다.');
 				location.href='member_list.jsp';
 			</script>
		<%
		} else {
		%>
		
			<script>
				alert('오류 발생으로 등록 x\n');
				Window.history.go(-1);
			</script>
			<% 
		}
			%>