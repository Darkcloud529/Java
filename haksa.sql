create table student(
    id char(7) primary key,
    name varchar2(10) not null,
    dept varchar2(20) not null
);

insert into student values('C202001','�̼���','��ǻ�Ͱ��а�');

commit;
select * from student;

create table books(
  no char(6) primary key, -- å��ȣ
  title varchar(50) not null, -- å�̸�
  author varchar(50) not null -- ����
);

insert into books values('000001','����Ŭ�⺻','��Ȳ');
insert into books values('000002','�ڹ�����','����');
insert into books values('000003','HTML5','������');

create table bookRent
( no char(10) primary key, -- �뿩��ȣ
  id char(7) not null, -- �й�
  bookNo char(6) not null, -- å��ȣ
  rDate char(8) not null ,-- �뿩��
  constraint fk_bookrent foreign key(id)
  references student2(id)
);

insert into bookRent values('2017071304','C202002','000001','20170713');
insert into bookRent values('2017071305','M202001','000002','20170713');
insert into bookRent values('2017071306','M202001','000003','20170713');

commit;