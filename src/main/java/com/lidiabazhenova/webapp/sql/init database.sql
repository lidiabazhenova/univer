CREATE USER automation_user WITH PASSWORD 'password';
GRANT ALL ON DATABASE automation TO automation_user;

CREATE SEQUENCE order_ids;

CREATE TABLE orders (
  order_id    INTEGER PRIMARY KEY DEFAULT NEXTVAL('order_ids'),
  order_title VARCHAR(52) NOT NULL
);

INSERT INTO orders (order_title) VALUES
  ('канцтовары на oz.by'),
  ('одежда на wildberries.by');

GRANT ALL ON SEQUENCE order_ids TO automation_user;
GRANT ALL ON TABLE orders TO automation_user;

CREATE SEQUENCE product_ids;

CREATE TABLE products (
  order_id   INTEGER REFERENCES orders (order_id),
  product_id INTEGER PRIMARY KEY DEFAULT NEXTVAL('product_ids'),
  url        VARCHAR(132) NOT NULL,
  name       VARCHAR(52)  NOT NULL,
  price      REAL,
  quantity   INTEGER
);

GRANT ALL ON SEQUENCE product_ids TO automation_user;
GRANT ALL ON TABLE products TO automation_user;

INSERT INTO products (order_id, url, name, price, quantity) VALUES
  (1, 'https://oz.by/stationery/more10315502.html', 'кнопки', 0.12, 3),
  (2, 'https://oz.by/pencils/more10137784.html', 'карандаш', 0.50, 5),
  (1, 'https://oz.by/pens/more10354332.html', 'ручка', 0.65, 20),
  (2, 'https://oz.by/stationery/more10254731.html', 'ластик', 0, 0),
  (1, 'https://oz.by/stationery/more10352701.html', 'стержень', 0.02, 100);

CREATE SEQUENCE user_ids;

CREATE TABLE users (
  id         INTEGER PRIMARY KEY DEFAULT NEXTVAL('user_ids'),
  login      VARCHAR(52) NOT NULL,
  first_name VARCHAR(52) NOT NULL,
  last_name  VARCHAR(52) NOT NULL,
  password   VARCHAR(32) NOT NULL
);

GRANT ALL ON SEQUENCE user_ids TO automation_user;
GRANT ALL ON TABLE products TO automation_user;

INSERT INTO users (login, first_name, last_name, password) VALUES
  ('user', 'Lidia', 'Bazhenava', 'password');

CREATE SEQUENCE history_ids;

CREATE TABLE history (
  id          INTEGER PRIMARY KEY DEFAULT NEXTVAL('history_ids'),
  order_id    INTEGER REFERENCES orders (order_id),
  description VARCHAR(252) NOT NULL,
  date        TIMESTAMPTZ         DEFAULT Now()
);

GRANT ALL ON SEQUENCE history_ids TO automation_user;
GRANT ALL ON TABLE history TO automation_user;
