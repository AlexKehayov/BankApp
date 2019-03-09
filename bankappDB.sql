drop database if exists bankapp;
create database bankapp;
use bankapp;

create table access(
id int auto_increment primary key,
name varchar(25) not null unique,
password varchar(25) not null);

create table accounts(
id int auto_increment primary key,
name varchar(100) not null,
balance decimal(10,2) not null);

create table trhistory(
sender varchar(100) not null,
receiver varchar(100) not null,
value decimal(10,2) not null,
dt timestamp default current_timestamp);

insert into access (name, password) values ('admin', 'admin');
insert into accounts (name, balance) values
 ('Albert A', 10000.00),
 ('George B', 50000.00);