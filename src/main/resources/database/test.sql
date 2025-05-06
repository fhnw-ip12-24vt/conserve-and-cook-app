drop table if exists ingredient;
create table ingredient
(
    id          INT       not null
        primary key,
    title       CHAR(255) not null,
    co2_score   INT       not null,
    comment     CHAR(255),
    category_id INT
);

drop table if exists languages;
create table languages
(
    id   INT
        primary key,
    name VARCHAR(20)
);

drop table if exists regions;
create table regions
(
    id    INT
        primary key,
    title VARCHAR(20)
);

drop table if exists recipe;
create table recipe
(
    id        INT
        primary key,
    region_id INT
        references regions,
    name      TEXT
);

drop table if exists ingredient_to_recipe;
create table ingredient_to_recipe
(
    recipe_id     INT     not null
        references recipe,
    ingredient_id INT     not null
        references ingredient,
    category_id   integer,
    id            INTEGER not null
        constraint ingredient_to_recipe_pk
            primary key autoincrement
);

drop table if exists translations;
create table translations
(
    id          INT
        primary key,
    text        VARCHAR(255),
    key         VARCHAR(255),
    language_id INT
        references languages
);

drop table if exists highscore;
create table highscore
(
    score INTEGER not null,
    name  TEXT,
    id    integer not null
        constraint highscore_pk
            primary key autoincrement
);

INSERT INTO regions (id, title)
VALUES (1, 'europe');
INSERT INTO regions (id, title)
VALUES (2, 'asia');
INSERT INTO regions (id, title)
VALUES (3, 'africa');
INSERT INTO regions (id, title)
VALUES (4, 'america');

INSERT INTO languages (id, name) VALUES (4, 'English');
INSERT INTO languages (id, name) VALUES (1, 'German');
INSERT INTO languages (id, name) VALUES (2, 'Francais');
INSERT INTO languages (id, name) VALUES (3, 'Italiano');

INSERT INTO translations (id, text, key, language_id) VALUES (1, 'Wilkommen zu Conserve&Cook!','intro',  1);
INSERT INTO translations (id, text, key, language_id) VALUES (2, 'Bienvenue chez Conserve&Cook!','intro',  2);
INSERT INTO translations (id, text, key, language_id) VALUES (3, 'Benvenuti a Conserve&Cook!','intro',  3);
INSERT INTO translations (id, text, key, language_id) VALUES (4, 'Welcome to Conserve&Cook!','intro',  4);

INSERT INTO recipe (id, region_id, name)
VALUES (1, 1, 'Burger');

INSERT INTO ingredient (id, title, co2_score, category_id, recipe_id) VALUES (1, 'poulet', 20, 0, 1);
INSERT INTO ingredient (id, title, co2_score, category_id, recipe_id) VALUES (0, 'rind', 40, 0, 1);
INSERT INTO ingredient (id, title, co2_score, category_id, recipe_id) VALUES (66, 'schwein', 30, 0, 1);
INSERT INTO ingredient (id, title, co2_score, category_id, recipe_id) VALUES (78, 'ananas', 10, 1, 1);
INSERT INTO ingredient (id, title, co2_score, category_id, recipe_id) VALUES (18, 'kaese', 20, 1, 1);
INSERT INTO ingredient (id, title, co2_score, category_id, recipe_id) VALUES (8, 'zwiebel', 5, 1, 1);
INSERT INTO ingredient (id, title, co2_score, category_id, recipe_id) VALUES (16, 'avocado', 20, 2, 1);
INSERT INTO ingredient (id, title, co2_score, category_id, recipe_id) VALUES (7, 'salat', 3, 2, 1);
INSERT INTO ingredient (id, title, co2_score, category_id, recipe_id) VALUES (6, 'tomate', 5, 2, 1);

INSERT INTO ingredient_to_recipe (id, recipe_id, ingredient_id) VALUES (1, 1, 78);
INSERT INTO ingredient_to_recipe (id, recipe_id, ingredient_id) VALUES (2, 1, 16);
INSERT INTO ingredient_to_recipe (id, recipe_id, ingredient_id) VALUES (3, 1, 18);
INSERT INTO ingredient_to_recipe (id, recipe_id, ingredient_id) VALUES (4, 1, 1);
INSERT INTO ingredient_to_recipe (id, recipe_id, ingredient_id) VALUES (5, 1, 0);
INSERT INTO ingredient_to_recipe (id, recipe_id, ingredient_id) VALUES (6, 1, 7);
INSERT INTO ingredient_to_recipe (id, recipe_id, ingredient_id) VALUES (7, 1, 66);
INSERT INTO ingredient_to_recipe (id, recipe_id, ingredient_id) VALUES (8, 1, 6);
INSERT INTO ingredient_to_recipe (id, recipe_id, ingredient_id) VALUES (9, 1, 8);
