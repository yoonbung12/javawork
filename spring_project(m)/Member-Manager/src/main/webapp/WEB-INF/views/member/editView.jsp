<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${result == 1 }"	>
				<script>
									alert('수정되었습니다.');
									location.href= '<c:url value="/"/>';
				</script>
</c:if>

<c:if test="${result == 0 }">
						<script>
											alert('오류로 인해 수정에 실패했습니다.\n다시 수정해주세요.');
											window.history.go(-1);
						</script>


</c:if>