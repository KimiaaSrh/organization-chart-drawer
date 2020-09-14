create table organization(
	org_name varchar(30) ,
	org_id varchar(20) PRIMARY KEY
	
);

create table department (
	dept_name varchar (30),
	dept_id varchar (20) PRIMARY KEY,
	org_id varchar (20),
	dept_parentId varchar (20),
	FOREIGN KEY (org_id) REFERENCES organization (org_id) ,
	FOREIGN KEY (dept_parentId) REFERENCES department (dept_id) 
);

create table roles(
	role_id varchar(20) PRIMARY KEY ,
	role_name varchar(30) ,
	dept_id varchar (20),
	FOREIGN KEY (dept_id) REFERENCES department (dept_id) ON UPDATE CASCADE
);


create table staff(
	st_personalCode varchar(20) PRIMARY KEY ,
	st_employeeDate date,
	st_name varchar(30),
	role_id varchar(20),
    FOREIGN KEY (role_id) REFERENCES roles (role_id) ON UPDATE CASCADE

);


create table task (
	task_id varchar(20) PRIMARY KEY ,
	task_name varchar(30),
	st_personalCode varchar(20),
	FOREIGN KEY (st_personalCode) REFERENCES staff (st_personalCode) ON UPDATE CASCADE
);
