--20210616

--view

--자주 사용되는
--30번 부서에 소속된 사원들의 사번과 이름과 부서번호를 출력하기 위한
--select문을 하나의 뷰로 정의해 봅시다.

create or replace view emp_view30
as
select empno, ename, deptno
from emp
where deptno = 30;

select * from emp_view30
where ename = 'JAMES';

--view 삭제
drop view em1p_view30;
drop view emp_view30;

--인라인 뷰를 이용한 TOP3 구하기
select ROWNUM, ename
from emp
--order by hiredate
;

select rownum, ename
from (select * from emp order by ename)
;

--입사일이 빠른 사람 5명만(top-n)을 얻어오기
select rownum, ename, hiredate
from (select * from emp order by hiredate) --입사일이 최근입사한 사람이면 hiredate 뒤에 desc쓸것
where rownum < 6
;

select * from emp order by hiredate;

create or replace view view_HIR
as
select * from emp order by hiredate;

select rownum, ename, hiredate from view_hir;

select rownum, ename, hiredate 
from view_hir
where rownum < 6
order by ename
;

--sequence : 번호 재생기
create sequence pi_idx_pk;

select pi_idx_pk.nextval
from dual;

select pi_idx_pk.currval
from dual;