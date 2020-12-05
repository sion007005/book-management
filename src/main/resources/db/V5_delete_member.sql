truncate member;

alter table member modify phone varchar(20) not null;

ALTER TABLE `book_management`.`member` 
ADD INDEX `idx_name` (`name` ASC);

ALTER TABLE `book_management`.`member` 
ADD UNIQUE INDEX `idx_email` (`email` ASC);



