DROP TABLE if exists languages;
CREATE TABLE if not exists languages
(
    id   INT NOT NULL,
    name VARCHAR(20)     NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE if exists translations;
CREATE TABLE if not exists translations
(
    id          INT primary key NOT NULL,
    text        VARCHAR(255)    NOT NULL,
    key VARCHAR(255)    NOT NULL,
    language_id INT             NOT NULL,
    FOREIGN KEY (language_id) REFERENCES languages (id)
);

DROP TABLE if exists regions;
CREATE TABLE if not exists regions
(
    id    INT         NOT NULL,
    title VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE if exists recipe;
create table if not exists recipe
(
    id        INT not null primary key,
    region_id INT references regions,
    name      TEXT
);

DROP TABLE if exists ingredient;
create table if not exists ingredient
(
    id          INT       not null primary key,
    title       CHAR(255) not null,
    co2_score   INT       not null,
    category_id INT,
    recipe_id   INT       not null,
    FOREIGN KEY (recipe_id) REFERENCES recipe (id)
);


DROP TABLE if exists ingredient_to_recipe;
CREATE TABLE if not exists ingredient_to_recipe
(
    id            INT NOT NULL,
    recipe_id     INT NOT NULL,
    ingredient_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (recipe_id) REFERENCES recipe (id),
    FOREIGN KEY (ingredient_id) REFERENCES ingredient (id)
);

DROP TABLE if exists highscore;
CREATE TABLE if not exists highscore
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
