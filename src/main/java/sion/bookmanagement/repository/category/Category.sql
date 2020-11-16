create table categories(
  category_id int(6) not null auto_increment COMMENT '카테고리 ID',
  category_name varchar(8) not null COMMENT '카테고리 이름',
  primary key(category_id)
); 

alter table `categories` add `created_at` DATETIME COMMENT '등록날짜';
alter table `categories` add `updated_at` DATETIME COMMENT '수정날짜';

DELETE FROM categories WHERE `created_at` is null || `updated_at` is null;

desc categories;
drop table categories;
select * from categories;
