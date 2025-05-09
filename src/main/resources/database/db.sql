PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE ingredient_to_recipe
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
INSERT INTO ingredient_to_recipe VALUES(1,0,0,1);
INSERT INTO ingredient_to_recipe VALUES(2,0,0,2);
INSERT INTO ingredient_to_recipe VALUES(4,0,0,4);
INSERT INTO ingredient_to_recipe VALUES(5,0,0,5);
INSERT INTO ingredient_to_recipe VALUES(1,1,0,6);
INSERT INTO ingredient_to_recipe VALUES(2,1,0,7);
INSERT INTO ingredient_to_recipe VALUES(4,1,0,8);
INSERT INTO ingredient_to_recipe VALUES(5,1,0,9);
INSERT INTO ingredient_to_recipe VALUES(8,0,0,10);
INSERT INTO ingredient_to_recipe VALUES(1,2,0,11);
INSERT INTO ingredient_to_recipe VALUES(2,2,0,12);
INSERT INTO ingredient_to_recipe VALUES(1,3,1,13);
INSERT INTO ingredient_to_recipe VALUES(2,3,1,14);
INSERT INTO ingredient_to_recipe VALUES(1,4,1,15);
INSERT INTO ingredient_to_recipe VALUES(3,4,0,16);
INSERT INTO ingredient_to_recipe VALUES(5,4,1,17);
INSERT INTO ingredient_to_recipe VALUES(9,4,1,18);
INSERT INTO ingredient_to_recipe VALUES(1,5,1,19);
INSERT INTO ingredient_to_recipe VALUES(3,5,0,20);
INSERT INTO ingredient_to_recipe VALUES(7,5,0,21);
INSERT INTO ingredient_to_recipe VALUES(9,5,0,22);
INSERT INTO ingredient_to_recipe VALUES(1,6,2,23);
INSERT INTO ingredient_to_recipe VALUES(1,7,2,24);
INSERT INTO ingredient_to_recipe VALUES(1,8,2,25);
INSERT INTO ingredient_to_recipe VALUES(2,8,1,26);
INSERT INTO ingredient_to_recipe VALUES(5,8,1,27);
INSERT INTO ingredient_to_recipe VALUES(7,8,2,28);
INSERT INTO ingredient_to_recipe VALUES(8,1,0,29);
INSERT INTO ingredient_to_recipe VALUES(9,8,2,30);
INSERT INTO ingredient_to_recipe VALUES(2,9,2,31);
INSERT INTO ingredient_to_recipe VALUES(2,10,1,32);
INSERT INTO ingredient_to_recipe VALUES(9,10,2,33);
INSERT INTO ingredient_to_recipe VALUES(2,16,2,34);
INSERT INTO ingredient_to_recipe VALUES(6,16,1,35);
INSERT INTO ingredient_to_recipe VALUES(2,17,2,36);
INSERT INTO ingredient_to_recipe VALUES(3,21,0,37);
INSERT INTO ingredient_to_recipe VALUES(3,22,1,38);
INSERT INTO ingredient_to_recipe VALUES(3,23,1,39);
INSERT INTO ingredient_to_recipe VALUES(3,24,1,40);
INSERT INTO ingredient_to_recipe VALUES(8,24,2,41);
INSERT INTO ingredient_to_recipe VALUES(3,25,2,42);
INSERT INTO ingredient_to_recipe VALUES(3,26,2,43);
INSERT INTO ingredient_to_recipe VALUES(6,26,0,44);
INSERT INTO ingredient_to_recipe VALUES(3,27,2,45);
INSERT INTO ingredient_to_recipe VALUES(4,28,0,46);
INSERT INTO ingredient_to_recipe VALUES(8,8,1,47);
INSERT INTO ingredient_to_recipe VALUES(8,31,1,48);
INSERT INTO ingredient_to_recipe VALUES(4,32,1,49);
INSERT INTO ingredient_to_recipe VALUES(4,33,1,50);
INSERT INTO ingredient_to_recipe VALUES(4,34,2,51);
INSERT INTO ingredient_to_recipe VALUES(5,34,2,52);
INSERT INTO ingredient_to_recipe VALUES(4,35,2,53);
INSERT INTO ingredient_to_recipe VALUES(5,35,2,54);
INSERT INTO ingredient_to_recipe VALUES(4,36,2,55);
INSERT INTO ingredient_to_recipe VALUES(5,40,0,56);
INSERT INTO ingredient_to_recipe VALUES(7,40,0,57);
INSERT INTO ingredient_to_recipe VALUES(5,41,1,58);
INSERT INTO ingredient_to_recipe VALUES(8,41,1,59);
INSERT INTO ingredient_to_recipe VALUES(9,41,1,60);
INSERT INTO ingredient_to_recipe VALUES(5,46,2,61);
INSERT INTO ingredient_to_recipe VALUES(6,48,0,62);
INSERT INTO ingredient_to_recipe VALUES(9,48,0,63);
INSERT INTO ingredient_to_recipe VALUES(6,49,0,64);
INSERT INTO ingredient_to_recipe VALUES(6,50,1,65);
INSERT INTO ingredient_to_recipe VALUES(7,50,2,66);
INSERT INTO ingredient_to_recipe VALUES(6,51,1,67);
INSERT INTO ingredient_to_recipe VALUES(6,53,2,68);
INSERT INTO ingredient_to_recipe VALUES(6,54,2,69);
INSERT INTO ingredient_to_recipe VALUES(8,54,2,70);
INSERT INTO ingredient_to_recipe VALUES(6,55,2,71);
INSERT INTO ingredient_to_recipe VALUES(7,57,0,72);
INSERT INTO ingredient_to_recipe VALUES(9,57,0,73);
INSERT INTO ingredient_to_recipe VALUES(7,59,1,74);
INSERT INTO ingredient_to_recipe VALUES(7,60,1,75);
INSERT INTO ingredient_to_recipe VALUES(7,61,1,76);
INSERT INTO ingredient_to_recipe VALUES(7,62,2,77);
INSERT INTO ingredient_to_recipe VALUES(8,66,0,78);
INSERT INTO ingredient_to_recipe VALUES(8,72,2,79);
INSERT INTO ingredient_to_recipe VALUES(9,79,1,80);
INSERT INTO ingredient_to_recipe VALUES(9,82,2,81);
INSERT INTO ingredient_to_recipe VALUES(4,31,1,82);
CREATE TABLE languages
(
    id   INT
        primary key,
    name VARCHAR(20)
);
INSERT INTO languages VALUES(1,'English');
INSERT INTO languages VALUES(2,'German');
CREATE TABLE recipe
(
    id        INT
        primary key,
    region_id INT
        references regions,
    name      TEXT
);
INSERT INTO recipe VALUES(1,3,'Burger');
INSERT INTO recipe VALUES(2,3,'Fajita');
INSERT INTO recipe VALUES(3,3,'Pancake');
INSERT INTO recipe VALUES(4,2,'Curry');
INSERT INTO recipe VALUES(5,2,'Wok');
INSERT INTO recipe VALUES(6,2,'Sushi');
INSERT INTO recipe VALUES(7,1,'Raclette');
INSERT INTO recipe VALUES(8,1,'Fleischfondue');
INSERT INTO recipe VALUES(9,1,'Pizza');
CREATE TABLE regions
(
    id    INT
        primary key,
    title VARCHAR(20)
);
INSERT INTO regions VALUES(1,'Europe');
INSERT INTO regions VALUES(2,'Asia');
INSERT INTO regions VALUES(3,'Amerika');
CREATE TABLE translations
(
    id          INT
        primary key,
    text        VARCHAR(255),
    language_id INT
        references languages
);
INSERT INTO translations VALUES(1,'Hello',1);
INSERT INTO translations VALUES(2,'Hallo',2);
CREATE TABLE highscore
(
    score INTEGER not null,
    name  TEXT,
    id    integer not null
    constraint highscore_pk
    primary key autoincrement
);
CREATE TABLE IF NOT EXISTS "ingredient"
(
    id         INT       not null
        primary key,
    title      CHAR(255) not null,
    co2_score  INT       not null,
    identifier TEXT
);
INSERT INTO ingredient VALUES(0,'Rindfleisch',13600,'034');
INSERT INTO ingredient VALUES(1,'Poulet',5500,'031');
INSERT INTO ingredient VALUES(2,'Falafel',1100,'014');
INSERT INTO ingredient VALUES(3,'Käse',5700,'022');
INSERT INTO ingredient VALUES(4,'Ananas',10000,'001');
INSERT INTO ingredient VALUES(5,'Speck',4600,'005');
INSERT INTO ingredient VALUES(6,'Tomate',500,'044');
INSERT INTO ingredient VALUES(7,'Salat',200,'036');
INSERT INTO ingredient VALUES(8,'Zwiebel',200,'046');
INSERT INTO ingredient VALUES(9,'Bohnen',800,'008');
INSERT INTO ingredient VALUES(10,'Mais',600,'027');
INSERT INTO ingredient VALUES(16,'Avocado',600,'004');
INSERT INTO ingredient VALUES(17,'Knoblauch',200,'024');
INSERT INTO ingredient VALUES(21,'Sahne',4200,'035');
INSERT INTO ingredient VALUES(22,'Erdbeer',500,'013');
INSERT INTO ingredient VALUES(23,'Eier',3000,'012');
INSERT INTO ingredient VALUES(24,'Knoblauchsauce',4500,'025');
INSERT INTO ingredient VALUES(25,'Banane',600,'007');
INSERT INTO ingredient VALUES(26,'Lachs',5100,'026');
INSERT INTO ingredient VALUES(27,'Schokolade',4100,'038');
INSERT INTO ingredient VALUES(28,'Tofu',1000,'043');
INSERT INTO ingredient VALUES(31,'Brokkoli',300,'009');
INSERT INTO ingredient VALUES(32,'Aubergine',200,'003');
INSERT INTO ingredient VALUES(33,'Bambussprossen',200,'006');
INSERT INTO ingredient VALUES(34,'Reis',3000,'033');
INSERT INTO ingredient VALUES(35,'Nudeln',700,'029');
INSERT INTO ingredient VALUES(36,'Fladenbrot',1000,'015');
INSERT INTO ingredient VALUES(40,'Heuschrecken',300,'017');
INSERT INTO ingredient VALUES(41,'Champignon',1300,'010');
INSERT INTO ingredient VALUES(46,'Quinoa',600,'032');
INSERT INTO ingredient VALUES(48,'Thunfisch',2400,'042');
INSERT INTO ingredient VALUES(49,'Gurke',200,'016');
INSERT INTO ingredient VALUES(50,'Kaviar',10000,'023');
INSERT INTO ingredient VALUES(51,'Karotten',100,'020');
INSERT INTO ingredient VALUES(53,'Ingwer',200,'018');
INSERT INTO ingredient VALUES(54,'Sojasauce',1000,'040');
INSERT INTO ingredient VALUES(55,'Wasabi',100,'045');
INSERT INTO ingredient VALUES(57,'Sardellen',2400,'037');
INSERT INTO ingredient VALUES(59,'Kartoffel',300,'021');
INSERT INTO ingredient VALUES(60,'Süsskartoffel',200,'041');
INSERT INTO ingredient VALUES(61,'Maniok',200,'028');
INSERT INTO ingredient VALUES(62,'Kapern',100,'019');
INSERT INTO ingredient VALUES(66,'Schweinefleisch',4600,'039');
INSERT INTO ingredient VALUES(72,'Cocktailsauce',3000,'011');
INSERT INTO ingredient VALUES(79,'Olive',2000,'030');
INSERT INTO ingredient VALUES(82,'Artischocken',200,'002');
INSERT INTO sqlite_sequence VALUES('ingredient_to_recipe',82);
INSERT INTO sqlite_sequence VALUES('highscore',12);
COMMIT;
