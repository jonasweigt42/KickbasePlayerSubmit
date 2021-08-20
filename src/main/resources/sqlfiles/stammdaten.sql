DROP TABLE IF EXISTS USER;
 
CREATE TABLE USER (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  mail_address VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL
);
 
INSERT INTO USER (first_name, last_name, mail_address, password) VALUES
  ('Jonas', 'Weigt', 'jonas@skatrunde.de','jonas'),
  ('Marcel', 'Hosenthien', 'hose@skatrunde.de', 'hose'),
  ('Franz', 'Deelmann', 'franz@skatrunde.de', 'franz');
  
