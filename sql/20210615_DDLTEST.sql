--첫번째 테이블 만들어보자
--테이블 명 : phoneInfo_basic
--컬럼 명: idx, fr_name, fr_phonenumber, fr_email, fr_address, fr_regdate
create table phoneInfo_basic(
        idx number(6) constraint basic_idx_pk primary key,
        
        fr_name varchar2(20) constraint basic_fr_name_nn not null,
        
        fr_phonenumber varchar2(20) constraint basic_fr_phonenumber_nn not null,
        
        fr_email varchar2(20),
        
        fr_address varchar2(20),
        
        fr_regdate Date default sysdate
);
select * from phoneInfo_basic;
drop table phoneInfo_basic;

--phoneInfo_univ 테이블 만들기
create table phoneInfo_univ(
        idx number(6) constraint univ_idx_pk primary key,
        
        fr_u_major varchar2(20) default 'N'
        constraint univ_fr_u_major_nn not null,
        
        fr_u_year number(1) default  1
        constraint univ_year_nn not null
        constraint univ_year_ck check(0 < fr_u_year and fr_u_year <5),
        
        fr_ref number(7) constraint univ_ref_fk references phoneInfo_basic(idx)
);        
desc phoneInfo_univ;        
drop table phoneInfo_univ;        

--phoneInfo_com 테이블 만들기
create table phoneInfo_com (
        idx number(6) 
        constraint pic_idx_pk primary key
        constraint pic_idx_nn not null,
        
        fr_c_company varchar2(20) default 'N'
        constraint pic_fr_c_company_nn not null,
        
        fr_ref number(6) 
        constraint pic_fr_ref_fk references phoneInfo_basic(idx)
        constraint pic_fr_ref_nn not null
);
desc phoneInfo_com
drop table phoneInfo_com;        