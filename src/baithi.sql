create database if not exists cinema;
use cinema;
drop table if exists movie;
create table movie (
   id int PRIMARY KEY,
   name varchar(50) ,
   premiere varchar(50),
   director varchar(50),
   times int
   );
insert into movie values(1,'lightyear','2022-1-1','Angus MacLane',90);
insert into movie values(2,'blackphone','2022-2-4','Scott Derrickson',120);

select  * from movie;