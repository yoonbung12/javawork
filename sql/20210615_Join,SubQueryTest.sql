--1 마당서점의고객이요구하는다음질문에대해SQL 문을작성하시오.

--(5) 박지성이구매한도서의출판사수
select * from orders;

select count(publisher)
from orders  natural join book
where custid = (select custid
                from customer
                where name = '박지성');

