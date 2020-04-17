DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS travelopportunity;
 
CREATE TABLE user (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  user_name VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL
);
 
INSERT INTO user (first_name, last_name, user_name, password) VALUES
  ('Jonas', 'Weigt', 'jonas','jonas'),
  ('Eva', 'Weigt', 'eva', 'eva'),
  ('Admin', 'Admin', 'admin', 'admin');
  
 CREATE TABLE travelopportunity (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL
 );
 
 INSERT INTO travelopportunity (name) VALUES
 ('Mitfahrgelegenheit'),
 ('E-Car Sharing'),
 ('E-Bike'), 
 ('E-Scooter'),
 ('Bus'), 
 ('autonomer Kleinbus');
 