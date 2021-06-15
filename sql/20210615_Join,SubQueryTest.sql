--1 마당서점의고객이요구하는다음질문에대해SQL 문을작성하시오.

--(5) 박지성이구매한도서의출판사수
select * from orders;

select count(publisher)
from orders  natural join book
where custid = (select custid
                from customer
                where name = '박지성');
--또하나의 풀이                
select count(distinct publisher)
from customer c, orders o, book b
where c.custid = o.custid and o.bookid=b.bookid
and c.name = '박지성';

--(6) 박지성이구매한
--도서의이름, 가격, 정가와판매가격의차이
select saleprice from orders;
select price from book;
select b.price, o.saleprice
from book b, orders o
where b.bookid = o.bookid
;

select bookname, price, price - saleprice
from orders natural join book
where custid = (select custid
                from customer
                where name = '박지성');
--또 하나의 풀이
select b.bookname, b.price, b.price -o.saleprice 
from orders o, book b
where o.bookid = b.bookid
and custid = ( select custid from customer where name = '박지성');

                
--(7) 박지성이구매하지않은도서의이름
-----
select *
from book;
--서브쿠워리
select bookname
from book minus(select bookname
            from book
            where bookid in( select bookid
                            from orders
                            where custid = (select custid
                                            from customer
                                            where name = '박지성')));
                                            
--join
select bookname
from book
where bookname not in(select bookname
                        from book natural join orders natural join customer
                        where name = '박지성');


