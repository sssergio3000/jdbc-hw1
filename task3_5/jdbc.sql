CREATE DATABASE MyJoinsDB1;
drop DATABASE myjoinsdb1;
use MyJoinsDB1;

CREATE TABLE people (
id int AUTO_INCREMENT PRIMARY KEY,
name varchar(20),
ph_num varchar(15)
);

CREATE TABLE off_info (
pers_id int not null,
salary INT not null,
position_name varchar(20),
FOREIGN KEY (pers_id) REFERENCES people (id)
);

CREATE TABLE pers_info (
pers_id int not null,
fam_status varchar(20) not NULL,
birth_date DATE not null DEFAULT '2000-01-01',
address varchar(30) not null,
FOREIGN KEY (pers_id) REFERENCES people (id)
);

drop table pers_info;

INSERT into people

(name, ph_num)
VALUES
('jack', '0987654321'),
('john','0983456543'),
('tom', '0509876543'),
('marta', '0679876541');

select * from people;

INSERT into off_info
VALUES
(1, 20000, 'director'),
(2, 15000, 'manager'),
(3, 10000, 'worker'),
(4, 12000, 'manager');

TRUNCATE off_info;

select * from off_info;

INSERT into pers_info
VALUES
(1, 'married', '2000-12-14', 'london, 12 beverly str'),
(2, 'divorced', '1980-05-20', 'paris, 15 aufgabe str'),
(3, 'single', '1995-10-17', 'frankfurt, 13 trink str'),
(4, 'married', '1993-12-12', 'praha, 22 fish str');

EXPLAIN people;

select name, ph_num, address from people
join pers_info on id = pers_id;

select name, pers_info.birth_date, ph_num from people
join pers_info on id = pers_id
where fam_status in ('single', 'divorced');

select name, pers_info.birth_date, ph_num from people
join off_info on id = pers_id
join pers_info on pers_info.pers_id = off_info.pers_id
where position_name = 'manager';




