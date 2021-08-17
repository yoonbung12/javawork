<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 리스트</title>
<style>

	#memberlist	{
		overflow : hidden;
	}

	div.card{
		float: left;
		
		width: 28%;
		padding: 10px;
		border : 1px solid #AAA;
		border-radius: 5px;
	}
	

</style>

<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>

	$(document).ready(function(){
		
		$.ajax({
			url : 'http://localhost:8080/op/members'
			type : 'GET', 
			success : function(data){
				console.log(data);
				$.each(data, function(index, item){
					console.log(index, item);
				
					var html = '<div class="card">';
					html += "idx : " + item.idx + "<br>";
					html += "아이디 : " + item.memberid + "<br>";
					html += "이름 : " + item.membername + "<br>";
					html += "사진 : " + item.memberphoto + "<br>";
					html += "등록일 : " + item.regdate + "<br>";
					html += "</div>"
					
					$('#memberlist').append(html);
					
				});
			}
		
		});
	
	});
	
	

</script>
</head>
<body>

	<h1>회원리스트</h1>
	<hr>
	<div id="memberlist">
	
	</div>

</body>
</html>