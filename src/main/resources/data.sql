INSERT INTO nave (id, name, series) VALUES (1, 'Enterprise', 'Federation');
INSERT INTO nave (id, name, series) VALUES (2, 'Millennium Falcon', 'Rebels');
INSERT INTO nave (id, name, series) VALUES (3, 'X-Wing', 'Republic');

ALTER TABLE nave ALTER COLUMN id RESTART WITH (SELECT MAX(id) + 1 FROM nave);