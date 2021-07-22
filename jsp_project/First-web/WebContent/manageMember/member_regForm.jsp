<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta  http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 정보 등록</title>
<style>
	table {
		width: 500px;
		height: 200px;
		text-align: center;
	}
	table>tr{
		margin: 5px 0;
	}
	table input[type=text], table input[type=password]{
		width: 200px;
		height: 30px;
		padding-left: 5px;
		float: left;
	}
	table input[type=submit]{
		width: 100px;
	}
	#msg {
		color: red;
		font-size: 10px;
	}

</style>
<script type="text/javascript">
window.onload = function(){
				//비밀번호, 비밀번호 재확인, form 캐스팅
		var pw = document.getElementById("pw")
		var repw = document.getElementById("repw");
		var regForm = document.getElementById('regForm');
		
	regForm.onsubmit = function(){
		//비밀번호 비밀번호 확인 일치 확인
		if(pw.value != repw.value) {
			document.querySelector('#msg').innerHTML = '비밀번호가 일치하지 않습니다.';
			document.querySelector('#msg').style.display = 'block';
			return false;
		}
	}
	repw.addEventListener('focus', function(){
		document.querySelector('#msg').style.display = 'none';
		document.querySelector('#msg').innerHTML = '';
	});
	
}
</script>
</head>
<body>

	<h1>회원 등록</h1>
	<hr>
	<a href="member_list.jsp"><button>홈으로 돌아가기</button></a>
	<hr>
	<form id="regForm" action="regMember.jsp" method="post">
		<table border="1">
			<tr>
				<td>아이디</td>
				<td>
					<input type="text" name="id" placeholder="아이디를 입력하세요." required>
					
			
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>
					<input type="password" id="repw" name="repw" placeholder="비밀번호를 한번더 입력하세요." required>
					<div id="msg"></div>
				
				</td>
			
			
			</tr>
			<tr>
				<td>이름</td>
				<td>
					<input type="text" name="name" placeholder="이름을 입력하세요." required>
				</td>
			
			
			
			</tr>
			<tr>
				<td>
					<input type="reset" value="초기화">
					
				
				
				</td>
				<td>
					<input type="submit" value="등록">
				</td>
				
			
			</tr>
		</table>
	
	
	
	</form>
	
</body>
</html>