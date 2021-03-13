CREATE SCHEMA CustomerDb;
CREATE SCHEMA CreditDb;
CREATE SCHEMA ProductDb;


CREATE TABLE CreditDb.Credit(
                                ID SERIAL PRIMARY KEY,
                                CreditName TEXT
);

CREATE TABLE CustomerDb.Customer(
                                    ID SERIAL PRIMARY KEY,
                                    CreditId INT,
                                    FirstName VARCHAR(30),
                                    Pesel CHAR(11),
                                    Surname VARCHAR(30),
                                    CONSTRAINT fk_credit FOREIGN KEY(CreditId) REFERENCES CreditDb.Credit
);

CREATE TABLE ProductDb.Product(
                                  ID SERIAL PRIMARY KEY,
                                  CreditId INT,
                                  CONSTRAINT fk_credit FOREIGN KEY(CreditId) REFERENCES CreditDb.Credit,
                                  ProductName TEXT,
                                  Value INT
);