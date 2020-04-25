DROP TABLE IF EXISTS USER;
DROP TABLE IF EXISTS TRAVEL_OPPORTUNITY;
 
CREATE TABLE USER (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  user_name VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  is_Fahrer BOOLEAN DEFAULT false
);
 
INSERT INTO USER (first_name, last_name, user_name, password) VALUES
  ('Jonas', 'Weigt', 'jonas','jonas'),
  ('Eva', 'Weigt', 'eva', 'eva'),
  ('Admin', 'Admin', 'admin', 'admin');
  
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
 