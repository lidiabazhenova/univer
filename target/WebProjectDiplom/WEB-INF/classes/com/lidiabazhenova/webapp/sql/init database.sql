CREATE USER automation_user WITH PASSWORD 'password';
GRANT ALL ON DATABASE automation TO automation_user;


CREATE SEQUENCE product_ids;

CREATE TABLE products (
  id   INTEGER PRIMARY KEY DEFAULT NEXTVAL('product_ids'),
  url  VARCHAR(132) NOT NULL,
  name VARCHAR(52) NOT NULL,
  category VARCHAR(20) NOT NULL
);
GRANT ALL ON SEQUENCE product_ids TO automation_user;
GRANT ALL ON TABLE products TO automation_user;

INSERT INTO products (url, name, category) VALUES
  ('https://oz.by/stationery/more10315502.html?sbtoken=9be46ff5c4f4eaf5374409dd55df5112', 'кнопки', 'КАНЦТОВАРЫ'),
  ('https://oz.by/pencils/more10137784.html?sbtoken=36d7cc45b5fa5da597bf5cbf09fecf2c', 'карандаш', 'КАНЦТОВАРЫ'),
  ('https://oz.by/pens/more10354332.html?sbtoken=eeb05f9e7b2000e5e21c1d09f42a4f58', 'ручка', 'КАНЦТОВАРЫ'),
  ('https://oz.by/stationery/more10254731.html?sbtoken=cd9ab238728f6c4773ec8f38f2d62d5f', 'ластик', 'КАНЦТОВАРЫ'),
  ('https://oz.by/stationery/more10352701.html?sbtoken=7c11f44037a1590f753b5d8d585d4f3b', 'стержень', 'КАНЦТОВАРЫ');
