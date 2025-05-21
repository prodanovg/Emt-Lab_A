INSERT INTO country (id, name, continent)
VALUES (1, 'United States', 'North America'),
       (2, 'United Kingdom', 'Europe'),
       (3, 'Germany', 'Europe'),
       (4, 'France', 'Europe'),
       (5, 'Japan', 'Asia'),
       (6, 'Canada', 'North America'),
       (7, 'Italy', 'Europe'),
       (8, 'Spain', 'Europe'),
       (9, 'Sweden', 'Europe'),
       (10, 'Australia', 'Oceania');

INSERT INTO author (id, name, surname, country_id)
VALUES (1, 'Mark', 'Twain', 1),
       (2, 'Jane', 'Austen', 2),
       (3, 'Johann', 'Goethe', 3),
       (4, 'Victor', 'Hugo', 4),
       (5, 'Haruki', 'Murakami', 5),
       (6, 'Margaret', 'Atwood', 6),
       (7, 'Dante', 'Alighieri', 7),
       (8, 'Miguel', 'de Cervantes', 8),
       (9, 'Astrid', 'Lindgren', 9),
       (10, 'Tim', 'Winton', 10);

INSERT INTO book (name, description, category, author_id, available_copies)
VALUES ('The Adventures of Tom Sawyer', 'A classic American novel.', 'CLASSICS', 1, 10),
       ('Pride and Prejudice', 'A romantic novel of manners.', 'NOVEL', 2, 15),
       ('Faust', 'A tragic play by Goethe.', 'DRAMA', 3, 5),
       ('Les Misérables', 'A novel set in revolutionary France.', 'HISTORY', 4, 8),
       ('Kafka on the Shore', 'A surreal novel by Murakami.', 'FANTASY', 5, 12),
       ('The Divine Comedy', 'An epic Italian poem.', 'CLASSICS', 7, 4),
       ('Don Quixote', 'A Spanish classic.', 'CLASSICS', 8, 6),
       ('Cloudstreet', 'An Australian family saga.', 'NOVEL', 10, 7);


