DROP TABLE IF EXISTS USER;
 
CREATE TABLE USER (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  mail_address VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL
);

CREATE TABLE PLAYER_SUBMIT (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  player_name VARCHAR(250) NOT NULL,
  user_name VARCHAR(250) NOT NULL,
  spieltag VARCHAR(250) NOT NULL,
  saison VARCHAR(250) NOT NULL
);

CREATE TABLE SPIELTAG (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);

INSERT INTO SPIELTAG (name) VALUES
('Spieltag 1'),
('Spieltag 2'),
('Spieltag 3'),
('Spieltag 4'),
('Spieltag 5'),
('Spieltag 6'),
('Spieltag 7'),
('Spieltag 8'),
('Spieltag 9'),
('Spieltag 10'),
('Spieltag 11'),
('Spieltag 12'),
('Spieltag 13'),
('Spieltag 14'),
('Spieltag 15'),
('Spieltag 16'),
('Spieltag 17'),
('Spieltag 18'),
('Spieltag 19'),
('Spieltag 20'),
('Spieltag 21'),
('Spieltag 22'),
('Spieltag 23'),
('Spieltag 24'),
('Spieltag 25'),
('Spieltag 26'),
('Spieltag 27'),
('Spieltag 28'),
('Spieltag 29'),
('Spieltag 30'),
('Spieltag 31'),
('Spieltag 32'),
('Spieltag 33'),
('Spieltag 34');


INSERT INTO USER (first_name, last_name, mail_address, password) VALUES
  ('Jonas', 'Weigt', 'jonas@skatrunde.de','jonas'),
  ('Marcel', 'Hosenthien', 'hose@skatrunde.de', 'hose'),
  ('Franz', 'Deelmann', 'franz@skatrunde.de', 'franz');
  
