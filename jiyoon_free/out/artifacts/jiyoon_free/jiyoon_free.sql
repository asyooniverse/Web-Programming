CREATE DATABASE jspdb default CHARACTER SET UTF8;

create user 'jspbook'@'%' identified by 'passwd';
drop user 'jspbook'@'%';

CREATE TABLE user
(
    id       VARCHAR(30) NOT NULL primary key,
    password VARCHAR(30) NOT NULL
);

CREATE TABLE article
(
    article_id   INT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title        VARCHAR(10) NOT NULL,
    user_id      VARCHAR(30) NOT NULL,
    created_at datetime     default now()
);

CREATE TABLE todo
(
    is_checked boolean,
    content      VARCHAR(30) NOT NULL,
    article_id   INT
);

select max(article_id) as article_id from article;

alter table todo modify content varchar(63), algorithm=inplace, lock = none;

insert into todo values(true, "밥먹기", 1);
insert into todo values(false, "밥먹기", 1);
insert into todo values(true, "밥먹기", 1);
insert into todo values(true, "밥먹기", 1);
insert into todo values(false, "밥먹기", 2);
insert into todo values(true, "밥먹기", 2);
insert into todo values(false, "밥먹기", 3);
insert into todo values(true, "밥먹기", 3);
insert into todo values(true, "밥먹기", 3);

select * from todo;
select * from article;

INSERT INTO article VALUES (1, '나의 투두',  '1', now());
INSERT INTO article VALUES (2, '나의 투두', '2', now());
INSERT INTO article VALUES (3, '나의 투두',  '3', now());
INSERT INTO article VALUES (4, '나의 투두', '11', now());
INSERT INTO article VALUES (5, '나의 투두', '11', now());
INSERT INTO article VALUES (6, '나의 투두', '11', now());
INSERT INTO article VALUES (7, '나의 투두', '11', now());
INSERT INTO article VALUES (8, '나의 투두리스트', '1', now());

INSERT INTO article VALUES (9,'나의 투두리스트', '1', now());
INSERT INTO article VALUES (10,'나의 투두리스트', '1', now());
INSERT INTO article VALUES (11,'나의 투두리스트', '1', now());
INSERT INTO article VALUES (12,'나의 투두리스트', '1', now());
INSERT INTO article VALUES (13,'나의 투두리스트', '1', now());
INSERT INTO article VALUES (14,'나의 투두리스트', '1', now());
INSERT INTO article VALUES (15,'나의 투두리스트', '1', now());
INSERT INTO article VALUES (16,'나의 투두리스트', '1', now());
INSERT INTO article VALUES (17,'나의 투두리스트', '2', now());
INSERT INTO article VALUES (18,'나의 투두리스트', '3', now());
INSERT INTO article VALUES (19,'나의 투두리스트', '4', now());
INSERT INTO article VALUES (21,'나의 투두리스트', '5', now());
INSERT INTO article VALUES (25,'나의 투두리스트', '6', now());
INSERT INTO article VALUES (23,'나의 투두리스트', '7', now());
INSERT INTO article VALUES (24,'나의 투두리스트', '8', now());

truncate table user;
truncate table todo;
truncate table article;