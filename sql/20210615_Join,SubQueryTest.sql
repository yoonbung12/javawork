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
                                            
--또 다른 풀이
select bookname
from book
where bookid not in(
        select o.bookid
        from orders o, customer c
        where o.custid = c.custid and c.name='박지성'
);

--2 마당서점의 운영자와 경영자가 요구하는 다음질문에대해 SQL 문을작성하시오.
--(8) 주문하지 않은 고객의 이름(부속질의사용)
select C.NAME
from orders o, customer c
where o.custid = c.custid
and o.orderid is null;

select *
from orders o, customer c
where o.custid(+) = c.custid and o.orderid is null
;

select name
from customer
where custid not in(select distinct custid from orders)
;

--(9) 주문 금액의 총액과 주문의 평균금액
select sum(saleprice), avg(saleprice)
from orders;

--(10) 고객의 이름과 고객별 구매액 (group by name)
select c.name, sum(saleprice)
from customer c, orders o
where c.custid = o.custid
group by c.name;

--또 다른 풀이
select o.custid,
        (select c.name from customer c where o.custid = c.custid),
        sum(saleprice)
from orders o
group by o.custid;

--(11) 고객의 이름과 고객이 구매한 도서 목록 
select c.name, b.bookname
from customer c, orders o, book b
where c.custid = o.custid and o.bookid = b.bookid
;

--(12) 도서의 가격(Book 테이블)과 판매가격(Orders 테이블)의
--     차이가 가장 많은 주문
select max(price - saleprice)
from book b, orders o
where b.bookid = o.bookid;

--또다른 풀이
select b.bookname, b.price-o.saleprice
from orders o, book b
where o.bookid = b.bookid
and price - saleprice = (
        select max(b.price-o.saleprice)
        from orders o, book b
        where o.bookid = b.bookid);
        
--(13) 도서의 판매액 평균 보다 
--     자신의 구매액평균이 더높은 고객의 이름
select c.name, avg(saleprice)
from orders o, customer c
where o.custid = c.custid
group by c.name
having avg(saleprice)> (
    select avg(saleprice) from orders
);

--3. 마당서점에서 다음의 심화된 질문에 대해 SQL 문을 작성하시오.

--(1) 박지성이 구매한 도서의 출판사와
--같은 출판사에서 도서를 구매한 고객의 이름
select b.publisher
from customer c, orders o, book b
where c.custid = o.custid 
        and b.bookid = o.bookid
        and c.name = '박지성'
;

select *
from orders o, customer c, book b
where o.custid = c.custid 
        and o.bookid = b.bookid
        and b.publisher in(
            select distinct b.publisher
            from orders o, customer c, book b
            where o.custid = c.custid 
            and o.bookid = b.bookid
            and c.name = '박지성'
)
and c.name != '박지성';

--(2) 두 개 이상의 서로 다른 출판사에서 도서를 구매한 고객의 이름
select c.name
from orders o, customer c, book b
where o.custid = c.custid and b.bookid = o.bookid
group by c.name
having count(distinct publisher) >= 2;