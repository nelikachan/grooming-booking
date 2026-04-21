CREATE EXTENSION IF NOT EXISTS "pgcrypto";

INSERT INTO services (id, name, description, duration_minutes, price_min, price_max) VALUES
(gen_random_uuid(), 'Krótkowłosy mały pies - kąpiel + wyczesanie', 'np. pinczer, chihuahua, jamnik, jack russell terrier', 60, 120, 120),
(gen_random_uuid(), 'Krótkowłosy średni pies - kąpiel + wyczesanie', 'np. buldog francuski, mops, beagle', 90, 130, 200),
(gen_random_uuid(), 'Krótkowłosy duży pies - kąpiel + wyczesanie', 'np. doberman, cane corso, labrador', 120, 130, NULL),
(gen_random_uuid(), 'Usuwanie kołtunów', 'usługa dodatkowa', 5, 20, 50),
(gen_random_uuid(), 'Obcinanie pazurów', 'usługa dodatkowa, bez wizyty kompleksowej', 5, 20, NULL),
(gen_random_uuid(), 'Odbudowa i pielęgnacja zniszczonego włosa', 'usługa dodatkowa, bez wizyty kompleksowej', 5, 50, NULL),
(gen_random_uuid(), 'Usuwanie kleszczy', NULL, 30, 50, NULL),
(gen_random_uuid(), 'Higiena oczu i uszu', 'usługa dodatkowa', 30, 50, NULL);