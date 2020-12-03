create table member(
  member_id int(11) not null auto_increment COMMENT '회원 ID',
  name varchar(20) not null COMMENT '이름',
  gender varchar(1) not null COMMENT '성별',
  email varchar(40) not null COMMENT '이메일',
  age int(3) not null COMMENT '나이',
  phone varchar(12) not null COMMENT '휴대폰 번호', 
  primary key(member_id)
);

desc member;
select * from member;

alter table `member` add `created_at` DATETIME COMMENT '등록날짜';
alter table `member` add `updated_at` DATETIME COMMENT '수정날짜';
alter table `member` add `password` int COMMENT '비밀번호';

update member set password = '1234' where password is null;
alter table member modify password varchar(20) not null;
alter table member modify password varchar(100) not null;

alter table `member` add `salt` varchar(100);

DELETE FROM member WHERE `created_at` is null || `updated_at` is null;

INSERT INTO member(name, gender, email, age, phone, created_at, updated_at, password, salt)(SELECT name, gender, email, age, phone, created_at, updated_at, password, salt FROM member);



