create table survey (
	idx int not null auto_increment, /* 설문지 고유번호 */
	start_date datetime not null, /* 설문 시작 날짜 */
	end_date datetime not null, /* 설문 마감 날짜 */
	subject varchar(60) not null, /* 설문 주제 */
	target varchar(20) not null, /* 설문 대상 */
	is_closed tinyint not null DEFAULT 0, /* 설문 마감 여부 0 : 진행중, 1 : 마감됨 */
	primary key (idx)
);

create table question (
	idx int not null auto_increment, 
    survey_idx int not null,
    content varchar(100),
    primary key (idx)
);
 
create table answer (
	idx int not null auto_increment,
    member_idx int not null,
    survey_idx int not null,
    question_idx int not null,
    checked_number int not null,
	primary key (idx)
); 