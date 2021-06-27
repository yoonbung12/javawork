-- final sql oop mini pr 

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
);
--car 테이블 생성
create table car(
carcode number(4) constraint car_carcode_pk primary key,
carnumber varchar2(20) constraint car_cnum_uk unique not null,
carname varchar2(20) not null,
carsize varchar2(10) not null,
carseat number(2) not null,
caryear number(4) not null,
fuel varchar2(20) not null,
rentck number(1) constraint car_rentck_ck check(rentck between 0 and 1) not null

-- rentck 는 대여현황을 체크하기 위한 컴럼으로 
-- 0 이면 대여가 가능한 상태임을 나타내고 1이면 대여중임을 나타낸다
);
--manager 테이블 생성
create table manager(
managercode number(4) constraint manager_managercode_pk primary key,
mid varchar2(20) not null,
mpw varchar2(20) not null
);
--manager 데이터 입력
insert into manager values(1,'admin','1234');

--rent table 생성 
create table rent(
rentcode number(4) constraint rent_rentcode_pk primary key,
pay number(6) not null,
rentperiod number(1) constraint rent_period_ck check(rentperiod between 1 and 3)  not null,
rent_date date default sysdate , --대여 날짜
carcode number constraint rent_carcode_fk REFERENCES car(carcode) on delete cascade not null,
membercode number constraint rent_membercode_fk REFERENCES member(membercode) on delete cascade constraint rent_membercode_uk unique not null,
managercode number constraint rent_managercode_fk REFERENCES manager(managercode) on delete cascade
);

create table pay(
paycode number(4) constraint pay_paycode_pk primary key,
paymoney number(8) constraint pay_paymoney_ck check(paymoney= 10000 or paymoney=20000 or paymoney=30000), --결제 금액을 제약 조건으로 (소,중,대)
carsize varchar2(10) not null
);
--pay 테이블 데이터 입력
insert into pay values(pay_paycode_seq.nextval, 10000,'small');
insert into pay values(pay_paycode_seq.nextval, 20000,'middle');
insert into pay values(pay_paycode_seq.nextval, 30000,'big');

--각 테이블의 전체 컬럼 보기
select * from manager;
select * from car;
select * from rent;
select * from member;
select * from pay;

-- rent table의 데이터가 들어가는 sql문 --> 6.26 : 자동차사이즈를 입력받아 저장된 금액과 날짜를 곱해 결제 금액을 알려주는 sql 작성완료.                                                                                                 
insert into rent values(rent_rentcode_seq.nextval,
                        3 * (select paymoney from pay where carsize = 'small'),
                        3,sysdate+3,(select carcode from car where carnumber = 4444),
                        (select membercode from member where carreg = '3'),
                        1
                        );
                        
-- equi 조인을 이용한 고객 id로 현재 고객이 대여하고 있는 정보를 나타내는 sql문                         
select r.rentcode, c.carnumber, c.carname, c.carsize, m.id, m.name, m.carreg, r.pay,r.rentperiod,r.rent_date 
from car c, member m, rent r 
where c.carcode = r.carcode and m.membercode = r.membercode and m.id = 'member';



--member 시퀀스 생성
CREATE SEQUENCE member_membercode_SEQ
INCREMENT BY 1
START WITH 1;
-- member 시퀀스 삭제
drop sequence member_membercode_seq;

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
--pay 시퀀스 생성
create sequence pay_paycode_seq
start with 1
increment by 1;
--pay 시퀀스 삭제
drop sequence pay_paycode_seq;


--커밋
commit;