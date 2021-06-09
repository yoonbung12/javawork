
--주석
--2021.06.09

--emp, dept DDL -> DML
--Table 생성 -> 테이블에 데이터를 저장(insert)

drop table emp; --삭제한다(drop)
drop table dept;

create table dept(                        -- dept table 생성
  deptno number,                          -- 부서번호
  dname  varchar2(14),                    -- 부서명
  loc    varchar2(13),                    -- 부서위치
  constraint pk_dept primary key (deptno) -- deptno로 pk설정
);

create table emp(                         -- emp table 생성
  empno    number,                        -- 직원번호
  ename    varchar2(10),                  -- 직원명
  job      varchar2(9),                   -- 직책
  mgr      number(4),                     -- 담당매니저
  hiredate date,                          -- 입사일
  sal      number(7),                     -- 월급
  comm     number(7),                     -- 보너스
  deptno   number,                        -- 부서번호
  constraint pk_emp primary key (empno)   -- empno로 pk설정
);

-- DEPT DML
insert into dept values(10, 'ACCOUNTING', 'NEW YORK');
insert into dept values(20, 'RESEARCH', 'DALLAS');
insert into dept values(30, 'SALES', 'CHICAGO');
insert into dept values(40, 'OPERATIONS', 'BOSTON');

-- EMP DML
insert into emp values( 7839, 'KING', 'PRESIDENT', null, to_date('1996-11-17','yyyy-mm-dd'), 5000, null, 10);
insert into emp values( 7698, 'BLAKE', 'MANAGER', 7839, to_date('1991-1-05','yyyy-mm-dd'), 2850, null, 30);
insert into emp values( 7782, 'CLARK', 'MANAGER', 7839, to_date('1999-9-06','yyyy-mm-dd'), 2450, null, 10);
insert into emp values( 7566, 'JONES', 'MANAGER', 7839, to_date('2001-02-04','yyyy-mm-dd'), 2975, null, 20);
insert into emp values( 7788, 'SCOTT', 'ANALYST', 7566, to_date('2003-06-17','yyyy-mm-dd'), 3000, null, 20);
insert into emp values( 7902, 'FORD', 'ANALYST', 7566, to_date('1981-03-12','yyyy-mm-dd'), 3000, null, 20);
insert into emp values( 7369, 'SMITH', 'CLERK', 7902, to_date('2007-12-1','yyyy-mm-dd'), 800, null, 20);
insert into emp values( 7499, 'ALLEN', 'SALESMAN', 7698, to_date('20-2-1981','dd-mm-yyyy'), 1600, 300, 30);
insert into emp values( 7521, 'WARD', 'SALESMAN', 7698, to_date('22-2-1981','dd-mm-yyyy'), 1250, 500, 30);
insert into emp values( 7654, 'MARTIN', 'SALESMAN', 7698, to_date('28-9-1981','dd-mm-yyyy'), 1250, 1400, 30);
insert into emp values( 7844, 'TURNER', 'SALESMAN', 7698, to_date('8-9-1981','mm-dd-yyyy'), 1500, 0, 30);
insert into emp values( 7876, 'ADAMS', 'CLERK', 7788, to_date('13-7-87', 'dd-mm-yy') - 51, 1100, null, 20);
insert into emp values( 7900, 'JAMES', 'CLERK', 7698, to_date('3-12-1981','mm-dd-yyyy'), 950, null, 30);
insert into emp values( 7934, 'MILLER', 'CLERK', 7782, to_date('2003-1-23','yyyy-mm-dd'), 1300, null, 10);

commit; --물리적으로 관리가 된다.

--검색 : 질의 -> select 
select * from dept;
select * from emp;

