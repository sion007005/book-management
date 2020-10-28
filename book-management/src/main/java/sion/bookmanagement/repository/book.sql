create table books(
  book_id int(11) not null auto_increment COMMENT '책 ID',
  category_id int(6) not null COMMENT '카테고리 ID',
  title varchar(100) not null COMMENT '제목',
  author varchar(15) not null COMMENT '저자',
  stock int(10) not null COMMENT '재고',
  year int(4) not null COMMENT '출판 년도', 
  price int(8) not null COMMENT '가격',
  created DATETIME COMMENT '등록날짜',
  primary key(book_id)
); 

desc books;
drop table books;
select * from books;

select count(*) from books where book_id=2;
