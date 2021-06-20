--1.전화 번호부 만들기
----- 기본정보(not null)
-- 대리키 : 일련번호   -> pIdx 기본키로 설정
-- 이름, 전화번호, 주소, 이메일 
-- 주소값과 이메일은 입력이 없을 때 기본값 입력
-- 친구의 타입 (카테고리)   : univ, com, cafe 세가지 값만 저장 가능
------ 선택 정보
-- 전공, 학년
-- 회사이름, 부서이름, 직급
-- 모임이름, 닉네임
create table phonebook(
    pidx number(5) constraint phonebook_pidx_pk primary key,
    name varchar2(20) constraint contact_name_nn not null,
    phonenumber varchar2(20) constraint contact_phonenumber_nn not null,
    address varchar2(20),
    email varchar2(20),
    regdate date default sysdate,
    friendtype varchar2(20) constraint contact_friendtype_ck check(friendtype in('univ', 'com','cafe')),
    
    major varchar2(20),
    grade number(1),
    companyname varchar2(20),
    dname varchar2(20),
    job varchar2(20),
    groupname varchar2(20),
    nickname varchar2(20)
    
);
--2. DEPT 테이블에 데이터를 삽입하는 SQL을 작성하시오. 입력 데이터는 임의로 작성하시오.
--insert into dept
--values (50, 'COMPUTER', 'KOREA');
insert into dept values(50, 'COMPUTTER', 'KOREA');
select * from phonebook;
select * from dept;
--3. DEPT 테이블에 위에서 삽입한 데이터의 dname, loc 데이터를 변경하는 SQL을 작성하시오.
--입력 데이터는 임의로 작성하시오.
update dept
set dname = 'computer', loc = 'busan'
where deptno = 50;

--4. DEPT 테이블에 위에서 삽입한 데이터를 deptno 컬럼의 값을 기반으로 삭제하는 SQL을 작성하시오.
delete from dept
where deptno = 50;

--5. 사용자가 보유한 테이블 목록을 확인하는 SQL문을 작성하시오.
select * from tab;

--6. EMP 테이블의 구조를 확인하는 SQL을 작성하시오.
desc emp;

--7. 사용자가 정의한 제약조건들을 확인하는 SQL문을 작성하시오.
select * 
from all_constraints
where table_name = '테이블명';

--8-1
--EMP 테이블의 ename 컬럼에 인덱스를 생성하는 SQL을 작성하시오. 인덱스의 이름은 emp_index
create index emp_index
on emp(ename);

--EMP테이블과 Dept 테이블을 조인하는 SQL을 기반으로 view 객체를 생성하는 SQL을 작성하시오.
--view 의 이름은 emp_view로 하시오.
create view emp_view
as
select *
from emp e, dept d
where e.deptno = d.deptno;

--emp 테이블에서 모든 사원의 부서번호를 이름이 'SCOTT'인 사원의 부서번호로 변경하는 sql을 작성하시오.
update emp
set deptno = (select deptno
                from emp
                where ename ='SCOTT');

drop table phoneBook;