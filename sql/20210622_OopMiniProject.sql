--OopMiniProject
--테이블 삭제
drop table rent;
drop table member;
drop table car;
drop table manager;

--데이터 삭제
delete from member;
delete from car;
delete from manager;
delete from rent;
--member table 생성
create table member(
membercode number(4) constraint member_membercode_pk primary key,
id varchar2(20) constraint member_id_uk unique not null,
pw varchar2(20) not null,
name varchar2(20) not null,
carreg varchar2(14) constraint member_carrg_uk unique not null,
email varchar2(40) not null,
address varchar2(40) not null
)
;
--car 테이블 생성
create table car(
carcode number(4) constraint car_carcode_pk primary key,
carnumber varchar2(20) constraint car_cnum_uk unique not null,
carname varchar2(20) not null,
carsize varchar2(10) not null,
carseat number(2) not null,
caryear number(4) not null,
fuel varchar2(20) not null,
rent char(1) constraint car_rent_ck check(rent = '0' or rent = '1') 
-- rent 는 대여현황을 체크하기 위한 컴럼으로 
-- 0 이면 대여가 가능한 상태임을 나타내고 1이면 대여중임을 나타낸다
)
;
--manager 테이블 생성
create table manager(
managercode number(4) constraint manager_managercode_pk primary key,
mid varchar2(20) not null,
mpw varchar2(20) not null
)
;

create table rent(
rentcode number(4) constraint rent_rentcode_pk primary key,
pay number(6) not null,
rentperiod number(1) constraint rent_period_ck check(rentperiod between 1 and 3)  not null,
rent_date date default sysdate , --대여 날짜
carcode number constraint rent_carcode_fk REFERENCES car(carcode) on delete cascade constraint rent_carcode_uk unique,
membercode number constraint rent_membercode_fk REFERENCES member(membercode) on delete cascade constraint rent_membercode_uk unique,
managercode number constraint rent_managercode_fk REFERENCES manager(managercode) on delete cascade
)
;

select sysdate from dual;


insert into rent values(rent_rentcode_seq.nextval,10000,3,sysdate+3,(select carcode from car where carnumber = 1111),(select membercode from member where carreg = 1111),1);
select * from rent where 
select to_char(rent_date, 'yyyy-mm-dd hh24:mi:ss') from rent;
select * from rent;
--데이터 출력
desc member;
select * from member;
select * from car;
--데이터 보기
desc car;
select * from car;

--rent 데이터 보기
desc rent;
select * from rent;

--member 시퀀스 생성
CREATE SEQUENCE member_membercode_SEQ
INCREMENT BY 1
START WITH 1;
-- member 시퀀스 삭제
delete from member_membercode_seq;

--car 시퀀스 생성
CREATE SEQUENCE car_carcode_SEQ
INCREMENT BY 1
START WITH 1;
-- car 시퀀스 삭제
drop sequence car_carcode_seq;

--rent 시퀀스 생성
CREATE SEQUENCE rent_rentcode_SEQ
INCREMENT BY 1
START WITH 1;
--rent 시퀀스 삭제
drop sequence rent_rentcode_seq;

--id 컬럼 삭제
alter table member
drop column id;

alter table member
add unique (id);

--member 데이터 입력
insert into member values
(1, 'member2', '123456', '흥민', '22222', 'member1@naver.com', 'SEOUL');
--member 데이터 삭제
delete from member;
-- member 테이블 삭제
drop table member;


insert into car values(CAR_CARCODE_SEQ.nextval, '2222', 'RAY', 'big', 5, 2018, '가솔린','1');
insert into car values(CAR_CARCODE_SEQ.nextval, '4444', 'lambo', 'small', 2, 2021, '디젤','0');
--car 데이터 삭제
delete from car;



-- 데이터 보기
desc manager;
select * from manager;
--데이터 입력
insert into manager values(1, 'admin', '1234');
--manager 시퀀스 생성
CREATE SEQUENCE manager_mncode_SEQ
INCREMENT BY 1
START WITH 1;
--manager 시퀀스 삭제
drop sequence manager_mncode_seq;

-----------------------------------------------------

--rent 테이블 생성 


--데이터 저장
insert into rent values(rent_rentcode_seq.nextval,10000,2,car_carcode_seq.nextval,member_mcode_seq.nextval,1 );
--테이블 삭제
drop table rent;
--foreign key 삭제하고 
alter table rent drop constraint rent_ccode_fk;
alter table rent drop constraint rent_memcode_fk;
alter table rent drop constraint rent_manacode_fk;
--on delete cascade 제약사항 추가 sql
alter table rent add constraint rent_ccode_fk FOREIGN key (carcode) REFERENCES car(carcode) on delete cascade;
alter table rent add constraint rent_memcode_fk FOREIGN key (membercode) REFERENCES member(membercode) on delete cascade;
alter table rent add constraint rent_manacode_fk FOREIGN key (managercode) REFERENCES manager(managercode) on delete cascade;









--커밋
commit;











--sequence

CREATE SEQUENCE member_membercode_SEQ
INCREMENT BY 1
START WITH 1;


--view
create or replace view rent_view
as
select *
from rent
order by rentcode
;

select * from rent_view
order by rentcode;
commit;


-----------------------------------------------------