--2021.06.11

--join

--CROSS JOIN
--스키마의 합 : 집합의 곱 연산과 같다

select *
from emp, dept
where emp.deptno = dept.deptno
;

--이름이 scott인 사람의 where e.ename = 'scott'
--부서명을 출력해봅시다.
--출력해야하는 컬럼을 가지는 테이블을 확인해보자
--이름 : emp, 부서명 : dept 
select e.ename, d.dname, e.deptno
from emp e, dept d
where e.deptno = d.deptno --and ename = 'SCOTT'
order by d.dname, e.ename
;