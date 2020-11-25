drop table category;
create table category(
  category_id int(6) not null auto_increment COMMENT '카테고리 ID',
  category_name varchar(8) not null COMMENT '카테고리 이름',
  primary key(category_id)
); 

alter table `category` add `created_at` DATETIME COMMENT '등록날짜';
alter table `category` add `updated_at` DATETIME COMMENT '수정날짜';

DELETE FROM category WHERE `created_at` is null || `updated_at` is null;

desc category;
select * from category;
