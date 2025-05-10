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
INSERT INTO ingredient_to_recipe VALUES(2,66,0,2);
INSERT INTO ingredient_to_recipe VALUES(3,22,0,3);
INSERT INTO ingredient_to_recipe VALUES(4,0,0,4);
INSERT INTO ingredient_to_recipe VALUES(5,47,0,5);
INSERT INTO ingredient_to_recipe VALUES(1,1,0,6);
INSERT INTO ingredient_to_recipe VALUES(2,1,0,7);
INSERT INTO ingredient_to_recipe VALUES(4,1,0,8);
INSERT INTO ingredient_to_recipe VALUES(5,66,0,9);
INSERT INTO ingredient_to_recipe VALUES(8,0,0,10);
INSERT INTO ingredient_to_recipe VALUES(1,2,0,11);
INSERT INTO ingredient_to_recipe VALUES(2,2,0,12);
INSERT INTO ingredient_to_recipe VALUES(1,3,1,13);
INSERT INTO ingredient_to_recipe VALUES(2,3,2,14);
INSERT INTO ingredient_to_recipe VALUES(1,4,1,15);
INSERT INTO ingredient_to_recipe VALUES(3,5,0,16);
INSERT INTO ingredient_to_recipe VALUES(5,40,0,17);
INSERT INTO ingredient_to_recipe VALUES(9,41,1,18);
INSERT INTO ingredient_to_recipe VALUES(1,6,1,19);
INSERT INTO ingredient_to_recipe VALUES(3,21,0,20);
INSERT INTO ingredient_to_recipe VALUES(7,59,0,21);
INSERT INTO ingredient_to_recipe VALUES(9,6,0,22);
INSERT INTO ingredient_to_recipe VALUES(1,7,2,23);
INSERT INTO ingredient_to_recipe VALUES(1,8,2,24);
INSERT INTO ingredient_to_recipe VALUES(1,24,2,25);
INSERT INTO ingredient_to_recipe VALUES(2,9,1,26);
INSERT INTO ingredient_to_recipe VALUES(5,41,1,27);
INSERT INTO ingredient_to_recipe VALUES(7,8,2,28);
INSERT INTO ingredient_to_recipe VALUES(8,28,0,29);
INSERT INTO ingredient_to_recipe VALUES(9,82,2,30);
INSERT INTO ingredient_to_recipe VALUES(2,10,2,31);
INSERT INTO ingredient_to_recipe VALUES(2,8,1,32);
INSERT INTO ingredient_to_recipe VALUES(9,80,2,33);
INSERT INTO ingredient_to_recipe VALUES(2,16,2,34);
INSERT INTO ingredient_to_recipe VALUES(6,49,0,35);
INSERT INTO ingredient_to_recipe VALUES(2,17,2,36);
INSERT INTO ingredient_to_recipe VALUES(3,23,1,37);
INSERT INTO ingredient_to_recipe VALUES(3,25,1,38);
INSERT INTO ingredient_to_recipe VALUES(3,27,1,39);
INSERT INTO ingredient_to_recipe VALUES(3,70,2,40);
INSERT INTO ingredient_to_recipe VALUES(8,15,2,41);
INSERT INTO ingredient_to_recipe VALUES(3,71,2,42);
INSERT INTO ingredient_to_recipe VALUES(3,4,2,43);
INSERT INTO ingredient_to_recipe VALUES(6,48,0,44);
INSERT INTO ingredient_to_recipe VALUES(4,28,0,46);
INSERT INTO ingredient_to_recipe VALUES(8,31,1,47);
INSERT INTO ingredient_to_recipe VALUES(8,41,1,48);
INSERT INTO ingredient_to_recipe VALUES(4,32,1,49);
INSERT INTO ingredient_to_recipe VALUES(4,33,1,50);
INSERT INTO ingredient_to_recipe VALUES(4,31,1,51);
INSERT INTO ingredient_to_recipe VALUES(5,42,1,52);
INSERT INTO ingredient_to_recipe VALUES(4,34,2,53);
INSERT INTO ingredient_to_recipe VALUES(5,43,1,54);
INSERT INTO ingredient_to_recipe VALUES(4,34,2,55);
INSERT INTO ingredient_to_recipe VALUES(5,35,2,56);
INSERT INTO ingredient_to_recipe VALUES(7,40,0,57);
INSERT INTO ingredient_to_recipe VALUES(5,46,2,58);
INSERT INTO ingredient_to_recipe VALUES(8,3,1,59);
INSERT INTO ingredient_to_recipe VALUES(9,79,1,60);
INSERT INTO ingredient_to_recipe VALUES(5,44,2,61);
INSERT INTO ingredient_to_recipe VALUES(6,47,0,62);
INSERT INTO ingredient_to_recipe VALUES(9,57,0,63);
INSERT INTO ingredient_to_recipe VALUES(6,51,1,64);
INSERT INTO ingredient_to_recipe VALUES(6,54,1,65);
INSERT INTO ingredient_to_recipe VALUES(7,50,2,66);
INSERT INTO ingredient_to_recipe VALUES(6,50,1,67);
INSERT INTO ingredient_to_recipe VALUES(6,53,2,68);
INSERT INTO ingredient_to_recipe VALUES(6,55,2,69);
INSERT INTO ingredient_to_recipe VALUES(8,54,2,70);
INSERT INTO ingredient_to_recipe VALUES(6,52,2,71);
INSERT INTO ingredient_to_recipe VALUES(7,57,0,72);
INSERT INTO ingredient_to_recipe VALUES(9,20,0,73);
INSERT INTO ingredient_to_recipe VALUES(7,60,1,74);
INSERT INTO ingredient_to_recipe VALUES(7,69,1,75);
INSERT INTO ingredient_to_recipe VALUES(7,61,1,76);
INSERT INTO ingredient_to_recipe VALUES(7,62,2,77);
INSERT INTO ingredient_to_recipe VALUES(8,66,0,78);
INSERT INTO ingredient_to_recipe VALUES(8,72,2,79);
INSERT INTO ingredient_to_recipe VALUES(9,4,1,80);
INSERT INTO ingredient_to_recipe VALUES(9,24,2,81);
INSERT INTO ingredient_to_recipe VALUES(4,34,2,82);
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
CREATE TABLE if not exists highscore
(
    score INTEGER not null,
    name  TEXT,
    id    integer not null
        constraint highscore_pk
            primary key autoincrement
);
CREATE TABLE IF NOT EXISTS "ingredient" (
                                            id INTEGER PRIMARY KEY,
                                            title CHAR(255),
                                            co2_score INTEGER,
                                            comment TEXT
                                            identifier TEXT
);
INSERT INTO ingredient VALUES(0,'Rindfleisch',13300,'Aber Achtung: Rindfleisch verursacht besonders viel CO₂ wegen der Methan-Emissionen. Vielleicht findest du eine klimafreundlichere Alternative?');
INSERT INTO ingredient VALUES(1,'Poulet',3400,replace('Nur ein kleiner Hinweis: Auch Poulet stammt aus Tierhaltung, was mit CO₂ verbunden ist. Vielleicht entdeckst eine noch klimafreundlichere pflanzliche Alternative.\n','\n',char(10)));
INSERT INTO ingredient VALUES(2,'Falafel',2500,'Dein Rezept ist nicht nur lecker, sondern auch richtig nachhaltig');
INSERT INTO ingredient VALUES(3,'Käse',8500,'Aber Käse braucht viel Milch, und das verursacht leider ziemlich viel CO₂. Für das Klima wäre eine pflanzliche Alternative vielleicht die bessere Wahl. Probier dich doch mal durch!');
INSERT INTO ingredient VALUES(4,'Ananas',15100,'Aber Ananas kommt meist von weit her und wird gekühlt transportiert.Das ist für’s Klima eher ungünstig. Vielleicht findest du etwas, das näher wächst?');
INSERT INTO ingredient VALUES(5,'Speck',4600,replace('Speck ist da ein Klassiker! Allerdings braucht die Verarbeitung von Fleisch mehr Energie. Für’s Klima geht das auch leichter – probier doch mal was Neues!\n\n','\n',char(10)));
INSERT INTO ingredient VALUES(6,'Tomate',200,'Dein Rezept ist nicht nur lecker, sondern auch richtig nachhaltig.');
INSERT INTO ingredient VALUES(7,'Salat',425,'Deine Zutatenwahl ist top für dich und die Umwelt.');
INSERT INTO ingredient VALUES(8,'Zwiebel',420,'Deine Zutatenwahl ist top für dich und die Umwelt.');
INSERT INTO ingredient VALUES(9,'Bohnen',1050,'Du bist sehr klimafreundlich unterwegs - weiter so!');
INSERT INTO ingredient VALUES(10,'Mais',1500,'Nur so als Hinweis: Der Anbau von Mais braucht viel Wasser. Vielleicht findest du beim nächsten Mal eine klimafreundlichere Variante.');
INSERT INTO ingredient VALUES(12,'Rindfleisch',13300,'Aber Achtung: Rindfleisch verursacht besonders viel CO₂ wegen der Methan-Emissionen. Vielleicht findest du eine klimafreundlichere Alternative?');
INSERT INTO ingredient VALUES(13,'Poulet',3400,replace('Nur ein kleiner Hinweis: Auch Poulet stammt aus Tierhaltung, was mit CO₂ verbunden ist. Vielleicht entdeckst eine noch klimafreundlichere pflanzliche Alternative.\n','\n',char(10)));
INSERT INTO ingredient VALUES(14,'Falafel',2500,'Dein Rezept ist nicht nur lecker, sondern auch richtig nachhaltig.');
INSERT INTO ingredient VALUES(15,'Zwiebel',420,'Deine Zutatenwahl ist top für dich und die Umwelt.');
INSERT INTO ingredient VALUES(16,'Avocado',846,'Du bist sehr klimafreundlich unterwegs - weiter so!');
INSERT INTO ingredient VALUES(17,'Knoblauch',415,replace('Du hast richtig klimafreundlich ausgewählt. Weiter so!\n','\n',char(10)));
INSERT INTO ingredient VALUES(18,'Käse',8500,'Aber Käse braucht viel Milch, und das verursacht leider ziemlich viel CO₂. Für das Klima wäre eine pflanzliche Alternative vielleicht die bessere Wahl. Probier dich doch mal durch!');
INSERT INTO ingredient VALUES(19,'Ananas',15100,'Aber Ananas kommt meist von weit her und wird gekühlt transportiert.Das ist für’s Klima eher ungünstig. Vielleicht findest du etwas, das näher wächst?');
INSERT INTO ingredient VALUES(20,'Speck',4600,replace('Speck ist da ein Klassiker! Allerdings braucht die Verarbeitung von Fleisch mehr Energie. Für’s Klima geht das auch leichter – probier doch mal was Neues!\n\n','\n',char(10)));
INSERT INTO ingredient VALUES(21,'Sahne',7600,'Nur: Sahne ist ein Milchprodukt mit viel Energieaufwand. Eine leichtere Alternative wäre vielleicht auch lecker.');
INSERT INTO ingredient VALUES(22,'Erdbeer',300,'Dein Rezept ist nicht nur lecker, sondern auch richtig nachhaltig.');
INSERT INTO ingredient VALUES(23,'Eier',1950,'Aber Eier tragen wegen der Hühnerhaltung doch mehr zur Klimaerwärmung bei, als viele denken.');
INSERT INTO ingredient VALUES(24,'Knoblauchsauce',2800,'Du bist sehr klimafreundlich unterwegs - weiter so!');
INSERT INTO ingredient VALUES(25,'Banane',110,'Dein Rezept ist nicht nur lecker, sondern auch richtig nachhaltig.');
INSERT INTO ingredient VALUES(26,'Lachs',5100,replace('Aber Lachs aus Zucht oder Wildfang hat einen recht hohen CO₂-Fussabdruck. Es gibt auch andere tolle Zutaten mit weniger Umweltauswirkungen.\n\n','\n',char(10)));
INSERT INTO ingredient VALUES(27,'Schokolade',13800,'Nur ein kleiner Hinweis: Schokolade entsteht aus Kakao, Milch und braucht viel Transport. Für das Klima ist sie leider nicht die beste Wahl – aber ab und zu ist Naschen auch okay.');
INSERT INTO ingredient VALUES(28,'Tofu',2100,'Mia mag Tofu-Rezepte! Zwar braucht die Herstellung einiges an Wasser und Energie, aber er ist trotzdem oft besser als Fleisch. Weiter so!');
INSERT INTO ingredient VALUES(29,'Poulet',3400,replace('Nur ein kleiner Hinweis: Auch Poulet stammt aus Tierhaltung, was mit CO₂ verbunden ist. Vielleicht entdeckst eine noch klimafreundlichere pflanzliche Alternative.\n','\n',char(10)));
INSERT INTO ingredient VALUES(30,'Rindfleisch',13300,'Aber Achtung: Rindfleisch verursacht besonders viel CO₂ wegen der Methan-Emissionen. Vielleicht findest du eine klimafreundlichere Alternative?');
INSERT INTO ingredient VALUES(31,'Brokkoli',500,replace('Du hast richtig klimafreundlich ausgewählt. Weiter so!\n','\n',char(10)));
INSERT INTO ingredient VALUES(32,'Aubergine',410,replace('Du hast richtig klimafreundlich ausgewählt. Weiter so!\n','\n',char(10)));
INSERT INTO ingredient VALUES(33,'Bambussprossen',405,replace('Du hast richtig klimafreundlich ausgewählt. Weiter so!\n','\n',char(10)));
INSERT INTO ingredient VALUES(34,'Reis',6000,'Beim Reisanbau entstehen viele Treibhausgase. Deshalb greifen viele lieber zu Hirse');
INSERT INTO ingredient VALUES(35,'Nudeln',1300,'Du bist sehr klimafreundlich unterwegs - weiter so!');
INSERT INTO ingredient VALUES(36,'Fladenbrot',1600,' Dein Rezept ist nicht nur lecker, sondern auch richtig nachhaltig.');
INSERT INTO ingredient VALUES(37,'Rindfleisch',13300,'Aber Achtung: Rindfleisch verursacht besonders viel CO₂ wegen der Methan-Emissionen. Vielleicht findest du eine klimafreundlichere Alternative?');
INSERT INTO ingredient VALUES(38,'Poulet',3400,'Nur ein kleiner Hinweis: Auch Poulet stammt aus Tierhaltung, was mit CO₂ verbunden ist. Vielleicht entdeckst eine noch klimafreundlichere pflanzliche Alternative.');
INSERT INTO ingredient VALUES(40,'Heuschrecken',2500,'Du bist sehr klimafreundlich unterwegs - weiter so!');
INSERT INTO ingredient VALUES(41,'Champignon',1300,'Du bist sehr klimafreundlich unterwegs - weiter so!');
INSERT INTO ingredient VALUES(42,'Zwiebel',420,'Deine Zutatenwahl ist top für dich und die Umwelt.');
INSERT INTO ingredient VALUES(43,'Ananas',15100,'Aber Ananas kommt meist von weit her und wird gekühlt transportiert.Das ist für’s Klima eher ungünstig. Vielleicht findest du etwas, das näher wächst?');
INSERT INTO ingredient VALUES(44,'Reis',6000,'Beim Reisanbau entstehen viele Treibhausgase. Deshalb greifen viele lieber zu Hirse');
INSERT INTO ingredient VALUES(45,'Nudeln',1300,'Du bist sehr klimafreundlich unterwegs - weiter so!');
INSERT INTO ingredient VALUES(46,'Quinoa',2500,'Du bist sehr klimafreundlich unterwegs - weiter so!');
INSERT INTO ingredient VALUES(47,'Lachs',5100,replace('Aber Lachs aus Zucht oder Wildfang hat einen recht hohen CO₂-Fussabdruck. Es gibt auch andere tolle Zutaten mit weniger Umweltauswirkungen.\n\n','\n',char(10)));
INSERT INTO ingredient VALUES(48,'Thunfisch',6000,replace('Ein kleiner Hinweis: Thunfisch wird oft weit draußen im Meer gefangen, und der Fischfang verbraucht viel Energie. Vielleicht gibt’s eine Alternative aus der Nähe?\n\n','\n',char(10)));
INSERT INTO ingredient VALUES(49,'Gurke',402,replace('Du hast richtig klimafreundlich ausgewählt. Weiter so!\n','\n',char(10)));
INSERT INTO ingredient VALUES(50,'Kaviar',10000,'Aber Achtung: Kaviar ist extrem aufwendig in der Herstellung und hat einen sehr hohen CO₂-Ausstoß. Fürs Klima wäre etwas Einfacheres besser.');
INSERT INTO ingredient VALUES(51,'Karotten',390,'Dein Rezept ist nicht nur lecker, sondern auch richtig nachhaltig.');
INSERT INTO ingredient VALUES(52,'Avocado',846,replace('Du hast richtig klimafreundlich ausgewählt. Weiter so!\n','\n',char(10)));
INSERT INTO ingredient VALUES(53,'Ingwer',401,replace('Du hast richtig klimafreundlich ausgewählt. Weiter so!\n','\n',char(10)));
INSERT INTO ingredient VALUES(54,'Sojasauce',2100,'Dein Rezept ist nicht nur lecker, sondern auch richtig nachhaltig.');
INSERT INTO ingredient VALUES(55,'Wasabi',1500,'Nur so als Hinweis: Der Anbau von Mais braucht viel Wasser. Vielleicht findest du beim nächsten Mal eine klimafreundlichere Variante.');
INSERT INTO ingredient VALUES(56,'Speck',4600,replace('Speck ist da ein Klassiker! Allerdings braucht die Verarbeitung von Fleisch mehr Energie. Für’s Klima geht das auch leichter – probier doch mal was Neues!\n\n','\n',char(10)));
INSERT INTO ingredient VALUES(57,'Sardellen',5000,replace('Sardellen sind klein, aber beim Fang und Kühlen wird trotzdem viel Energie verbraucht. Du findest bestimmt noch etwas, das klimafreundlicher ist.\n\n','\n',char(10)));
INSERT INTO ingredient VALUES(58,'Heuschrecken',2500,'Du bist sehr klimafreundlich unterwegs - weiter so!');
INSERT INTO ingredient VALUES(59,'Kartoffel',430,replace('Du hast richtig klimafreundlich ausgewählt. Weiter so!\n\n','\n',char(10)));
INSERT INTO ingredient VALUES(60,'Süsskartoffel',398,replace('Du hast richtig klimafreundlich ausgewählt. Weiter so!\n','\n',char(10)));
INSERT INTO ingredient VALUES(61,'Maniok',396,replace('Du hast richtig klimafreundlich ausgewählt. Weiter so!\n','\n',char(10)));
INSERT INTO ingredient VALUES(62,'Kapern',388,'Dein Rezept ist nicht nur lecker, sondern auch richtig nachhaltig.! Du hast dich für eine klimafreundliche Zutat entschieden – weiter so! ');
INSERT INTO ingredient VALUES(63,'Zwiebel',420,'Deine Zutatenwahl ist top für dich und die Umwelt.');
INSERT INTO ingredient VALUES(64,'Kaviar',10000,'Aber Achtung: Kaviar ist extrem aufwendig in der Herstellung und hat einen sehr hohen CO₂-Ausstoß. Fürs Klima wäre etwas Einfacheres besser.');
INSERT INTO ingredient VALUES(65,'Rindfleisch',13300,'Aber Achtung: Rindfleisch verursacht besonders viel CO₂ wegen der Methan-Emissionen. Vielleicht findest du eine klimafreundlichere Alternative?');
INSERT INTO ingredient VALUES(66,'Schweinefleisch',3250,replace('Aber Schweinehaltung braucht viel Futter und Energie. Vielleicht entdeckst du beim nächsten Mal eine Alternative, die genauso gut passt.\n\n','\n',char(10)));
INSERT INTO ingredient VALUES(67,'Poulet',3400,replace('Nur ein kleiner Hinweis: Auch Poulet stammt aus Tierhaltung, was mit CO₂ verbunden ist. Vielleicht entdeckst eine noch klimafreundlichere pflanzliche Alternative.\n','\n',char(10)));
INSERT INTO ingredient VALUES(68,'Zwiebel',420,'Deine Zutatenwahl ist top für dich und die Umwelt.');
INSERT INTO ingredient VALUES(69,'Brokkoli',500,replace('Du hast richtig klimafreundlich ausgewählt. Weiter so!\n\n','\n',char(10)));
INSERT INTO ingredient VALUES(70,'Champignon',1300,'Du bist sehr klimafreundlich unterwegs - weiter so!');
INSERT INTO ingredient VALUES(71,'Knoblauchsauce',2800,'Du bist sehr klimafreundlich unterwegs - weiter so!');
INSERT INTO ingredient VALUES(72,'Cocktailsauce',2700,'Dein Rezept ist nicht nur lecker, sondern auch richtig nachhaltig.');
INSERT INTO ingredient VALUES(73,'Sojasauce',2100,'Dein Rezept ist nicht nur lecker, sondern auch richtig nachhaltig.');
INSERT INTO ingredient VALUES(74,'Sardellen',5000,replace('Sardellen sind klein, aber beim Fang und Kühlen wird trotzdem viel Energie verbraucht. Du findest bestimmt noch etwas, das klimafreundlicher ist.\n\n','\n',char(10)));
INSERT INTO ingredient VALUES(75,'Thunfisch',6000,replace('Ein kleiner Hinweis: Thunfisch wird oft weit draußen im Meer gefangen, und der Fischfang verbraucht viel Energie. Vielleicht gibt’s eine Alternative aus der Nähe?\n\n','\n',char(10)));
INSERT INTO ingredient VALUES(76,'Speck',4600,replace('Speck ist da ein Klassiker! Allerdings braucht die Verarbeitung von Fleisch mehr Energie. Für’s Klima geht das auch leichter – probier doch mal was Neues!\n\n','\n',char(10)));
INSERT INTO ingredient VALUES(77,'Champignon',1300,'Du bist sehr klimafreundlich unterwegs - weiter so!');
INSERT INTO ingredient VALUES(78,'Ananas',15100,'Aber Ananas kommt meist von weit her und wird gekühlt transportiert.Das ist für’s Klima eher ungünstig. Vielleicht findest du etwas, das näher wächst?');
INSERT INTO ingredient VALUES(79,'Olive',1400,'Du bist sehr klimafreundlich unterwegs - weiter so!');
INSERT INTO ingredient VALUES(80,'Zwiebel',420,'Deine Zutatenwahl ist top für dich und die Umwelt.');
INSERT INTO ingredient VALUES(81,'Mais',1500,'Dein Rezept ist nicht nur lecker, sondern auch richtig nachhaltig.');
INSERT INTO ingredient VALUES(82,'Artischocken',394,'Dein Rezept ist nicht nur lecker, sondern auch richtig nachhaltig.');
INSERT INTO sqlite_sequence VALUES('ingredient_to_recipe',82);
COMMIT;
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
