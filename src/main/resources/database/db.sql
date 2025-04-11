drop table if exists ingredient;
create table ingredient
(
    id          INT       not null
        primary key,
    title       CHAR(255) not null,
    co2_score   INT       not null,
    category_id INT
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

drop table if exists languages;
create table languages
(
    id   INT
        primary key,
    name VARCHAR(20)
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

drop table if exists regions;
create table regions
(
    id    INT
        primary key,
    title VARCHAR(20)
);

drop table if exists translations;
create table translations
(
    id          INT
        primary key,
    text        VARCHAR(255),
    language_id INT
        references languages
);

INSERT INTO translations (id, text, language_id) VALUES (1, 'Hello', 1);
INSERT INTO translations (id, text, language_id) VALUES (2, 'Hallo', 2);


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


INSERT INTO languages (id, name) VALUES (1, 'English');
INSERT INTO languages (id, name) VALUES (2, 'German');


INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (1, 1000, 0, 1);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (2, 1000, 0, 2);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (3, 1000, 0, 3);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (4, 1000, 0, 4);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (5, 1000, 0, 5);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (1, 1001, 0, 6);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (2, 1001, 0, 7);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (4, 1001, 0, 8);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (5, 1001, 0, 9);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (8, 1001, 0, 10);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (1, 1002, 0, 11);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (2, 1002, 0, 12);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (1, 1003, 1, 13);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (2, 1003, 2, 14);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (1, 1004, 1, 15);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (3, 1004, 0, 16);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (5, 1004, 1, 17);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (9, 1004, 1, 18);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (1, 1005, 1, 19);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (3, 1005, 0, 20);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (7, 1005, 0, 21);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (9, 1005, 0, 22);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (1, 1006, 2, 23);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (1, 1007, 2, 24);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (1, 1008, 2, 25);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (2, 1008, 1, 26);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (5, 1008, 1, 27);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (7, 1008, 2, 28);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (8, 1008, 1, 29);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (9, 1008, 2, 30);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (2, 1009, 2, 31);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (2, 1010, 1, 32);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (9, 1010, 2, 33);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (2, 1016, 2, 34);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (6, 1016, 1, 35);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (2, 1017, 2, 36);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (3, 1021, 0, 37);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (3, 1022, 1, 38);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (3, 1013, 1, 39);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (3, 1024, 1, 40);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (8, 1024, 2, 41);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (3, 1025, 2, 42);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (3, 1026, 2, 43);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (6, 1026, 0, 44);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (3, 1027, 2, 45);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (4, 1028, 0, 46);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (8, 1031, 1, 47);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (8, 1031, 1, 48);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (4, 1032, 1, 49);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (4, 1033, 1, 50);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (4, 1034, 2, 51);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (5, 1034, 2, 52);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (4, 1035, 2, 53);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (5, 1035, 2, 54);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (4, 1036, 2, 55);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (5, 1040, 0, 56);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (7, 1040, 0, 57);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (5, 1041, 1, 58);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (8, 1041, 1, 59);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (9, 1041, 1, 60);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (5, 1046, 2, 61);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (6, 1048, 0, 62);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (9, 1048, 0, 63);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (6, 1049, 0, 64);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (6, 1050, 1, 65);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (7, 1050, 2, 66);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (6, 1051, 1, 67);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (6, 1053, 2, 68);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (6, 1054, 2, 69);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (8, 1054, 2, 70);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (6, 1055, 2, 71);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (7, 1057, 0, 72);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (9, 1057, 0, 73);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (7, 1059, 1, 74);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (7, 1060, 1, 75);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (7, 1061, 1, 76);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (7, 1062, 2, 77);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (8, 1066, 0, 78);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (8, 1072, 2, 79);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (9, 1079, 1, 80);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (9, 1082, 2, 81);
INSERT INTO ingredient_to_recipe (recipe_id, ingredient_id, category_id, id) VALUES (4, 1031, 1, 82);


INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1000, 'Rindfleisch', 13600, 0);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1001, 'Poulet', 5500, 0);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1002, 'Falafel', 1100, 0);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1003, 'Käse', 5700, 1);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1004, 'Ananas', 10000, 1);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1005, 'Speck', 4600, 1);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1006, 'Tomate', 500, 2);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1007, 'Salat', 200, 2);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1008, 'Zwiebel', 200, 2);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1009, 'Bohnen', 800, 1);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1010, 'Mais', 600, 1);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1012, 'Rindfleisch', 13600, 0);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1013, 'Poulet', 5500, 0);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1014, 'Falafel', 1100, 0);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1015, 'Zwiebel', 200, 1);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1016, 'Avocado', 600, 2);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1017, 'Knoblauch', 200, 2);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1018, 'Käse', 5700, 2);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1019, 'Ananas', 10000, 0);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1020, 'Speck', 4600, 0);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1021, 'Sahne', 4200, 0);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1022, 'Erdbeer', 500, 1);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1023, 'Eier', 3000, 1);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1024, 'Knoblauchsauce', 4500, 1);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1025, 'Banane', 600, 2);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1026, 'Lachs', 5100, 2);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1027, 'Schokolade', 4100, 2);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1028, 'Tofu', 1000, 0);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1029, 'Poulet', 5500, 0);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1030, 'Rindfleisch', 13600, 0);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1031, 'Brokkoli', 300, 1);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1032, 'Aubergine', 200, 1);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1033, 'Bambussprossen', 200, 1);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1034, 'Reis', 3000, 2);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1035, 'Nudeln', 700, 2);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1036, 'Fladenbrot', 1000, 2);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1037, 'Rindfleisch', 13600, 0);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1038, 'Poulet', 5500, 0);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1040, 'Heuschrecken', 300, 0);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1041, 'Champignon', 1300, 1);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1042, 'Zwiebel', 200, 1);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1043, 'Ananas', 10000, 1);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1044, 'Reis', 3000, 2);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1045, 'Nudeln', 700, 2);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1046, 'Quinoa', 600, 2);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1047, 'Lachs', 5100, 0);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1048, 'Thunfisch', 2400, 0);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1049, 'Gurke', 200, 0);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1050, 'Kaviar', 10000, 1);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1051, 'Karotten', 100, 1);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1052, 'Avocado', 600, 1);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1053, 'Ingwer', 200, 2);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1054, 'Sojasauce', 1000, 2);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1055, 'Wasabi', 100, 2);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1056, 'Speck', 4600, 0);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1057, 'Sardellen', 2400, 0);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1058, 'Heuschrecken', 300, 0);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1059, 'Kartoffel', 300, 1);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1060, 'Süsskartoffel', 200, 1);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1061, 'Maniok', 200, 1);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1062, 'Kapern', 100, 2);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1063, 'Zwiebel', 200, 2);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1064, 'Kaviar', 10000, 2);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1065, 'Rindfleisch', 13600, 0);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1066, 'Schweinefleisch', 4600, 0);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1067, 'Poulet', 5500, 0);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1068, 'Zwiebel', 200, 1);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1069, 'Brokkoli', 300, 1);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1070, 'Champignon', 1300, 1);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1071, 'Knoblauchsauce', 4500, 2);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1072, 'Cocktailsauce', 3000, 2);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1073, 'Sojasauce', 1000, 2);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1074, 'Sardellen', 2400, 0);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1075, 'Thunfisch', 2400, 0);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1076, 'Speck', 4600, 0);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1077, 'Champignon', 1300, 1);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1078, 'Ananas', 10000, 1);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1079, 'Olive', 2000, 1);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1080, 'Zwiebel', 200, 2);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1081, 'Mais', 600, 2);
INSERT INTO ingredient (id, title, co2_score, category_id) VALUES (1082, 'Artischocken', 200, 2);
