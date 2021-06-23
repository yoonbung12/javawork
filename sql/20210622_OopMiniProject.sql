--OopMiniProject

create table member(
membercode number(4) constraint member_mcode_pk primary key,
id varchar2(20) not null,
pw varchar2(20) not null,
name varchar2(20) not null,
carreg varchar2(14) not null,
email varchar2(40) not null,
address varchar2(40) not null
)
;
desc member;
select * from member;

CREATE SEQUENCE member_membercode_SEQ
INCREMENT BY 1
START WITH 1;

insert into member values
(1, 'member1', '123456', '손흥민', '123456-1234567', 'member1@naver.com', 'SEOUL');
insert into member values
(2, 'member2', '123456', '케인', '234567-1345678', 'member2@google.com', 'PUSAN');
insert into member values
(3, 'member3', '123456', '모우라', '345678-1456789', 'member3@daum.net', 'DAEJEON');
insert into member values
(4, 'member4', '123456', '알리', '456789-1567890', 'member4@naver.com', 'JEJU');
insert into member values
(5, 'member5', '123456', '베일', '567890-1678901', 'member5@google.com', 'SEOUL');

commit;
create table car(
carcode number(4) constraint car_carcode_pk primary key,
carnumber varchar2(20) not null,
carname varchar2(20) not null,
carsize varchar2(10) not null,
carseat number(2) not null,
caryear number(4) not null,
fuel varchar2(20) not null
);

desc car;
select * from car;

insert into car values(1, '1111', 'RAY', 'SMALL', 5, 2017, '가솔린');
insert into car values(car_carcode_seq.nextval, '2222', 'NIRO EV', 'SMALL', 5, 2022, '전기');
insert into car values(3, '3333', 'SONATA', 'MIDDLE', 5, 2020, '가솔린');
insert into car values(4, '4444', 'MODEL3', 'MIDDLE', 5, 2020, '전기');
insert into car values(5, '5555', 'BMW X7', 'BIG', 7, 2020, '가솔린');
insert into car values(6, '6666', 'CARNIVAL', 'BIG', 9, 2021, '가솔린');

CREATE SEQUENCE car_carcode_SEQ
INCREMENT BY 1
START WITH 1;
select * from car;

select To_Char(rent_date + 3, 'yyyymmdd')from dual;
select to_date(sysdate,yy/mm/dd)
from dual
;

select date, rent_date + 1 from dual;
select date

create table manager(
managercode number(4) constraint manager_managercode_pk primary key,
mid varchar2(20) not null,
mpw varchar2(20) not null
);

desc manager;
select * from manager;

insert into manager values(1, 'manager1', '123123');
insert into manager values(2, 'manager2', '321321');


CREATE SEQUENCE manager_managercode_SEQ
INCREMENT BY 1
START WITH 1;

-----------------------------------------------------
create table rent(
rentcode number(4) constraint rent_rentcode_pk primary key, --대여 번호
pay INTEGER not null,  --가격지불
rentperiod number(1) not null, --대여기간
rent_date date, --대여 날짜
--대여기간을 더하여 반납해야할 날짜
carcode number constraint rent_carcode_fk REFERENCES car(carcode),
membercode number constraint rent_memcode_fk REFERENCES member(membercode),
managercode number constraint rent_managercode_fr REFERENCES manager(managercode)
);

create table rent(
rentcode number(4) constraint rent_rentcode_pk primary key, --대여 번호
pay INTEGER not null,  --가격지불
rentperiod number(1) not null, --대여기간
rent_date date, --대여 날짜
--대여기간을 더하여 반납해야할 날짜
carcode number constraint rent_carcode_fk REFERENCES car(carcode),
membercode number constraint rent_memcode_fk REFERENCES member(membercode),
managercode number constraint rent_managercode_fr REFERENCES manager(managercode)
);
--반납일 추가
alter table rent add enddate date;
select * from rent_view order by rentcode;
select * from rent;



alter table rent add 
CREATE SEQUENCE rent_rentcode_SEQ
INCREMENT BY 1
START WITH 1;

insert into rent values(1, 1000, 2, '2021/06/21', 1, 1 , 1);
commit;

desc rent;
select * from rent;

CREATE SEQUENCE rent_idx_SEQ
INCREMENT BY 1
START WITH 1;

create table rent_order (
order_num number(20) primary key,
order_membercode number(4) references member(membercode) on delete cascade,
order_carname varchar2(20),
order_date date,
order_time varchar2(20)
);

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

insert into rent_view values(Rent_rentcode_SEQ.nextval,1001,3,'20210622', car_carcode_SEQ.currval, 1,member_membercode_SEQ.currval);
-----------------------------------------------------