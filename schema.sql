-- first, create database and name it "Bank_P0"

CREATE TABLE branches
(
    BranchID    serial primary key,
    BranchName        VARCHAR(30) not null,
    BranchCity       VARCHAR(30) not null
);

CREATE TABLE users
(
    UserID    serial primary key,
	BranchID	integer,
    FirstName        VARCHAR(30) not null,
    LastName       VARCHAR(30) not null,
	Email			VARCHAR(30) unique not null,
	pass		VARCHAR(80) not null,
	DOB 	date not null,
    role VARCHAR(5) not null,
	constraint fk_branchID
     foreign key (BranchID) 
     REFERENCES branches (BranchID)
);

CREATE TABLE accounts
(
    AccountID    serial primary key,
	UserID 			integer,
    Balance        numeric(6, 2) not null,
    OpenDate		date not null default current_date,
	Status			VARCHAR(10) not null,
	constraint fk_userid
     foreign key (UserID) 
     REFERENCES users (UserID)
	on delete cascade;
);
