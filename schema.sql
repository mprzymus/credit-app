CREATE SCHEMA CustomerDb;
CREATE SCHEMA CreditDb;
CREATE SCHEMA ProductDb;

CREATE TABLE CustomerDb.Customer
(
    CreditID  SERIAL PRIMARY KEY,
    FirstName VARCHAR(30),
    Pesel     CHAR(11),
    Surname   VARCHAR(30)
);

CREATE TABLE CreditDb.Credit
(
    ID         SERIAL PRIMARY KEY,
    CreditName TEXT
);

CREATE TABLE ProductDb.Product
(
    ID          SERIAL PRIMARY KEY,
    CreditId    INT,
    CONSTRAINT FkCredit FOREIGN KEY (CreditId) REFERENCES CreditDb.Credit,
    ProductName TEXT,
    Value       INT
);