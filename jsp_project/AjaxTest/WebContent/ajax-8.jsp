<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" Content="text/html; charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<style>
</style>
<script>

	$(document).ready(function(){
		//alert("ready");
		$('#myform').submit(function(){
			
			console.log($(this).serialize());
			console.log($(this).serializeArray());
			
			$.ajax({
				url : 'parameter.jsp',
				//data : $(this).serizlize(),
				data : $(this).serializeArray(),
				success : function(data){
					$('#menu').append(data);
				}
			});
			
			
			return false; 
		});
	
		
		
	});
</script>
</head>
<body>

	<h1>제품 등록</h1>
	<hr>
	<form id="myform">
	제품 이름 : <input type="text" id="pname" name="pname"> <br>
	제품 가격 : <input type="number" id="price" name="price"><br>
	<input id="btn" type="submit">
	</form>
	<hr>
	<div id="menu">
		<h3>메뉴표</h3>
		<hr>
		
		
	</div>


</body>
</html>