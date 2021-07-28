<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" Content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<style>
</style>
<script>

	$(document).ready(function(){
		//alert("ready");
		
		$.ajax({
			url : 'data.html',
			success : function(data){
				$('body').html(data);
			}
		});
	});
</script>
</head>
<body>
ajax-1.jsp
</body>
</html>