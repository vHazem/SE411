CREATE TABLE course
(
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    code varchar(100) NOT NULL,
    name varchar(30),
    description varchar(500),
    credits int
);
