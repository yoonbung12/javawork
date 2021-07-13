// 회원 정보(아이디,비밀번호, 이름)
// Member -> 생성자 함수 정의
function Member(id, pw, name) {
    this.userid = id;
    this.pw = pw;
    this.username = name;   
}
// 메소드 공통으로 사용-prototype
Member.prototype.makeHtml = function() {
    return '[id:' + this.userId + ' , pw' + this.pw+ ' , name: ' + this.userName + ']'
}

// 회원 정보 저장하는 배열
var members = [];


// 이벤트 연결
$(document).ready(function(){

    //localStorage.getItem('members') 업으면 null반환
    if(localStorage.getItem('members') == null) {
        
        //배열 members 를 저장
        localStorage.setItem('members', JSON.stringify(members));
    } else {
        // JSON 문자열 -> 객체로 변환
        members = JSON.parse(localStorage.getItem('members'));
        console.log(members);
         // 테이블 세팅?
        setList();
    }
   
    
    // document 객체를 -> jquey 객체로 변환
    // var userid = document.querySelector('#userID');
    // var pw = document.querySelector('#pw');
    // var repw = document.querySelector('#repw');
    // var userName = document.querySelector('#userName');
    // 아이디
    
    // var:전역변수,함수
    var id = $('#userID');
    var pw = $('#pw');
    var repw = $('#repw');
    var name = $('#userName');
    
    // var id = $('input[type=text]').val();
    // // console.log(userid);

    // // 비밀번호
    // var pw = $('input[type=password]').val();
    // // console.log(pw);
    // // 비밀번호 확인
    // var repw = $('input[type=password]').val();
    // // console.log(repw);
    // // 이름
    // var name = $('input[type=text]').val();
    // // console.log(userName);
    
    

    // regForm캐스팅
    $('#regForm').submit(function() {
        
        var id_reg = /\w+@\w+\.\w+/;

        // 아이디 검사
        if(id.val().trim().length < 1) { // 동적 html요소
            $('#userID + .msg').html('필수항목입니다.');
            $('#userID + .msg').css({display: 'block'});
            return false;
        }
        


        //비밀번호 
        if(pw.val().trim().length < 1) {
            $('#pw + .msg').html('필수항목입니다.');
            $('#pw + .msg').css({display: 'block'});
            return false;
        }
        // 비밀번호 확인 일치 여부 확인
        if(pw.val().trim() != repw.val().trim()){
            $('#repw + .msg').html('비밀번호가 일치하지 않습니다');
            $('#repw + .msg').css({display: 'block'});
            return false;
        }

        //이름 입력
        if(name.val().trim().length < 1){
            $('#userName + .msg').html('필수항목입니다.');
            $('#userName + .msg').css({display: 'block'});
        }
        

        // 배열에 사용자 정보를 추가
        members.push(new Member(id.val(), pw.val(),name.val()));

        // 저장
        localStorage.setItem('members',JSON.stringify(members));

        alert('등록되었습니다.');
        console.log('회원리스트', members);

        // form초기화
        this.reset();

        // 테이블 세팅
        setList();
        return false;

    });

    $(id).focus(function() {
        $('#userId+div.msg').css({display: 'none'});
        $('#userId+div.msg').html('');
    });
    $(pw).focus(function () {
        $('#pw+div.msg').css({display: 'none'});
        $('#pw+div.msg').html('');
    });

    $(repw).focus(function () {
        $('#repw+div.msg').css({display : 'none'});
        $('#repw+div.msg').html('');
        repw.value = '';
    });

    $(name).focus(function () {
        $('#userName+div.msg').css({display : 'none'});
        $('#userName+div.msg').html('');
    });
    
}); 

function setList(){

    var list=$('#list');

    var tbody ='<tr>';
    tbody += '  <th>순번(index)</th>';
    tbody += '  <th>아이디</th>';
    tbody += '  <th>비밀번호</th>';
    tbody += '  <th>이름</th>';
    tbody += '  <th>관리</th>';
    tbody += '</tr>';

    if(members.length< 1) {
        tbody += '<tr>';
        tbody += '<td colspan="5">데이터가 존재하지 않습니다.</td>';
        tbody += '</tr>';
    } else {

        for(var i=0; i< members.length; i++){
            tbody += '<tr>';
            tbody += '  <td>' + i + '</td>';
            tbody += '  <td>' + members[i].userid + '</td>';
            tbody += '  <td>' + members[i].pw + '</td>';
            tbody += '  <td>' + members[i].username + '</td>';
            tbody += '  <td><a href="javascript.editMember('+i+')">수정</a> |' +
                '<a href="javascript:deleteMember('+i+')">삭제</a></td>';
            tbody += '</tr>';
        }
    }
    $(list).html(tbody);
}; // setList

// 배열의 요소 삭제 함수
function deleteMember(index) {
    // 배열의 index요소를 삭제
    if(confirm('삭제하시겠습니까?')){
        members.splice(index, 1);
        alert('삭제되었습니다,');

        // 저장
        localStorage.setItem('members', JSON.stringify(members));

        // 테이블 리스트 갱싱
        setList();
    }
}

// 배열의 요소 수정함수
function editMember(index) {

    // 수정 폼 영역이 노출되어야함.
    $('#editFormArea').css({display: 'block'});

    // 이전 데이터를 폼에 세팅
    $('#editId').val(members[index].userid);
    $('#editPw').val(members[index].pw);
    $('#editRePw').val(members[index].pw);
    $('#editName').val(members[index].username);
    $('#index').val(index);

    $('#editForm').submit(function(){
        if(editPw.val() != editRePw.val()){
            alert('비밀번호와 비밀번호 확인이 일치하지 않습니다.');
            return false;
        }
        if(!confirm('수정하시겠습니까?')){
            return false;
        }

        members[editIndex.val()].pw = editPw.val();
        members[editIndex.val()].username = editName.val();

        // 저장
        localStorage.setItem('members',JSON.stringify(members));

        alert('수정되었습니다.');

        // 테이블세팅
        setList();

        editMemberClose();

        return false;
    });
}
function editMemberClose(){
    $('#editFormArea').css({display: 'none'});
}

