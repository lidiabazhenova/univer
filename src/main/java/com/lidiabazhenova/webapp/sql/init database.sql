CREATE USER automation_user WITH password 'password';
GRANT ALL ON DATABASE automation TO automation_user;


CREATE SEQUENCE product_ids;
CREATE TABLE products (
  id INTEGER PRIMARY KEY DEFAULT NEXTVAL('product_ids'),
  url VARCHAR(32) NOT NULL,
  name VARCHAR(32) NOT NULL
);
GRANT ALL ON SEQUENCE product_ids TO automation_user;
GRANT ALL ON TABLE products TO automation_user;