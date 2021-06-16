--첫번째 테이블 만들어보자 
--테이블 명 : phoneInfo_basic
--컬럼 명: idx, fr_name, fr_phonenumber, fr_email, fr_address, fr_regdate
create table phoneInfo_basic(
        idx number(6) constraint basic_idx_pk primary key,
        fr_name varchar2(20) constraint basic_fr_name_nn not null,
      --fr_name varchar2(20) not null,
      
        fr_phonenumber varchar2(20) constraint basic_fr_phonenumber_nn not null,
      --fr_phonenumber varchar(20) not null,  
       
        fr_email varchar2(20),
        fr_address varchar2(20),
        fr_regdate Date default sysdate
);
--1. phoneInfo_basic 테이블의 SELECT,
--UPDATE, DELETE, INSERT 하는 SQL
select * from phoneInfo_basic;
--insert : create
desc phoneInfo_basic;
insert into phoneInfo_basic --not null인곳은 무조건 채워넣어야함!!!
values (1, 'KING', '010-000-0000', 'king@gmail.com', 'korea', sysdate);

insert into phoneInfo_basic (idx, fr_name, fr_phonenumber)
values(2, 'SCOTT', '010-100-000');

insert into phoneInfo_basic values(10, 'Hong', '010-2303-3321', 'sdue@naver.com',
                                                'seoul', '2021-06-16');
insert into phoneInfo_basic values(20, 'Jong', '010-2303-3221', 'sseeue@naver.com',
                                                'seoul', '2021-06-15');
insert into phoneInfo_basic values(30, 'kong', '010-2303-3321', 'sdue@naver.com',
                                                'seoul', '2021-06-16');                                                
--update
--SCOTT의 이메일을 업데이트, 주소도 업데이트
update phoneInfo_basic
set fr_email = 'scott@naver.com', fr_address = 'SEOUL'
where fr_name = 'SCOTT'; --idx값으로 해주는게 좋음 이름이 중복인 경우도 있을수 있어서.

--hong의 주소를 업데이트 하시오.
update phoneInfo_basic
set fr_address = 'Busan'
where idx = 10;

--delete 
delete from phoneInfo_basic; --전체 삭제
delete from phoneInfo_basic where idx = 1; --idx =1 행을 삭제
delete from phoneInfo_basic where fr_name = 'kong'; --'kong'이름이 있는 행 삭제
select *from phoneInfo_basic;

drop table phoneInfo_basic;



--phoneInfo_univ 테이블 만들기
create table phoneInfo_univ(
        idx number(6) constraint univ_idx_pk primary key,
        
        fr_u_major varchar2(20) default 'N'
        constraint univ_fr_u_major_nn not null,
        
        
        fr_u_year number(1) default  1 
        constraint univ_year_nn not null
        constraint univ_year_ck check(0 < fr_u_year and fr_u_year <5),
                                         --fr_u_year between 1 and 4       
        fr_ref number(7) constraint univ_ref_fk references phoneInfo_basic(idx)
);
--insert : create 데이터 생성
--대학친구의 정보를 입력
--1. basic 정보 입력
desc phoneInfo_basic;
insert into phoneInfo_basic --not null인곳은 무조건 채워넣어야함!!!
values (3, 'SON', '010-110-1100', 'son@gmail.com', 'korea', sysdate);
--2.univ 정보 입력
insert into phoneInfo_univ
values (1, 'COMPUTER', 4, 3) --3은 참조해서 써야된다(외래키)
;

--select : read 데이터 검색
select fr_name, pu.fr_u_major, pu.fr_u_year 
from phoneInfo_basic pb, phoneInfo_univ pu
where pb.idx = pu.fr_ref
;

desc phoneInfo_univ;        
drop table phoneInfo_univ;        

--phoneInfo_com 테이블 만들기
create table phoneInfo_com (
        idx number(6) 
        constraint pic_idx_pk primary key,
        --constraint pic_idx_nn not null,
        
        fr_c_company varchar2(20) default 'N' not null,
        --constraint pic_fr_c_company_nn not null,
        
        fr_ref number(6) 
        constraint pic_fr_ref_fk references phoneInfo_basic(idx)
        constraint pic_fr_ref_nn not null
);
desc phoneInfo_com;
drop table phoneInfo_com;        