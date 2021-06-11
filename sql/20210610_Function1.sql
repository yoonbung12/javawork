--2021.06.10
--function : 단일행 함수, 집합(다중행)함수
--단일행 함수 : 하나의 행이 포함하는 특정 컬럼의 값 하나를 처리하고 반환
--다중행(집합) 함수 : 여러행의 특정 컬럼값들을 대상으로 연산하고 반환

--숫자 함수
select abs(10), abs(-10)
from dual
;

select floor(15.7)
from dual;

select ROUND(15.198,2), ROUND(15.193,-1) -- 마이너스는 정수방향,
from dual
;
select TRUNC(15.79,1), TRUNC(15.79)
from dual;

select MOD(11,5)
from dual;

--사원들의 급여
select sal, mod(sal, 2)
from emp
where mod(sal, 2) = 1
;

--문자함수
--concat(문자, 문자) -> '' || ''
select concat('abc', 'efg'), 'abc' || 'efg'
from dual
;

-- substr : 문자열 추출
-- substr(문자열, 시작 위치, 길이)
select substr('apple', 1, 3)
from dual;

select substr('apple', -3)
from dual;

--replace : 특정 문자열(패턴) 다른 문자열(패턴)으로 변경
select REPLACE('JACK and JUE','J','**')
from dual;

select sysdate
from dual;

------------------------------------
--2021.06.11
------------------------------------

--형변환 함수******
--날짜 -> 문자, 숫자->문자
-- to_char(날짜데이터, '패턴'), to_char(숫자, "패턴")
select sysdate, to_char(sysdate, 'YYYY.MM.DD. HH24:MI:SS')
from dual;

select ename, hiredate, to_char(hiredate, 'YYYY.MM.DD')
from emp
;

SELECT * FROM ORDERS;
SELECT ORDERID, ORDERDATE, TO_CHAR(orderdate, 'YYYY.MM.DD') 
FROM ORDERS
;

--숫자 -> 문자
SELECT TO_CHAR(123456, '0,000,000,000'), TO_CHAR(123456, 'L9,999,999,999')
FROM DUAL;

SELECT EMPNO, ENAME, SAL, TO_CHAR(SAL*1100, 'L999,999,999')
FROM EMP;

--'1,000,000' + 100000

--문자 -> 숫자,
--TO_NUMBER(문자열, 패턴)
--select to_number('1,000,000', '9,999,999), 
select to_number('1,000,000', '9,999,999'),
       to_number('1,000,000', '9,999,999') + 1000
FROM DUAL
;
--문자 -> 날짜
--to_date(문자열, 패턴)
select to_date('2012-05-17', 'YYYY-MM-DD'), 
        trunc((sysdate-to_date('2012.05.17', 'YYYY.MM.DD'))/365)
from dual;

--decode 함수 : 분기를 위해 사용 switch-case 유사하다
--decode (컬럼, = 조건1 값, 조건1의 참일때 사용할 값
--          ,조건2 값, 조건2의 참일때 사용할 값
--          ,....
--)

--emp 테이블에서 부서번호에 맞는 부서이름 출력
select * from dept;

--10 accounting
--20 research
--30 sales
--40 operations
select ename, deptno,
    decode(deptno, 10, 'ACCOUNTING',
                    20, 'RESEARCH',
                    30, 'SALES',
                    40, 'OPERATION'
        ) as DNAME
from emp
order by dname
;
--8. 직급에 따라 급여를 인상하도록 하자.
--직급이 'ANAlYST'인 사원은 5%,
--'SALESMAN'인 사원은 10%, 
--'MANAGER'인 사원은 15%,
--'CLERK'인 사원은 20%인 인상한다.
select ename, sal,
        decode(job, 'ANALYST', sal *1.05, --5%인상
                    'SALESMAN', sal*1.1, --10%인상
                    'MANAGER', sal*1.15, 
                    'CLERK', sal*1.2 
            ) as upsal
from emp
;

-- case 함수도 분기할 때 사용
-- case when 조건식 then 참일때 값
select ename, deptno,
        case when deptno = 10 then 'ACCOUNTING'
            when deptno =20 then 'RESEARCH'
            when deptno =30 then 'SALESMAN'
            when deptno = 40 then 'OPERATIONS'
        end as deptname
from emp
order by deptname desc
;