create table project.member(
midx int not null,
memail varchar(50) not null,
mname varchar(50) not null,
mpw varchar(50) not null,
mphone varchar(50) not null,
primary key(midx)
);
desc project.member;

-- ------------------------------
-- 상품 생성
create table project.goods(
gidx int not null,
gname varchar(20) not null,
gprice int not null,
gphoto varchar(45) not null,
gtext text not null,
primary key(gidx)
);
desc table project.goods;
-- drop table goods;
-- select * from goods;
insert into project.goods(gidx,gname,gprice,gphoto,gtext) value(2,'감귤칩', 11000,'감귤칩2.png','감귤칩 과자');
insert into project.goos(gidx,ganme,gprice,gphoto,gtext) value(3, '화투', 


-- -------------------------------
-- 장바구니 생성
create table project.basket(
bidx int not null ,
bcount int not null,
midx int not null,
gidx int not null,
primary key(bidx)
);
desc basket;
alter table project.basket add foreign key(midx) references project.member(midx);
alter table project.basket add foreign key(gidx) references project.goods(gidx);
-- 장바구니 테이블 시퀀스 생성 해야하나??








-- drop table project.basket;
-- desc  project.basket;
-- select * from basket;
-- ----------------------------------
-- 투어 테이블--------------------
create table project.tour(
tidx int not null,
tdate date not null,
tcurrent int not null,
ttotal int not null,
tprice int not null,
primary key(tidx)
);
desc project.tour;

-- --------------------------------------
-- 주소 테이블
create table project.address(
aidx int not null primary key,
address varchar(255) not null,
midx int not null,
foreign key(midx) references member(midx)
);
desc project.address;


-- ----------------------------------------
-- 주문 테이블
create table project.order(
oidx int not null primary key,
odate date not null,
ocategory varchar(20) not null,
tidx int not null,
midx int not null,
aidx int not null,
foreign key(tidx) references tour(tidx),
foreign key(midx) references member(midx),
foreign key(aidx) references address(aidx)
);
desc project.order;

-- -----------------------------------------
-- 결제 테이블
create table project.payment(
pidx int not null primary key,
pprice int not null,
pdate date not null,
pway varchar(10) not null,
pstatus varchar(10) not null,
oidx int not null,
foreign key(oidx) references project.order(oidx)
);
desc project.payment;

-- -------------------------------------------
-- 리뷰 테이블
create table project.review(
ridx int not null primary key,
rtitle varchar(255) not null,
rtext text not null,
rphoto varchar(45) ,
rrate decimal not null,
rdate date not null,
rcategory varchar(20) not null,
rlikes int,
pidx int not null,
foreign key(ridx) references project.payment(pidx)
);
desc project.review;

-- -----------------------------------------------------
-- 댓글 테이블
create table project.comment(
cidx int not null primary key,
cdate date not null,
ctext text not null,
ridx int not null,
midx int not null,
foreign key(ridx) references review(ridx),
foreign key(midx) references member(midx)
);
desc  project.comment;

commit;

