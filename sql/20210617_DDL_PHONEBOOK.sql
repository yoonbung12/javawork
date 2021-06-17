--PhoneBook DDL : 테이블 정의서 를 참고 해서 DDL 작성한다.
create table phoneInfo_basic(
        idx number(6) constraint pi_basic_idx_PK primary key,
        fr_name VARCHAR(20) not null,
        fr_phonenumber VARCHAR2(20) not null,
        fr_email VARCHAR2(20),
        fr_address VARCHAR2(20),
        fr_regdate date default sysdate
);

--insert : create
DESC phoneInfo_basic;
insert into phoneInfo_basic
values(PI_IDX_PK.nextval, 'KING', '010-0000-0000', 'king@gmail.com','KOREA', sysdate)
;

insert into phoneInfo_basic (idx, fr_name, fr_phonenumber)
values (PI_IDX_PK.nextval, 'SCOTT', '010-9999-9999')
;

--select : READ
select * from phoneInfo_basic;
select * from phoneInfo_basic where idx = 2;

--UPDATE : UPDATE
--SCOTT의 이메일을 업데이트, 주소도 업데이트
--SCOTT 의 idx -> 2
update phoneInfo_basic
set fr_email = 'scott@naver.com', fr_address='서울'
where idx=2 --fr_name='SCOTT'
;

--DELETE : DELETE
delete from phoneInfo_basic
where idx= 1
;

create table phoneInfo_univ (
    idx number(6),
    fr_u_major VARCHAR2(20) default 'N' not null,
    fr_u_year number(1) default 1 not null, --check(fr_u_year between 1 and 4),
    fr_ref number(6) not null,
    constraint pi_univ_idx_PK primary key(idx),
    constraint chk check (fr_u_year between 1 and 4), --테이블 레벨에서 check 제약 설정
    constraint pi_univ_ref_FK FOREIGN KEY(fr_ref) REFERENCES phoneInfo_basic(idx)
);

--INSERT : CREATE 데이터 생성
--대학친구의 정보를 입력
--1 basic 정보 입력
insert into phoneInfo_basic
values(PI_IDX_PK.nextval, 'SON', '010-1111-1111', 'son@gmail.com', 'KOREA', sysdate)
;
--2.univ 정보 입력
insert into phoneInfo_univ
values (PI_U_IDX_PK.nextval, 'COMPUTER', 4, pi_idx_pk.currval)
;

--SELECT : READ 데이터 검색
select pb.idx, pu.idx, fr_name, pu.fr_u_major, pu.fr_u_year
from phoneinfo_basic pb, phoneInfo_univ pu
where pb.idx=pu.fr_ref
;

--UPDATE : UPDATE 데이터 수정
--전공과 학년을 수정, idx 또는 외래키
update phoneInfo_univ
set fr_u_major = 'KOR', fr_u_year = 1
where idx = 1
;

--DELETE : DELETE 데이터 삭제
delete from phoneInfo_univ
where idx = 1
;
delete from phoneInfo_basic
where idx = 3
;

--phoneInfo_com
create table phoneInfo_com (
    idx number(6) constraint pi_com_idx_PK primary key,
    fr_c_company VARCHAR(20) default 'N' not null,
    fr_ref number(6) not null constraint pi_com_ref_FK REFERENCES phoneInfo_basic (idx)
);
--INSERT : CREATE
insert into phoneInfo_basic
values (pi_idx_pk.nextval, 'PARK', '010-7777-7777', 'park@gmail.com', 'LONDON', sysdate)
;

insert into phoneInfo_com values (PI_C_IDX_PK.nextval, 'NAVER', pi_idx_pk.currval);

--select : read
select pb.idx, pc.idx, fr_name, pb.fr_phonenumber, pb.fr_email, pb.fr_address, pc.fr_c_company
from phoneInfo_basic pb, phoneInfo_com pc
where pb.idx = pc.fr_ref
;

--UPDATE : UPDATE
--회사정보를 수정
update phoneInfo_com
set fr_c_company = 'GOOGLE'
where idx =1
;

--DELETE : DELETE
delete from phoneInfo_com
where idx = 1
;
delete from phoneInfo_basic
where idx=4
;

--전체 친구 정보
select * 
from phoneInfo_basic pb, phoneInfo_univ pu, phoneInfo_com pc
where pb.idx=pu.fr_ref(+) and pb.idx=pc.fr_ref(+)

