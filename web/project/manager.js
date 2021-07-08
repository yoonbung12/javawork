

// 회원의 정보 : 아이디, 비밀번호, 이름
// Member -> 생성자 함수를 정의

function Member(id, pw, name){
    this.userId=id;
    this.pw=pw;
    this.userName=name;
}
//////////////생성자 함수
// 객체가 가지는 메소드는 공통으로 사용 -> prototype
Member.prototype.makeHtml = function() {
    return '[id:' +this.userId+ ', pw:' +this.pw+', name: '+this.name+']'
};

//////////////////////////////
//
var member = new Member();

/////////////////////////////////////

// 사용자 입력한 정보를 가지고 Member 객체를 생성
// submit 이벤트 연결

window.onload = function (){
    
    // 테이블 세팅
    setList();

    var userid = document.querySelector('#userID');
    var pw = document.querySelector('#pw');
    var repw = document.querySelector('#repw');
    var userName = document.querySelector('#userName');


    // regForm 캐스팅
    var regForm = document.getElementById('regForm');
    regForm.onsubmit = function(){
        //alert('submit');  //확인해볼려고 alert사용
        
        // 사용자가 입력한 값
        // var userid = document.querySelector('#userID').Value;
        // var pw = document.querySelector('#pw').value;
        // var repw = document.querySelector('#repw').value;
        // var userName = document.querySelector('#userName').value;
        

        if(userid.value.trim().length < 1) {
            // alert('이름을 입력해주세요.');
            document.querySelector('#userID+div.msg').innerHTML='필수항목입니다.';
            document.querySelector('#userID+div.msg').style.display='block';
            return false;
        }

        if(pw.value.trim().length<1 ){
            //alert('비밀번호를 입력해주세요.');
            document.querySelector('#pw+div.msg').innerHTML='필수항목입니다.';
            document.querySelector('#pw+div.msg').style.display='block';
            return false;
        }

        if(repw.value.trim().length<1){
            document.querySelector('#repw+div.msg').innerHTML='필수항목입니다.';
            document.querySelector('#repw+div.msg').style.display='block';           
        }

        // 비밀번호 비밀번호 확인 일치 여부 체크
        if(pw.value.trim() != repw.trim()) {
            //alert('비밀번호와 비밀번호 확인과 일치하지 않습니다. \n 다시 확인해주세요');
            document.querySelector('#repw+div.msg').innerHTML='비밀번호가 일치하지않습니다.';
            document.querySelector('#repw+div.msg').style.display='block';
            return false;
        }
        // 사용자 이름 정보
        if(userName.value.trim()<1) {
            alert('이름을 입력해주세요.');
            return false;
        }

        console.log(userid.value);
        console.log(pw.value);
        console.log(repw.value);
        console.log(userName.value);  
        
        // 객체 생성
        // var member = new Member(userid.value, pw.value, userName.value);
        // console.log(typeof member, member.makeHtml());
        
        // 배열에 사용자 정보를 추가
        members.push(new Member(userid.value, pw.value, userName.value));
        
        alert('등록되었습니다.');
        console.log('회원 리스트', members);

        // form 초기화
        this.reset();
        
        return false;
    }

    userid.addEventListener('focus', function(){
        document.querySelector('#userID+div.msg').style.display='none';
        document.querySelector('#userID+div.msg').innerHTML='';
        // userid.value=''; 
    });

    pw.addEventListener('focus', function(){
        document.querySelector('#pw+div.msg').style.display='none';
        document.querySelector('#pw+div.msg').innerHTML='';
    //    userid.value=''; 
    });

    repw.addEventListener('focus', function(){
        document.querySelector('#repw+div.msg').style.display='none';
        document.querySelector('#repw+div.msg').innerHTML='';
        repw.value='';
    });

    userName.addEventListener('focus', function(){
        document.querySelector('#userName+div.msg').style.display='none';
        document.querySelector('#userName+div.msg').innerHTML='';
    //    userid.value=''; 
    });

}

// 배열에 있는 요소를 -> table tr 행을 만들어서 출력
function setList(){

    // table 의 tbody 캐스팅
    var list = document.querySelector('#list');

    //     <tr>
    //     <td>순번(index)</td>
    //     <td>아이디</td>
    //     <td>비밀번호</td>
    //     <td>이름</td>
    //     <td>관리</td>
    // </tr>


    var tbody = '<tr>';
    tbody += '      <th>순번(index)</th>';
    tbody += '      <th>아이디</th>';
    tbody += '      <th>비밀번호</th>';
    tbody += '      <th>이름</th>';
    tbody += '      <th>관리</th>';
    tobdy += '  </tr>';
    if(members.length<1){

        // tbody += '<tr>';
        // tbody += '<td colspan="5">데이터가 존재하지 않습니다.</td>';
        // tbody += '</tr>';

    } else {
        
        for(var i=0; i<members.length; i++) {
            
            tbody += '<tr>';
            tbody += ' <td>'+i+ '<td>';
            tbody += ' <td>'+members[i].userId+ '<td>';
            tbody += ' <td>'+members[i].pw+ '<td>';
            tbody += ' <td>'+members[i].userName+ '<td>';
            tbody += '</tr>';
        }
    }

    

    list.innerHTML = tbody;

}