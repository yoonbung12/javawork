--OopMiniProject

create table member(
idx number(4) constraint member_idx_pk primary key,
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

CREATE SEQUENCE member_idx_SEQ
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


create table car(
idx number(4) constraint car_idx_pk primary key,
carnumber varchar2(20) not null,
carname varchar2(20) not null,
carsize varchar2(10) not null,
carseat number(2) not null,
caryear number(4) not null,
fuel varchar2(20) not null
)
;
desc car;
select * from car;

insert into car values(1, '1111', 'RAY', 'SMALL', 5, 2017, '가솔린');
insert into car values(2, '2222', 'NIRO EV', 'SMALL', 5, 2022, '전기');
insert into car values(3, '3333', 'SONATA', 'MIDDLE', 5, 2020, '가솔린');
insert into car values(4, '4444', 'MODEL3', 'MIDDLE', 5, 2020, '전기');
insert into car values(5, '5555', 'BMW X7', 'BIG', 7, 2020, '가솔린');
insert into car values(6, '6666', 'CARNIVAL', 'BIG', 9, 2021, '가솔린');

CREATE SEQUENCE car_idx_SEQ
INCREMENT BY 1
START WITH 1;



create table manager(
idx number(4) constraint manager_idx_pk primary key,
mid varchar2(20) not null,
mpw varchar2(20) not null
)
;
desc manager;
select * from manager;

insert into manager values(1, 'manager1', '123123');
insert into manager values(2, 'manager2', '321321');


CREATE SEQUENCE manager_idx_SEQ
INCREMENT BY 1
START WITH 1;

-----------------------------------------------------
create table rent(
idx number(4) constraint rent_idx_pk primary key,
pay INTEGER not null,
rentperiod number(1) not null,
carcode number constraint rent_carcode_fk REFERENCES car(idx),
membercode number constraint rent_memcode_fk REFERENCES member(idx),
managercode number constraint rent_manacode_fr REFERENCES manager(idx)
)
;

desc rent;
select * from rent;

CREATE SEQUENCE rent_idx_SEQ
INCREMENT BY 1
START WITH 1;
-----------------------------------------------------