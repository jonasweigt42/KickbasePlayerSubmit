DROP TABLE IF EXISTS USER;
DROP TABLE IF EXISTS TRAVEL_OPPORTUNITY;
 
CREATE TABLE USER (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  user_name VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  is_Fahrer BOOLEAN,
  is_Mitfahrer BOOLEAN
);
 
INSERT INTO USER (first_name, last_name, user_name, password, is_Fahrer, is_Mitfahrer) VALUES
  ('Jonas', 'Weigt', 'jonas','jonas', false, false),
  ('Eva', 'Weigt', 'eva', 'eva', false, false),
  ('Admin', 'Admin', 'admin', 'admin', false, false);
  
 CREATE TABLE TRAVEL_OPPORTUNITY (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL
 );
 
 INSERT INTO TRAVEL_OPPORTUNITY (name) VALUES
 ('Mitfahrgelegenheit'),
 ('eCarsharing'),
 ('eBike'), 
 ('eScooter'),
 ('Bus'), 
 ('autonomer Kleinbus');
 