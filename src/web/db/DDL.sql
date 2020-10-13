use scott;

-- table creation
create table test(id int primary key,
name varchar(20) not null);
desc test;

-- tel 컬럼 추가
alter table test add tel varchar(10);

-- tel 컬럼 수정
alter table test modify tel varchar(13);

-- tel 컬럼 삭제
alter table test drop tel;

-- tel 컬럼 rename
alter table test rename column tel to phone;

-- talbe 삭제
drop table test;

-- member
create table member(mid varchar(10),
name varchar(20) not null,
addr varchar(20) not null);

-- orders
create table orders(oid int primary key, -- inline 방식으로 pk 지정 가능
mid varchar(10), -- 회원 아이디.fk(member table의 mid 참조)
odate timestamp default current_timestamp,
payment varchar(13) default 'money wire' not null,
constraint pcheck check(payment in('credit card', 'money wire', 'point')));

-- pk 지정
alter table member
add constraint member_pk primary key(mid); -- member_pk : 제약조건 이름

alter table orders
add constraint oid_pk primary key(oid);

desc orders;

-- fk 지정
alter table orders
add constraint orders_fk foreign key(mid)
references member(mid);

insert into member values('100', 'tommy', 'la');
select * from member;

insert into orders(oid, mid) values(1, '100');

