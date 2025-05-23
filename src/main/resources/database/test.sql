PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE languages
(
    id   INT         NOT NULL,
    name VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);
INSERT INTO languages VALUES(1,'deutsch');
INSERT INTO languages VALUES(2,'französisch');
INSERT INTO languages VALUES(3,'english');
INSERT INTO languages VALUES(4,'italienisch');
CREATE TABLE translations
(
    id          INT primary key NOT NULL,
    text        VARCHAR(255)    NOT NULL,
    key         VARCHAR(255)    NOT NULL,
    language_id INT             NOT NULL,
    FOREIGN KEY (language_id) REFERENCES languages (id)
);
INSERT INTO translations VALUES(1,'Wilkommen zu Conserve&Cook!','intro',1);
INSERT INTO translations VALUES(2,'Bienvenue chez Conserve&Cook!','intro',2);
INSERT INTO translations VALUES(3,'Benvenuti a Conserve&Cook!','intro',3);
INSERT INTO translations VALUES(4,'Welcome to Conserve&Cook!','intro',4);

INSERT INTO translations VALUES(5,'rind ist schlecht!','comment.0',1);
INSERT INTO translations VALUES(6,'le bœuf est mauvais!','comment.0',2);
INSERT INTO translations VALUES(7,'il manzo è cattivo!','comment.0',3);
INSERT INTO translations VALUES(8,'beef is bad!','comment.0',4);

CREATE TABLE regions
(
    id    INT         NOT NULL,
    title VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);
INSERT INTO regions VALUES(1,'europe');
INSERT INTO regions VALUES(2,'asia');
INSERT INTO regions VALUES(3,'africa');
INSERT INTO regions VALUES(4,'america');
CREATE TABLE recipe
(
    id        INT not null primary key,
    region_id INT references regions,
    name      TEXT
);
INSERT INTO recipe VALUES(1,1,'Burger');
CREATE TABLE IF NOT EXISTS "ingredient"
(
    id          INT       not null
        primary key,
    title       CHAR(255) not null,
    co2_score   INT       not null,
    category_id INT,
    comment     TEXT,
    identifier TEXT
);
INSERT INTO ingredient VALUES(1,'poulet',20,0,'abc','001');
INSERT INTO ingredient VALUES(0,'rind',40,2,'rind ist schlecht!','000');
INSERT INTO ingredient VALUES(66,'schwein',30,0,'abc','066');
INSERT INTO ingredient VALUES(78,'ananas',10,1,'abc','078');
INSERT INTO ingredient VALUES(18,'kaese',20,1,'abc','018');
INSERT INTO ingredient VALUES(8,'zwiebel',5,1,'abc','008');
INSERT INTO ingredient VALUES(16,'avocado',20,2,'abc','016');
INSERT INTO ingredient VALUES(7,'salat',3,2,'abc','007');
INSERT INTO ingredient VALUES(6,'tomate',5,2,'abc','006');
CREATE TABLE ingredient_to_recipe
(
    id            INT NOT NULL,
    recipe_id     INT NOT NULL,
    ingredient_id INT NOT NULL,
    category_id   INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (recipe_id) REFERENCES recipe (id),
    FOREIGN KEY (ingredient_id) REFERENCES ingredient (id)
);
INSERT INTO ingredient_to_recipe VALUES(1,1,78,1);
INSERT INTO ingredient_to_recipe VALUES(2,1,16,2);
INSERT INTO ingredient_to_recipe VALUES(3,1,18,1);
INSERT INTO ingredient_to_recipe VALUES(4,1,1,0);
INSERT INTO ingredient_to_recipe VALUES(5,1,0,2);
INSERT INTO ingredient_to_recipe VALUES(6,1,7,2);
INSERT INTO ingredient_to_recipe VALUES(7,1,66,0);
INSERT INTO ingredient_to_recipe VALUES(8,1,6,2);
INSERT INTO ingredient_to_recipe VALUES(9,1,8,1);
CREATE TABLE highscore
(
    score INTEGER not null,
    name  TEXT,
    id    integer not null
        constraint highscore_pk
            primary key autoincrement
);
COMMIT;
