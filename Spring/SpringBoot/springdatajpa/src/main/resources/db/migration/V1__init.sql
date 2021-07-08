create sequence hibernate_sequence start with 1 increment by 1;
create table account (id int8 not null, password varchar(255), user_name varchar(255), primary key (id));