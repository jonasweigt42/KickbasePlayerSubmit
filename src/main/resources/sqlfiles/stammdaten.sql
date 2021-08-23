INSERT INTO SPIELTAG (name) VALUES
('1'),
('2'),
('3'),
('4'),
('5'),
('6'),
('7'),
('8'),
('9'),
('10'),
('11'),
('12'),
('13'),
('14'),
('15'),
('16'),
('17'),
('18'),
('19'),
('20'),
('21'),
('22'),
('23'),
('24'),
('25'),
('26'),
('27'),
('28'),
('29'),
('30'),
('31'),
('32'),
('33'),
('34');


INSERT INTO USER (first_name, last_name, mail_address, password) VALUES
  ('Jonas', 'Weigt', 'jonas@skatrunde.de','$2a$10$6OrUGZkGY64EMXVw6dGJ1.zRiqU8tDSJHwp8Zrb4cPQIxspBCxSnO'),
  ('Hose', 'Hosenthien', 'hose@skatrunde.de', '$2a$10$EdQewR3e4dBxxmjZTnM60OdCWNeWEUkODQ1VkjneOrrCodL/X05iq'),
  ('Franz', 'Deelmann', 'franz@skatrunde.de', '$2a$10$A..xU6lE3QiOttkzcW1Ow.MpzlbI8ys7tcQ4ctcEnKMe5iRl82PNm');
  
 
INSERT INTO PLAYER_SUBMIT(player_name, user_name, spieltag, saison, punkte) VALUES
('Stindl', 'Jonas', '2', '2021/2022', -57),
('Kramaric', 'Hose', '2', '2021/2022', 112),
('Reus', 'Franz', '2', '2021/2022', 56);
