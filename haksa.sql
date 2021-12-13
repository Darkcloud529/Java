create table student(
    id char(7) primary key,
    name varchar2(10) not null,
    dept varchar2(20) not null
);

insert into student values('C202001','이순신','컴퓨터공학과');

commit;
select * from student;

create table books(
  no char(6) primary key, -- 책번호
  title varchar(50) not null, -- 책이름
  author varchar(50) not null -- 저자
);

insert into books values('000001','오라클기본','이황');
insert into books values('000002','자바정복','율곡');
insert into books values('000003','HTML5','강감찬');

create table bookRent
( no char(10) primary key, -- 대여번호
  id char(7) not null, -- 학번
  bookNo char(6) not null, -- 책번호
  rDate char(8) not null ,-- 대여일
  constraint fk_bookrent foreign key(id)
  references student2(id)
);

insert into bookRent values('2017071304','C202002','000001','20170713');
insert into bookRent values('2017071305','M202001','000002','20170713');
insert into bookRent values('2017071306','M202001','000003','20170713');

commit;