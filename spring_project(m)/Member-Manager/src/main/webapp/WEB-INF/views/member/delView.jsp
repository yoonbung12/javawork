<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${result == 1 }">
				<script>
									alert('삭제되었습니다.');
									location.href = '<c:url value="/"/>';
				</script>
</c:if>

<c:if test="${result == 0 }">
				<script>
									alert('오류로 인해 삭제에 실패했습니다.\n 다시 삭제해주세요.');
									location.href = '<c:url value="/member/list"/'>;
				</script>

</c:if>