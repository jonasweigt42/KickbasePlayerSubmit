INSERT INTO SPIELTAG (name, start_date_time) VALUES
('1', '2021-08-13 20:30:00'),
('2', '2021-08-20 20:30:00'),
('3', '2021-08-27 20:30:00'),
('4', '2021-09-11 15:30:00'),
('5', '2021-09-17 20:30:00'),
('6', '2021-09-24 20:30:00'),
('7', '2021-10-01 20:30:00') 
('8', '2021-10-15 20:30:00'),
('9', '2021-10-22 20:30:00'),
('10','2021-10-29 20:30:00'),
('11','2021-11-05 20:30:00'),
('12','2021-11-19 20:30:00'),
('13','2021-11-26 20:30:00'),
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
('Stindl', 'sigi', '2', '2021/2022', -57),
('Kramaric', 'hose', '2', '2021/2022', 112),
('Reus', 'franz', '2', '2021/2022', 56);
