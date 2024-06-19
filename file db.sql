create database quanlynhanvien;
use quanlynhanvien;

create table Department (
	department_id varchar(5) primary key,
	department_name varchar(50) not null unique,
	description text,
	is_deleted bit default 0
);

create table Employee (
	employee_id int primary key auto_increment,
	first_name varchar(20) not null,
	last_name varchar(20) not null,
	date_of_birth date not null,
    phone varchar(15),
    address varchar(255),
    salary double not null check (salary > 0),
    created_at Datetime,
    update_at Datetime,
    is_deleted bit default 0,
    department_id varchar(5) not null,
    foreign key (department_id) references Department(department_id)
);