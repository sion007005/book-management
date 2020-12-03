CREATE DATABASE book_management CHARACTER SET utf8mb4;
create user sion@'%' identified by '1234';
grant all privileges on book_management.* to sion@'%' ;