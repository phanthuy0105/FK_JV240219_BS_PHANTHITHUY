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
                          created_at Datetime default current_timestamp,
                          update_at Datetime null,
                          is_deleted bit default 0,
                          department_id varchar(5) not null,
                          foreign key (department_id) references Department(department_id)
);

insert into Department(department_id, department_name, description, is_deleted)
    VALUE ('P001','Phòng nhân sự','Phụ trách quản lý hợp đồng lao động của nhân viên',0),
    ('P002','Phòng hành chính','Tiếp nhận và xử lý các công việc nội bộ trong doanh nghiệp',0),
    ('P003','Phòng Marketing','Xây dựng hình ảnh và phát triển thương hiệu cho doanh nghiệp',1),
    ('P004','Phòng kế toán','Thực hiện công việc về nghiệp vụ chuyên môn tài chính kế toán theo quy định của Nhà nước',0),
    ('P005','Phòng công nghệ thông tin',' Quản lý hạ tầng công nghệ thông tin, bao gồm hệ thống mạng, máy chủ, thiết bị lưu trữ và phần mềm hệ thống',0);
select * from Department;

insert into Employee(first_name, last_name, date_of_birth, phone, address, salary, created_at, update_at, is_deleted, department_id)
    VALUE   ('Văn Bình','Nguyễn','1999-10-10','0987894567', 'Hà Nội', 5000000, now(), null, 0, 'P001'),
    ('Hoàng Cường','Trần','1997-08-16','0976849898', 'Hà Nam', 4000000, now(), null, 0, 'P005'),
    ('Gia An','Phí','1997-08-9','0947892233', 'Nam Định', 3000000, now(), null, 0, 'P003'),
    ('Đình Đại','Bùi','2000-06-20','0937694668', 'Hải Phòng', 6000000, now(), null, 1, 'P002'),
    ('Mai Anh','Lê','1995-07-28','0862849965', 'Hà Tĩnh', 4500000, now(), null, 0, 'P004'),
    ('Hữu Hoàng','Đặng','1996-05-22','0884678877', 'TP.HCM', 5000000, now(), null, 1, 'P005');
select * from Employee;

DELIMITER &&
create procedure Find_all_department()
BEGIN
    select * from Department;
end &&
DELIMITER &&;

DELIMITER &&
create procedure Find_department_by_id(
    department_id_in int
)
BEGIN
    select * from Department where department_id = department_id_in;
end &&
DELIMITER &&

DELIMITER &&
create procedure is_exist_department (
    department_id_in int,
    out is_exist bit
)
begin
    declare cnt_department int;
    set cnt_department = (select count(*) from Department where department_id = department_id_in);
    if cnt_department = 0 then
        set is_exist = 0;-- Mã loại phòng ban không tồn tại
    else
        set is_exist = 1;-- Mã loại phòng ban đã tồn tại
    end if;
end &&
DELIMITER &&;

DELIMITER &&
create procedure Create_department (
    department_id_in varchar(5),
    department_name_in varchar(100),
    description_in text,
    is_deleted_in bit
)
begin
    insert into Department(department_id, department_name, description, is_deleted)
    values (department_id_in, department_name_in, description_in, is_deleted_in);
end &&
DELIMITER &&;

DELIMITER &&
create procedure Update_department (
    department_id_in int,
    department_name_in varchar(100),
    description_in text,
    is_deleted_in bit
)
begin
    update Department
    set department_id = department_id_in,
        department_name = department_name_in,
        description = description_in,
        is_deleted = is_deleted_in
    where department_id = department_id_in;
end &&
DELIMITER &&;

DELIMITER &&
create procedure Delete_department(department_id_in varchar(5))
begin
    delete from Department where department_id like department_id_in;
end &&
DELIMITER &&;

DELIMITER &&
create procedure Find_all_employee()
BEGIN
    select * from Employee;
end &&
DELIMITER &&;

DELIMITER &&
create procedure Find_employee_by_id(
    employee_id_in int
)
BEGIN
    select * from Employee where employee_id = employee_id_in;
end &&
DELIMITER &&

DELIMITER &&
create procedure is_exist_employee (
    employee_id_in int,
    out is_exist bit
)
begin
    declare cnt_employee int;
    set cnt_employee = (select count(*) from Employee where employee_id = employee_id_in);
    if cnt_employee = 0 then
        set is_exist = 0;-- Mã nhân viên không tồn tại
    else
        set is_exist = 1;-- Mã nhân viênn đã tồn tại
    end if;
end &&
DELIMITER &&;

DELIMITER &&
create procedure Create_employee (
    first_name_in varchar(20),
    last_name_in varchar(20),
    date_of_birth_in date,
    phone_in varchar(15),
    address_in varchar(255),
    salary_in double,
    created_at_in Datetime,
    update_at_in Datetime,
    is_deleted_in bit,
    department_id_in varchar(5)
)
begin
    insert into Employee(first_name, last_name, date_of_birth, phone, address, salary, created_at, update_at, is_deleted, department_id)
    values (first_name_in, last_name_in, date_of_birth_in, phone_in, address_in, salary_in, created_at_in, update_at_in, is_deleted_in, department_id_in);
end &&
DELIMITER &&;

DELIMITER &&
create procedure Update_employee (
    employee_id_in int,
    first_name_in varchar(20),
    last_name_in varchar(20),
    date_of_birth_in date,
    phone_in varchar(15),
    address_in varchar(255),
    salary_in double,
    created_at_in Datetime,
    update_at_in Datetime,
    is_deleted_in bit,
    department_id_in varchar(5)
)
begin
    update Employee
    set first_name = first_name_in,
        last_name = last_name_in,
        date_of_birth = date_of_birth_in,
        phone = phone_in,
        address = address_in,
        salary = salary_in,
        created_at = created_at_in,
        update_at = update_at_in,
        is_deleted = is_deleted_in,
        department_id = department_id_in
    where employee_id = employee_id_in;
end &&
DELIMITER &&;

DELIMITER &&
create procedure Delete_employee(employee_id_in varchar(5))
begin
    delete from Employee where employee_id = employee_id_in;
end &&
DELIMITER &&;
