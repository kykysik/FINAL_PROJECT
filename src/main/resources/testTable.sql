
ALTER DATABASE testDB CHARACTER   SET utf8 COLLATE utf8_general_ci;


CREATE TABLE user
(
  id INT NOT NULL AUTO_INCREMENT,
  login VARCHAR(45) NOT NULL ,
  password VARCHAR(45) NOT NULL ,
  second_name VARCHAR(45) NOT NULL ,
  first_name VARCHAR(45) NOT NULL ,
  middle_name VARCHAR(45) NOT NULL ,
  gender VARCHAR(1) NOT NULL ,
  birth_date DATE  ,
  life_activity FLOAT ,
  height FLOAT  ,
  weight FLOAT ,
  norm_calories LONG, /*может инт? + добавить почту*/
  PRIMARY KEY (id)
);

CREATE TABLE products
(
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL ,
  fats FLOAT NOT NULL ,
  proteins FLOAT NOT NULL ,
  carbohydrates FLOAT NOT NULL ,
  calories FLOAT NOT NULL ,
  PRIMARY KEY (id)
);


CREATE TABLE portions
(
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  calories FLOAT NOT NULL ,
  PRIMARY KEY (id)

);

  CREATE TABLE user_portions
(
  id INT NOT NULL AUTO_INCREMENT,
  user_id INT NOT NULL ,
  portions_id INT NOT NULL,
  date DATE NOT NULL ,
  amount LONG NOT NULL ,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES user(id),
  FOREIGN KEY (portions_id) REFERENCES portions(id)
);

CREATE TABLE user_products
(
  id INT NOT NULL  AUTO_INCREMENT,
  user_id INT NOT NULL ,
  products_id INT NOT NULL,
  date DATE NOT NULL ,
  amount LONG NOT NULL ,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES user(id),
  FOREIGN KEY (products_id) REFERENCES products(id)

);

CREATE TABLE portions_products
(
  portions_id INT NOT NULL ,
  products_id INT NOT NULL,
  amount LONG NOT NULL ,
  PRIMARY KEY (portions_id,products_id),
  FOREIGN KEY (portions_id) REFERENCES portions(id)
    ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (products_id) REFERENCES products(id)
);

CREATE TABLE statistics
(
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL ,
  fats FLOAT NOT NULL ,
  proteins FLOAT NOT NULL ,
  carbohydrates FLOAT NOT NULL ,
  calories FLOAT NOT NULL ,
  amount INT NOT NULL ,
  date DATE NOT NULL ,
  user_id INT,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES user(id)
  ON DELETE NO ACTION
);

INSERT INTO products(name, fats, proteins, carbohydrates, calories)
VALUES('Морковь', 20, 30, 40, 100);

INSERT INTO products(name, fats, proteins, carbohydrates, calories)
VALUES('Ойойойой', 200, 330, 420, 350);

INSERT INTO products(name, fats, proteins, carbohydrates, calories)
VALUES('Петрушка', 10, 30, 0, 30);

INSERT INTO products(name, fats, proteins, carbohydrates, calories)
VALUES('s', 20, 30, 40, 100);

INSERT INTO products(name, fats, proteins, carbohydrates, calories)
VALUES('f', 200, 330, 420, 350);

INSERT INTO products(name, fats, proteins, carbohydrates, calories)
VALUES('w', 10, 30, 0, 30);

INSERT INTO portions(name,calories) VALUES ('Яичница',1000);
INSERT INTO user_products(user_id,products_id, date, amount) VALUES (43,17,'2018-09-04', 7);


INSERT INTO portions(name,calories) VALUES ('Суп',1000);
INSERT INTO portions(name,calories) VALUES ('Плов',1000);
INSERT INTO portions(name,calories) VALUES ('Голубцы',1000);
INSERT INTO portions(name,calories) VALUES ('Зеленый Борщ',1000);
INSERT INTO portions(name,calories) VALUES ('Красный Борщ',1000);
INSERT INTO portions(name,calories) VALUES ('Картошка с жареной рыбой',1000);
INSERT INTO portions(name,calories) VALUES ('Филе с картошкой',500);
INSERT INTO portions(name,calories) VALUES ('Отбивная',500);
INSERT INTO portions(name,calories) VALUES ('Картошка с котлетой',500);
INSERT INTO portions(name,calories) VALUES ('Борщ',500);
INSERT INTO portions(name,calories) VALUES ('Ананасовый пирог',100);

INSERT INTO portions_products(portions_id, products_id, amount) VALUES (25, 4, 3);
INSERT INTO portions_products(portions_id, products_id, amount) VALUES (25, 5, 1);
INSERT INTO portions_products(portions_id, products_id, amount) VALUES (25, 6, 1);


INSERT INTO statistics(name, fats, proteins, carbohydrates,calories, amount, date, user_id)
VALUES ('OX', 200,330,420,2100, 1, '2018-01-15', 2);

