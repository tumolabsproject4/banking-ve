CREATE DATABASE IF NOT EXISTS banking_ve;
-- use banking_ve
CREATE TABLE IF NOT EXISTS user (user_id BIGINT PRIMARY KEY auto_increment, username VARCHAR(255) NOT NULL UNIQUE
    ,user_password VARCHAR(255)NOT NULL UNIQUE);

CREATE TABLE IF  NOT EXISTS bank(bank_id BIGINT PRIMARY KEY auto_increment,bank_name VARCHAR(255) NOT NULL UNIQUE ,
                                 address VARCHAR(255) NOT NULL UNIQUE);

CREATE TABLE IF NOT EXISTS employee(
    employee_id BIGINT PRIMARY KEY auto_increment,first_name VARCHAR(255) NOT NULL UNIQUE,
    last_name VARCHAR(255) NOT NULL UNIQUE, age INT NOT NULL UNIQUE, salary FLOAT NOT NULL UNIQUE,
    address VARCHAR(255) NOT NULL UNIQUE,department VARCHAR(255) NOT NULL UNIQUE,
    employees_status BIT NOT NULL UNIQUE,
    bank_id BIGINT NOT NULL UNIQUE,
    FOREIGN KEY (bank_id) REFERENCES bank(bank_id));






