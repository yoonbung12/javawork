// 회원의 정보 : 아이디, 비밀번호, 이름
// Member -> 생성자 함수를 정의
function Member(id, pw, name) {
    this.userid = id;
    this.pw = pw;
    this.username = name;
}

// 객체가 가지는 메소드는 공통으로 사용 -> prototype

Member.prototype.makeHtml = function () {
    return '[id:' + this.userid + ' , pw: ' + this.pw + ' , name: ' + this.username + ' ]'
};



var members = []; // new Array();

$(document).ready(function(){

    // localStorage 저장된 데이터가 있는지 확인
    // localStorage.getItem('members') 없으면 null 반환
    if(localStorage.getItem('members') == null) {
        // 배열 members 를 저장
        localStorage.setItem('members', JSON.stringify(members));
    } else {
        members = JSON.parse(localStorage.getItem('members')); // JSON 문자열 -> 객체로 변환
        console.log(members);
        // 테이블 세팅
        setList();
    }


    var id = $('#userID');
    var pw = $('#pw');
    var repw = $('#repw');
    var name = $('#userName');


    // regForm 캐스팅

    $('#regForm').submit(function(){
        
        var id_reg=/\w+@\w+\.\w+/;

        if(id.val().trim().length < 1){
            $('#userID + .msg').html('필수항목입니다.');
            $('#userID + .msg').css({display : 'block'});
            return false;
        }

        if(pw.val().trim().length < 1){
            $('#pw + .msg').html('필수항목입니다.');
            $('#pw + .msg').css({display : 'block'});
            return false;
        }

        // 비밀번호 비밀번호 확인 일치 여부 체크
        if(pw.val().trim() != repw.val().trim()){
            $('#repw + .msg').html('비밀번호가 일치하지 않습니다.');
            $('#repw + .msg').css({display : 'block'});
            return false;
        }

        // 사용자의 이름 정보
        if(name.val().trim().length < 1){
            $('#userName + .msg').html('필수항목입니다.');
            $('#userName + .msg').css({display : 'block'});
        }




        // 배열에 사용자 정보를 추가
        members.push(new Member(id.val(), pw.val(), name.val()));

        // 저장
        localStorage.setItem('members', JSON.stringify(members));

        alert('등록되었습니다.');
        console.log('회원 리스트', members);

        // form 초기화
        this.reset();

        // 테이블 세팅
        setList();


        return false;
    });

    $(id).focus(function () {
        $('#userID+div.msg').css({display : 'none'});
        $('#userID+div.msg').html('');
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
}); // ready fnc

    function setList(){

        var list = $('#list');

        var tbody = '<tr>';
        tbody += '  <th>순번(index)</th>';
        tbody += '  <th>아이디</th>';
        tbody += '  <th>비밀번호</th>';
        tbody += '  <th>이름</th>';
        tbody += '  <th>관리</th>';
        tbody += '</tr>';

        if(members.length < 1){
            tbody += '<tr>';
            tbody += '<td colspan="5">데이터가 존재하지않습니다.</td>';
            tbody += '</tr>';
    
        } else {
    
            for (var i = 0; i < members.length; i++) {
                tbody += '<tr>';
                tbody += '  <td>' + i + '</td>';
                tbody += '  <td>' + members[i].userid + '</td>';
                tbody += '  <td>' + members[i].pw + '</td>';
                tbody += '  <td>' + members[i].username + '</td>';
                tbody += '  <td> <a href="javascript:editMember('+i+')">수정</a> |' +
                    '<a href="javascript:deleteMember('+i+')">삭제</a></td>';
                tbody += '</tr>';
            }
        }

        $(list).html(tbody);
    }; // setList

    // 배열의 요소 삭제 함수
    function deleteMember(index) {
        //alert(index + ' 인덱스의 요소를 삭제합니다.');

        //var chk = confirm('삭제하시겠습니까?');

        // 배열의 index 요소를 삭제
        // splice(index, count) : index에서 시작해서 count 만큼의 요소를 삭제하고 남은 요소의 배열을 반환
        // splice(index, 1)
        if(confirm('삭제하시겠습니까?')){
            members.splice(index, 1);
            alert('삭제되었습니다.');

            // 저장
            localStorage.setItem('members', JSON.stringify(members));

            // 테이블 리스트를 갱신
            setList();
        }

    }


    // 배열의 요소 수정 함수
    function editMember(index) {

        // 수정 폼 영역이 노출되어야 한다!
        $('#editFormArea').css({display :'block'});

        // 이전 데이터를 폼에 세팅
        $('#editId').val(members[index].userid);
        $('#editPw').val(members[index].pw);
        $('#editRePw').val(members[index].pw);
        $('#editName').val(members[index].username);
        $('#index').val(index);

       $('#editForm').submit(function(){

            // 비밀번호와 비밀번호 확인이 같은지 체크
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
            localStorage.setItem('members', JSON.stringify(members));

            alert('수정되었습니다.');

            // 테이블 세팅
            setList();

            editMemberClose();

            return false;
       });

    }



    function editMemberClose(){
        $('#editFormArea').css({display : 'none'});
    }