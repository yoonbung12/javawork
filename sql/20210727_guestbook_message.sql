create table project.guestbook_message (
	messageid int not null auto_increment primary key,
    guestname varchar(20) not null,
    password varchar(20) not null,
    message text not null,
    regdate timestamp default current_timestamp
);

-- select
select * from project.guestbook_message order by regdate desc;
-- limit index, count : 페이징 처리 
select * from project.guestbook_message order by regdate desc limit 0, 3;

-- insert
insert into project.guestbook_message (guestname, password, message)
values ('king', '1111', 'Hello');

-- update
update project.guestbook_message
set guestname='scott', password='0000', message='안녕하세요'
where messageid=1;

-- delete
delete from project.guestbook_message where messageid=1;