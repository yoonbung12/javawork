<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Open Project : 회원가입</title>

<%@ include file="/WEB-INF/views/frame/metaheader.jsp"%>

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
</style>
<script>
	$(document).ready(function() {
		
		$('#memberid').focusin(function() {
			$('#msg').addClass('display_none');
			$('#msg').removeClass('color_blue');
			$('#msg').removeClass('color_red');
			
			$(this).val('');
		});
		$('#memberid').focusout(function() {
			// ajax 비동기 통신 > id를 서버로 보내고 사용 가능 유무의 응답 코드를 받는다 -> 화면에 메시지 출력
			$.ajax({
				url : '<c:url value="/member/idCheck"/>',
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
	});
</script>
</head>
<body>

	<%@ include file="/WEB-INF/views/frame/header.jsp"%>

	<%@ include file="/WEB-INF/views/frame/nav.jsp"%>



	<div id="content">
		<h2>회원가입</h2>
		<hr>

		<form method="post" enctype="multipart/form-data">

			<table>
				<tr>
					<td>아이디</td>
					<td><input type="text" name="memberid" id="memberid">
						<span id="msg" class="display_none"></span> <img id="loadingimg"
						class="display_none" alt="loading"
						src="<c:url value="/images/loading.gif"/>"></td>
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
					<td><input type="submit"> <input type="reset">
					</td>
				</tr>
			</table>

		</form>

			<div>
			ajax로 회원 가입 <input type="button" value="회원가입" id="btnReg1">
			</div>
			<div>
			ajax로 Json 전송 회원 가입 <input type="button" value="회원가입" id="btnReg2">
			</div>



	</div>


	<script>
		$(document).ready(function() {
			$('#btnReg1').click(function() {
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
					}
				});
			});
			
			
			$('#btnReg2').click(function() {
				var userid = $('#memberid').val();
				var pw = $('#password').val();
				var username = $('#membername').val();
				var member = {
						memberid: userid,
						password: pw,
						membername: username
				}
				
				console.log(JSON.stringify(member));
				
				$.ajax({
					url : '/op/members/reg2',
					type : 'post',
					data : JSON.stringify(member),
					dataType : 'json',
					contentType : 'application/json',
					success : function(data) {
						console.log(data);
					}
				});
			});
		});
	</script>










</body>
</html>