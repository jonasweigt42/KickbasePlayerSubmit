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


INSERT INTO USER (user_name, password) VALUES
  ('sigi','$2a$10$901M6MjwRZHYRzZTRqvOZeouxfjAUA.PPHVKXmLVKIr2iEodwLhHO'),
  ('hose', '$2a$10$EdQewR3e4dBxxmjZTnM60OdCWNeWEUkODQ1VkjneOrrCodL/X05iq'),
  ('franz', '$2a$10$A..xU6lE3QiOttkzcW1Ow.MpzlbI8ys7tcQ4ctcEnKMe5iRl82PNm');
  
 
INSERT INTO PLAYER_SUBMIT(player_name, user_name, spieltag, saison, punkte) VALUES
('Stindl', 'Sigi', '2', '2021/2022', -57),
('Kramaric', 'Hose', '2', '2021/2022', 112),
('Reus', 'Franz', '2', '2021/2022', 56);
