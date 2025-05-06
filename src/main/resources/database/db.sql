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


INSERT INTO regions (id, title) VALUES (1, 'Europe');
INSERT INTO regions (id, title) VALUES (2, 'Asia');
INSERT INTO regions (id, title) VALUES (3, 'Amerika');


INSERT INTO recipe (id, region_id, name) VALUES (1, 3, 'Burger');
INSERT INTO recipe (id, region_id, name) VALUES (2, 3, 'Fajita');
INSERT INTO recipe (id, region_id, name) VALUES (3, 3, 'Pancake');
INSERT INTO recipe (id, region_id, name) VALUES (4, 2, 'Curry');
INSERT INTO recipe (id, region_id, name) VALUES (5, 2, 'Wok');
INSERT INTO recipe (id, region_id, name) VALUES (6, 2, 'Sushi');
INSERT INTO recipe (id, region_id, name) VALUES (7, 1, 'Raclette');
INSERT INTO recipe (id, region_id, name) VALUES (8, 1, 'Fleischfondue');
INSERT INTO recipe (id, region_id, name) VALUES (9, 1, 'Pizza');


INSERT INTO languages (id, name) VALUES (4, 'English');
INSERT INTO languages (id, name) VALUES (1, 'German');
INSERT INTO languages (id, name) VALUES (2, 'Francais');
INSERT INTO languages (id, name) VALUES (3, 'Italiano');

INSERT INTO translations (id, text, key, language_id) VALUES (1, 'Wilkommen zu Conserve&Cook!','intro',  1);
INSERT INTO translations (id, text, key, language_id) VALUES (2, 'Bienvenue chez Conserve&Cook!','intro',  2);
INSERT INTO translations (id, text, key, language_id) VALUES (3, 'Benvenuti a Conserve&Cook!','intro',  3);
INSERT INTO translations (id, text, key, language_id) VALUES (4, 'Welcome to Conserve&Cook!','intro',  4);

INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (1, 0, 0, 1);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (2, 0, 0, 2);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (4, 0, 0, 4);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (5, 0, 0, 5);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (1, 1, 0, 6);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (2, 1, 0, 7);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (4, 1, 0, 8);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (5, 1, 0, 9);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (8, 0, 0, 10);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (1, 2, 0, 11);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (2, 2, 0, 12);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (1, 3, 1, 13);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (2, 3, 1, 14);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (1, 4, 1, 15);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (3, 4, 0, 16);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (5, 4, 1, 17);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (9, 4, 1, 18);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (1, 5, 1, 19);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (3, 5, 0, 20);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (7, 5, 0, 21);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (9, 5, 0, 22);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (1, 6, 2, 23);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (1, 7, 2, 24);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (1, 8, 2, 25);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (2, 8, 1, 26);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (5, 8, 1, 27);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (7, 8, 2, 28);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (8, 1, 0, 29);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (9, 8, 2, 30);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (2, 9, 2, 31);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (2, 10, 1, 32);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (9, 10, 2, 33);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (2, 16, 2, 34);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (6, 16, 1, 35);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (2, 17, 2, 36);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (3, 21, 0, 37);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (3, 22, 1, 38);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (3, 23, 1, 39);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (3, 24, 1, 40);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (8, 24, 2, 41);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (3, 25, 2, 42);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (3, 26, 2, 43);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (6, 26, 0, 44);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (3, 27, 2, 45);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (4, 28, 0, 46);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (8, 15, 1, 47);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (8, 31, 1, 48);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (4, 32, 1, 49);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (4, 33, 1, 50);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (4, 34, 2, 51);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (5, 34, 2, 52);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (4, 35, 2, 53);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (5, 35, 2, 54);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (4, 36, 2, 55);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (5, 40, 0, 56);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (7, 40, 0, 57);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (5, 41, 1, 58);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (8, 41, 1, 59);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (9, 41, 1, 60);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (5, 46, 2, 61);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (6, 48, 0, 62);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (9, 48, 0, 63);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (6, 49, 0, 64);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (6, 50, 1, 65);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (7, 50, 2, 66);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (6, 51, 1, 67);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (6, 53, 2, 68);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (6, 54, 2, 69);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (8, 54, 2, 70);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (6, 55, 2, 71);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (7, 57, 0, 72);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (9, 57, 0, 73);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (7, 59, 1, 74);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (7, 60, 1, 75);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (7, 61, 1, 76);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (7, 62, 2, 77);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (8, 66, 0, 78);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (8, 72, 2, 79);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (9, 79, 1, 80);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (9, 82, 2, 81);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (4, 31, 1, 82);
