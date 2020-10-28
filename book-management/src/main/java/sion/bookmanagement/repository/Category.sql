create table categories(
  category_id int(6) not null auto_increment COMMENT '카테고리 ID',
  category_name varchar(8) not null COMMENT '카테고리 이름',
  primary key(category_id)
); 

desc categories;
drop table categories;
select * from categories;
