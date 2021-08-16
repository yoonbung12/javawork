<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
<title>Member Manager - List</title>
<%@ include file="/WEB-INF/views/frame/metaheader.jsp" %>
</head>
<body>

			<%@ include file="/WEB-INF/views/frame/header.jsp" %>
			<%@ include file="/WEB-INF/views/frame/nav.jsp" %>
			
			<h3>회원 리스트</h3>
			
			<table border=1>
							<tr>
												<th>index</th>
												<th>아이디</th>
												<th>비밀번호</th>
												<th>이름</th>
												<th>가입일자</th>
												<th>관리</th>
							</tr>
							
							<c:if test="${members != null }" >
										<c:forEach items= "${members }" var="member">
													<tr>
																	<td>${member.idx }</td>
																	<td>
																				<img src='<c:url value="/uploadfile/${member.memberphoto }"/>' height="30">
																				${member.memberid}
																	</td>
																	<td>${member.password }</td>
																	<td>${member.membername }</td>
																	<td>${member.regdate }</td>
																	<td>
																				<a href="<c:url value="/member/edit/${member.memberid }"/>">수정</a>
																				<a href="javascript:delMember('${member.memberid }')">삭제</a>
																	</td>
													</tr>
										</c:forEach>
							</c:if>
		
			</table>
			
			<script>
								function delMember(userId){
									if(confirm('정말로 삭제하시겠습니까?')){
														location.href = '<c:url value="/member/del/"/>'+userId;
									}
								}
			</script>

</body>
</html>