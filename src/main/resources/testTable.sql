-- Create table
/*DROP TABLE USER_ACCOUNT;
create table USER_ACCOUNT
(



  USER_NAME VARCHAR(255) ,
  PASSWORD  VARCHAR(255) ,
  SECOND_NAME VARCHAR(255) ,
  FIRST_NAME VARCHAR(255) ,
  MIDDLE_NAME VARCHAR(255),
  primary key (USER_NAME)
);
*/

-- Insert data: ---------------------------------------------------------------

/*insert into USER_ACCOUNT (USER_NAME, PASSWORD, SECOND_NAME, FIRST_NAME, MIDDLE_NAME)
values ('tommyK', 'tom001', 'Beggins', 'Tom', 'Frodovich');

insert into USER_ACCOUNT (USER_NAME, PASSWORD, SECOND_NAME, FIRST_NAME, MIDDLE_NAME)
values ('tommyY', 'tom0011', 'Beggins1', 'Tom1', 'Frodovich1');*/

/*create into USER_ACCOUNT (USER_NAME, GENDER, PASSWORD, SECOND_NAME, FIRST_NAME, MIDDLE_NAME)
values ('jerryMe', 'M', 'jerry001', 'Вильчинский', 'Богдан', 'Сергеевич');
*/

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
  norm_calories LONG,
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
  user_id INT NOT NULL ,
  portions_id INT NOT NULL,
  amount LONG NOT NULL ,
  PRIMARY KEY (user_id,portions_id),
  FOREIGN KEY (user_id) REFERENCES user(id),
  FOREIGN KEY (portions_id) REFERENCES portions(id)
);

CREATE TABLE user_products
(
  user_id INT NOT NULL ,
  products_id INT NOT NULL,
  amount LONG NOT NULL ,
  PRIMARY KEY (user_id,products_id),
  FOREIGN KEY (user_id) REFERENCES user(id),
  FOREIGN KEY (products_id) REFERENCES products(id)
);

CREATE TABLE portions_products
(
  portions_id INT NOT NULL ,
  products_id INT NOT NULL,
  amount LONG NOT NULL ,
  PRIMARY KEY (portions_id,products_id),
  FOREIGN KEY (portions_id) REFERENCES portions(id),
  FOREIGN KEY (products_id) REFERENCES products(id)
);

CREATE TABLE statistics
(
  id INT NOT NULL AUTO_INCREMENT,
  food_name VARCHAR(45) NOT NULL ,
  fats FLOAT NOT NULL ,
  proteins FLOAT NOT NULL ,
  carbohydrates FLOAT NOT NULL ,
  calories FLOAT NOT NULL ,
  amount INT NOT NULL ,
  date DATE NOT NULL ,
  exceeded_norm LONG,
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

INSERT INTO portions(name,calories) VALUES ('Яичница',1000);
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

INSERT INTO portions_products(portions_id, products_id, amount) VALUES (1, 2, 3);
INSERT INTO portions_products(portions_id, products_id, amount) VALUES (1, 3, 1);

INSERT INTO statistics(food_name, fats, proteins, carbohydrates,calories, amount, date, user_id)
VALUES ('Петрушка', 200,330,420,2100, 1, '2018-01-15', 2);

INSERT INTO user(login, password, second_name, first_name, middle_name, gender, birth_date, life_activity, height, weight, norm_calories)
VALUES ('Valera',555,'Voloshenko', 'Valeria', 'Vitalievna', 'W', '1998-03-04', 1.4, 165, 58, 2800);

INSERT INTO user(login, password, second_name, first_name, middle_name, gender, birth_date, life_activity, height, weight, norm_calories)
VALUES ('qcuk',555,'Vilchynskyi', 'Bogdan', 'Sergeevich', 'W', '1998-03-04', 1.4, 165, 58, 3200);



INSERT INTO user_portions(user_id, portions_id, amount) VALUES (1,1,2);

/*INSERT INTO statistics(user_id, food_name, fats, proteins, carbohydrates, calories, amount, date
*/


    SELECT user.id, p2.name ,p2.fats, p2.proteins, p2.carbohydrates, p2.calories, u.amount, '1998-03-04'
FROM user
JOIN user_portions u ON user.id = u.user_id
JOIN portions p ON u.portions_id = p.id
JOIN portions_products product ON p.id = product.portions_id
JOIN products p2 ON product.products_id = p2.id;


/*INSERT INTO user_portions(user_id, portions_id, amount)
    SELECT user.id, products.id*/


/*  INSERT INTO food(name, fats, proteins, carbohydrates, calories)
  SELECT user.login, s.fats, s.proteins, s.carbohydrates, s.calories
  FROM user
   JOIN user_has_statistics u ON user.id = u.user_id
   JOIN statistics s ON u.statistics_id = s.id;
*/

