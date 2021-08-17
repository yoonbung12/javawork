<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원리스트</title>
<style>
	.display_none {
		display: none;
	}
	
	.color_blue {
		color: blue;
	}
	
	.color_red {
		color: red;
	}
	
	#loadingimg {
		height: 20px;
	}
	
	#memberlist {
		overflow: hidden;
	}
	div.card {
		float: left;
		
		width : 300px;
		height : 150px;
		padding : 10px;
		border : 1px solid #AAA;
		border-radius: 5px;
		margin : 5px;
	}
	
	#regBtn {
		cursor: pointer;
	}
	
	img {
		width : 30px;
	}
	
	
	
</style>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
	$(document).ready(function(){
		
		$('#memberid').focusin(function() {
			$('#msg').addClass('display_none');
			$('#msg').removeClass('color_blue');
			$('#msg').removeClass('color_red');
			$(this).val('');
		});
		$('#memberid').focusout(function() {
			// ajax 비동기 통신 > id를 서버로 보내고 사용 가능 유무의 응답 코드를 받는다 -> 화면에 메시지 출력
			$.ajax({
				url : 'http://localhost:8080/op/member/idCheck',
				type : 'post',
				data : {
					mid : $(this).val()
				},
				beforeSend : function() {
					$('#loadingimg').removeClass('display_none');
				},
				success : function(data) {
					// data : Y / N
					if (data == 'Y') {
						$('#msg').html('사용가능');
						$('#msg').addClass('color_blue');
						$('#msg').removeClass('display_none');
					} else {
						$('#msg').html('사용 불가능');
						$('#msg').addClass('color_red');
						$('#msg').removeClass('display_none');
					}
				},
				error : function(request, status, error) {
					alert('서버 통신에 문제가 발생했습니다. 다시 실행해주세요.');
					console.log(request);
					console.log(status);
					console.log(error);
				},
				complete : function() {
					$('#loadingimg').addClass('display_none');
				}
			});
		});
		
		$('#submit').click(function(){
			
			var photoFile = $('#photo');
			var file1 = photoFile[0].files[0];
			//console.log(file1);
			var formData = new FormData();
			formData.append("memberid", $('#memberid').val());
			formData.append("password", $('#password').val());
			formData.append("membername", $('#membername').val());
			formData.append("photo", file1);
			console.log(formData);
			$.ajax({
				url : '/op/members/reg1',
				type : 'post',
				data : formData,
				enctype : 'multipart/form-data',
				processData : false,
				contentType : false,
				cache : false,
				success : function(data) {
					console.log(data);
					if(data==1){
						alert('회원가입이 되었습니다.');
						$('#regform').addClass('display_none');
						memberList();
					}
				}
			});
			
			
			
		});
		
		
		$('#regBtn').click(function(){
			
			$('#regform').removeClass('display_none');
			$('#msg').addClass('display_none');
			$('#memberid').val('');
			$('#password').val('');
			$('#membername').val('');
			$('#photo').val('');
			
		});
		
		$('#formclose').click(function(){
			$('#regform').addClass('display_none');
		});
		
		
		
		
		
		memberList();
		
		
		
	});
	
	function memberList(){
		$.ajax({
			url : 'http://localhost:8080/op/members',
			type : 'GET',
			success : function(data){
				console.log(data);
				$.each(data, function(index, item){
					console.log(index,item);
					
					var html = '<div class="card">';
					html += 'idx : ' + item.idx + '<br>';
					html += '아이디 : ' + item.memberid + '<br>';
					html += '이름 : ' + item.membername + '<br>';
					html += '사진 : <img src="http://localhost:8080/op/uploadfile/' + item.memberphoto + '"><br>';
					html += '등록일 : ' + item.regdate + '<br>';
					html += '</div>';
					
					$('#memberlist').append(html);
					
					
				});
			}
			
		});
	}
</script>
</head>
<body>

	<div id="memu">
		<span id="regBtn">회원가입</span>
	</div>
	
	<div id="regform" class="display_none">
		<h1>회원가입</h1>
		<hr>
		
			<table>
				<tr>
					<td>아이디</td>
					<td><input type="text" name="memberid" id="memberid">
						<span id="msg" class="display_none"></span> 
						<img id="loadingimg" class="display_none" alt="loading"
						src="http://locahost:8080/op/images/loading.gif"></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="password" id="password"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="membername" id="membername"></td>
				</tr>
				<tr>
					<td>사진</td>
					<td><input type="file" name="photo" id="photo"></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<input type="submit" id="submit"> 
						<input type="reset"> 
						<input type="button" value="입력 폼 닫기" id="formclose">
					</td>
				</tr>
			</table>
		
	</div>
	

	<h1>회원 리스트</h1>
	<hr>
	<div id="memberlist">
	
		

	
	</div>


</body>
</html>