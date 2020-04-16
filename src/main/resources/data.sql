DROP TABLE IF EXISTS users;
 
CREATE TABLE users (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  user_name VARCHAR(250) NOT NULL,
  mailaddress VARCHAR(250) DEFAULT NULL,
  password VARCHAR(250) NOT NULL
);
 
INSERT INTO users (first_name, last_name, user_name, password) VALUES
  ('Jonas', 'Weigt', 'jonas','jonas'),
  ('Eva', 'Weigt', 'eva', 'eva'),
  ('Admin', 'Admin', 'admin', 'admin');