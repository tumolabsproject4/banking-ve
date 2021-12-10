CREATE DATABASE IF NOT EXISTS banking_ve;
-- use banking_ve
CREATE TABLE IF NOT EXISTS user
(
    user_id       BIGINT PRIMARY KEY auto_increment,
    username      VARCHAR(255) NOT NULL UNIQUE ,
    user_password VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS bank
(
    bank_id   BIGINT PRIMARY KEY auto_increment,
    bank_name VARCHAR(255) ,
    address   VARCHAR(255) ,
    image     BLOB
);

CREATE TABLE IF NOT EXISTS employee
(
    employee_id      BIGINT PRIMARY KEY auto_increment,
    first_name       VARCHAR(255) ,
    last_name        VARCHAR(255) ,
    age              INT          ,
    salary           FLOAT        ,
    address          VARCHAR(255) ,
    department       VARCHAR(255) ,
    employees_status BIT          ,
    bank_id          BIGINT       ,
    image            BLOB,
    FOREIGN KEY (bank_id) REFERENCES bank (bank_id)
);






