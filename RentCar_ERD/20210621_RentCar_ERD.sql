--member 테이블 생성하기
create table member(
     membercode number(4) constraint member_membercode_pk primary key,
     id VARCHAR2(20) not null,
     pw varchar2(20) not null,
     name varchar2(20) not null,
     carreg VARCHAR2(20) not null,
     email varchar2(20) not null,
     address varchar2(20) not null
);
desc  member;
select * from member;

--회원 정보 입력
insert into member
values( 1 , 'coat', 'aaaa', '파이', '90000-0000000', 'asd@naver.com', 'SEOUL');

insert into member
values ( 2, 'suhit', 'bbbb', '수힛', '900000-2222222', 'su@gmail.com', 'BUSAN');

insert into member
values (3, 'yena', 'cccc', '예나', '9000000-2333333', 'yena@daum.net', 'SEOUL');

--CarManage 테이블 만들기
create table carmanage (
    carcode number(4) constraint carmanage_carcode_pk primary key,
    carname VARCHAR2(20) not null,
    carnumber INTEGER not null,
    carsize varchar2(20) not null,
    cardate date,
    usingcar number(3)
);
drop table carmanage;
desc carmanage;
select * from carmanage;

insert into carmanage
values ( 10, '모닝', 1010, '경차', '20200101', null);

insert into carmanage
values (20, '소나타', 2012, '중형차', '20200215', null);

insert into carmanage
values ( 30, '투싼', 3333, 'SUV', '20210505', null);

--carinfo table 만들기
create table carinfo (
    year number not null,
    carseat number not null,
    fuel varchar2(20) not null,
    carmanage_carcode number(4) references carmanage(carcode)
);
drop table carinfo;
select * from carinfo;

insert into carinfo 
values ( 2018, 4, '휘발유', 10);

insert into carinfo
values ( 2020, 5, 'LPG', 20);

insert into carinfo
values( 2020, 9, '휘발유', 30);