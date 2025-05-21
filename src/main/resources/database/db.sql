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
INSERT INTO ingredient_to_recipe VALUES(2,3,1,14);
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
INSERT INTO ingredient_to_recipe VALUES(9,8,2,33);
INSERT INTO ingredient_to_recipe VALUES(2,16,2,34);
INSERT INTO ingredient_to_recipe VALUES(6,49,0,35);
INSERT INTO ingredient_to_recipe VALUES(2,17,2,36);
INSERT INTO ingredient_to_recipe VALUES(3,23,1,37);
INSERT INTO ingredient_to_recipe VALUES(3,25,1,38);
INSERT INTO ingredient_to_recipe VALUES(3,27,1,39);
INSERT INTO ingredient_to_recipe VALUES(3,41,2,40);
INSERT INTO ingredient_to_recipe VALUES(8,8,2,41);
INSERT INTO ingredient_to_recipe VALUES(3,24,2,42);
INSERT INTO ingredient_to_recipe VALUES(3,4,2,43);
INSERT INTO ingredient_to_recipe VALUES(6,48,0,44);
INSERT INTO ingredient_to_recipe VALUES(4,28,0,46);
INSERT INTO ingredient_to_recipe VALUES(8,31,1,47);
INSERT INTO ingredient_to_recipe VALUES(8,41,1,48);
INSERT INTO ingredient_to_recipe VALUES(4,32,1,49);
INSERT INTO ingredient_to_recipe VALUES(4,33,1,50);
INSERT INTO ingredient_to_recipe VALUES(4,31,1,51);
INSERT INTO ingredient_to_recipe VALUES(5,8,1,52);
INSERT INTO ingredient_to_recipe VALUES(4,34,2,53);
INSERT INTO ingredient_to_recipe VALUES(5,4,1,54);
INSERT INTO ingredient_to_recipe VALUES(4,35,2,55);
INSERT INTO ingredient_to_recipe VALUES(5,35,2,56);
INSERT INTO ingredient_to_recipe VALUES(7,40,0,57);
INSERT INTO ingredient_to_recipe VALUES(5,46,2,58);
INSERT INTO ingredient_to_recipe VALUES(8,3,1,59);
INSERT INTO ingredient_to_recipe VALUES(9,79,1,60);
INSERT INTO ingredient_to_recipe VALUES(5,34,2,61);
INSERT INTO ingredient_to_recipe VALUES(6,47,0,62);
INSERT INTO ingredient_to_recipe VALUES(9,57,0,63);
INSERT INTO ingredient_to_recipe VALUES(6,51,1,64);
INSERT INTO ingredient_to_recipe VALUES(6,54,1,65);
INSERT INTO ingredient_to_recipe VALUES(7,50,2,66);
INSERT INTO ingredient_to_recipe VALUES(6,50,1,67);
INSERT INTO ingredient_to_recipe VALUES(6,53,2,68);
INSERT INTO ingredient_to_recipe VALUES(6,55,2,69);
INSERT INTO ingredient_to_recipe VALUES(8,54,2,70);
INSERT INTO ingredient_to_recipe VALUES(6,16,2,71);
INSERT INTO ingredient_to_recipe VALUES(7,57,0,72);
INSERT INTO ingredient_to_recipe VALUES(9,5,0,73);
INSERT INTO ingredient_to_recipe VALUES(7,60,1,74);
INSERT INTO ingredient_to_recipe VALUES(7,31,1,75);
INSERT INTO ingredient_to_recipe VALUES(7,61,1,76);
INSERT INTO ingredient_to_recipe VALUES(7,62,2,77);
INSERT INTO ingredient_to_recipe VALUES(8,66,0,78);
INSERT INTO ingredient_to_recipe VALUES(8,72,2,79);
INSERT INTO ingredient_to_recipe VALUES(9,4,1,80);
INSERT INTO ingredient_to_recipe VALUES(9,24,2,81);
INSERT INTO ingredient_to_recipe VALUES(4,46,2,82);
CREATE TABLE languages
(
    id   INT
        primary key,
    name VARCHAR(20)
);
INSERT INTO languages VALUES(1,'deutsch');
INSERT INTO languages VALUES(2,'französisch');
INSERT INTO languages VALUES(3,'english');
INSERT INTO languages VALUES(4,'italienisch');
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
    id          INT primary key NOT NULL,
    text        VARCHAR(255)    NOT NULL,
    key         VARCHAR(255)    NOT NULL,
    language_id INT             NOT NULL,
    FOREIGN KEY (language_id) REFERENCES languages (id)
);

-- Tutorial Deutsch
INSERT INTO translations VALUES(1,'Wilkommen bei'|| char(10) ||'Conserve & Cook!','tutorial.intro',1);
INSERT INTO translations VALUES(5,'Dein Ziel ist es,'|| char(10) ||' leckere Gerichte '|| char(10) ||' aus aller Welt zu kochen','tutorial.aim',1);
INSERT INTO translations VALUES(6,'Die Region bestimmt,'|| char(10) ||'welche Rezepte du kochen wirst!','tutorial.region',1);
INSERT INTO translations VALUES(7,'Wähle 3 Zutaten.'|| char(10) ||'Du Bekommst neun zur Auswahl'|| char(10) ||'und musst drei davon einscannen,'|| char(10) ||' bevor die Zeit abläuft.','tutorial.ingredient',1);
INSERT INTO translations VALUES(8,'Scanne die Zutaten. Nutze den Barcodescanner,'|| char(10) ||' um Zutaten aus dem Kochbuch auszuwählen. '|| char(10) ||' Genau wie an der Supermarktkasse! '|| char(10) ||' '|| char(10) ||' Falls du eine andere Zutat möchtest,'|| char(10) ||' scanne einfach eine neue ein.','tutorial.scan',1);
INSERT INTO translations VALUES(9,'Das wars!'|| char(10) ||'Viel Spass beim Kochen!','tutorial.end',1);

-- Tutorial Französisch
INSERT INTO translations VALUES(2,'Bienvenue chez'|| char(10) ||'Conserve & Cook!','tutorial.intro',2);
INSERT INTO translations VALUES(10,'Ton objectif est'|| char(10) ||'de préparer des plats'|| char(10) ||'du monde entier. ','tutorial.aim',2);
INSERT INTO translations VALUES(11,'Choisis une région:'|| char(10) ||' elle détermine le type de recette!','tutorial.region',2);
INSERT INTO translations VALUES(12,'Choisissez 3 ingrédients.'|| char(10) ||'Il y aura 9 options'|| char(10) ||'et tu devras scanner 3 ingrédients,'|| char(10) ||'avant la fin du temps imparti.','tutorial.ingredient',2);
INSERT INTO translations VALUES(13,'Scannez les ingrédients'|| char(10) ||'de votre livre de cuisine'|| char(10) ||'comme à la caisse d''un supermarché.'|| char(10) ||' '|| char(10) ||'Si tu changes d''avis,'|| char(10) ||'scanne un nouveau code-barres pour le remplacer.','tutorial.scan',2);
INSERT INTO translations VALUES(14,'Et c''est tout !'|| char(10) ||' Bon amusement!','tutorial.end',2);

-- Tutorial Italienisch
INSERT INTO translations VALUES(3,'Benvenuti a'|| char(10) ||'Conserve & Cook!','tutorial.intro',3);
INSERT INTO translations VALUES(15,'L''obiettivo è'|| char(10) ||'cucinare piatti deliziosi'|| char(10) ||'da tutto il mondo. ','tutorial.aim',3);
INSERT INTO translations VALUES(16,'La regione determina'|| char(10) ||'quali ricette cucinare!','tutorial.region',3);
INSERT INTO translations VALUES(17,'Scegliere 3 ingredienti.'|| char(10) ||'Ne avrete nove tra cui'|| char(10) ||'scegliere e dovrete scansionarne tre'|| char(10) ||'prima dello scadere del tempo.','tutorial.ingredient',3);
INSERT INTO translations VALUES(18,'Usate lo scanner per selezionare gli ingredienti.'|| char(10) ||'Proprio come alla cassa del supermercato!'|| char(10) ||' '|| char(10) ||'Se si desidera un ingrediente diverso,'|| char(10) ||'è sufficiente scansionarne uno nuovo.','tutorial.scan',3);
INSERT INTO translations VALUES(19,'Ecco fatto!'|| char(10) ||'Divertitevi a cucinare!','tutorial.end',3);

-- Tutorial Englisch
INSERT INTO translations VALUES(20,'Welcome to'|| char(10) ||'Conserve & Cook!','tutorial.intro',4);
INSERT INTO translations VALUES(21,'Your goal is to'|| char(10) ||'cook delicous meals'|| char(10) ||'from all over the world!','tutorial.aim',4);
INSERT INTO translations VALUES(22,'The region determines '|| char(10) ||'which recipes you will cook!','tutorial.region',4);
INSERT INTO translations VALUES(23,'Choose 3 ingredients.'|| char(10) ||'You will be given nine to choose from'|| char(10) ||'and must scan three of them,'|| char(10) ||'before the time runs out.','tutorial.ingredient',4);
INSERT INTO translations VALUES(24,'Use the barcode scanner to select'|| char(10) ||'ingredients from the cookery book.'|| char(10) ||'Just like at the supermarket checkout!'|| char(10) ||' '|| char(10) ||'  If you change your mind,'|| char(10) ||' scan a new one to replace it.','tutorial.scan',4);
INSERT INTO translations VALUES(9,'That’s it!'|| char(10) ||'Have fun cooking!','tutorial.end',4);

-- Region
INSERT INTO translations VALUES(26,'Region Auswählen','region',1);
INSERT INTO translations VALUES(27,'Sélectionner une région','region',2);
INSERT INTO translations VALUES(28,'Selezionare la regione','region',3);
INSERT INTO translations VALUES(23,'Select a Region','region',4);

CREATE TABLE highscore
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
, identifier TEXT);
INSERT INTO ingredient VALUES(0,'Rindfleisch',13300,'Aber Achtung: Rindfleisch verursacht besonders viel CO2 wegen der Methan-Emissionen. Vielleicht findest du eine klimafreundlichere Alternative?','034');
INSERT INTO ingredient VALUES(1,'Poulet',3400,replace('Nur ein kleiner Hinweis: Auch Poulet stammt aus Tierhaltung, was mit CO2 verbunden ist. Vielleicht entdeckst eine noch klimafreundlichere pflanzliche Alternative.\n','\n',char(10)),'031');
INSERT INTO ingredient VALUES(2,'Falafel',2500,'Falafel sind eine super pflanzliche Proteinquelle und oft klimafreundlicher als viele andere Proteinquellen. Weiter so!','014');
INSERT INTO ingredient VALUES(3,'Käse',8500,'Käse ist zwar lecker, hat aber oft einen hohen CO2-Abdruck, weil für die Milchproduktion viel Futter und Wasser benötigt werden. Vielleicht gibt es eine klimafreundlichere Alternative!','022');
INSERT INTO ingredient VALUES(4,'Ananas',15100,'Ananas schmeckt süss und frisch, aber oft wird sie von weit her importiert, was viel Energie für den Transport braucht. Vielleicht gibt es auch eine klimafreundlichere Frucht!','001');
INSERT INTO ingredient VALUES(5,'Speck',4600,replace('Speck ist da ein Klassiker! Allerdings braucht die Verarbeitung von Fleisch mehr Energie. Für’s Klima geht das auch leichter – probier doch mal was Neues!\n\n','\n',char(10)),'005');
INSERT INTO ingredient VALUES(6,'Tomate',200,'Tomaten haben in der Saison einen besonders niedrigen CO2-Abdruck, wenn sie regional angebaut werden. So müssen sie nicht energieintensiv beheizt werden. Weiter so!','044');
INSERT INTO ingredient VALUES(7,'Salat',425,'Salat hat einen niedrigen CO2-Abdruck, wenn er saisonal und regional angebaut wird, da er wenig Energie benötigt. Weiter so!','036');
INSERT INTO ingredient VALUES(8,'Zwiebel',420,'Zwiebeln sind robuste Pflanzen, die in vielen Regionen angebaut werden und lange haltbar sind – das spart Energie bei Transport und Lagerung. Tolle Wahl, weiter so','046');
INSERT INTO ingredient VALUES(9,'Bohnen',1050,'Bohnen sind eine hervorragende Wahl, da sie wenig Wasser und Energie brauchen und den Boden sogar verbessern können. Tolle Entscheidung, weiter so!','008');
INSERT INTO ingredient VALUES(10,'Mais',1500,'Mais wächst schnell und wird in vielen Regionen angebaut, aber er braucht oft viel Wasser und Dünger. Das kann seinen CO2-Abdruck erhöhen. Vielleicht findest du nächstes Mal eine noch klimafreundlichere Zutat!','027');
INSERT INTO ingredient VALUES(16,'Avocado',846,'Avocados sind zwar wasserintensiv, haben aber im Vergleich zu tierischen Produkten oft einen deutlich niedrigeren CO2-Abdruck. Super Wahl, weiter so!','004');
INSERT INTO ingredient VALUES(17,'Knoblauch',415,'Knoblauch hat einen relativ geringen CO2-Abdruck, da er wenig Wasser und Nährstoffe benötigt und gut gelagert werden kann. Weiter so!','024');
INSERT INTO ingredient VALUES(21,'Sahne',7600,'Sahne ist cremig und lecker, hat aber oft einen hohen CO2-Abdruck, weil sie aus Milch hergestellt wird, die viel Futter, Wasser und Energie für die Kühe erfordert. Vielleicht findest du eine leichtere, klimafreundlichere Alternative!','035');
INSERT INTO ingredient VALUES(22,'Erdbeer',300,'Erdbeeren aus der Region sind eine tolle Wahl, weil sie kurze Transportwege haben und oft ohne aufwendige Lagerung auskommen. Super Entscheidung, weiter so!','013');
INSERT INTO ingredient VALUES(23,'Eier',1950,'Eier sind lecker und vielseitig aber ihre Produktion verursacht relativ hohe CO2-Emissionen','012');
INSERT INTO ingredient VALUES(24,'Knoblauchsauce',2800,'Knoblauchsauce gibt deinem Gericht einen tollen Geschmack und ist oft einfacher herzustellen als viele andere Saucen. Gute Wahl, weiter so!','025');
INSERT INTO ingredient VALUES(25,'Banane',110,'Bananen haben einen überraschend niedrigen CO2-Abdruck, weil sie in grossen Mengen per Schiff transportiert werden. Das ist deutlich klimafreundlicher als Flugtransporte. Super Wahl, weiter so!','007');
INSERT INTO ingredient VALUES(27,'Schokolade',13800,'Schokolade schmeckt zwar lecker, hat aber oft einen hohen CO2-Abdruck, weil der Kakao dafür weit transportiert und aufwendig verarbeitet wird. Vielleicht gibt es eine klimafreundlichere Alternative!','038');
INSERT INTO ingredient VALUES(28,'Tofu',2100,'Tofu ist eine tolle pflanzliche Proteinquelle, aber die Sojabohnen dafür werden oft weit transportiert und brauchen viel Wasser. Vielleicht findest du beim nächsten Mal eine noch klimafreundlichere Zutat!','043');
INSERT INTO ingredient VALUES(31,'Brokkoli',500,'Brokkoli ist eine klimafreundliche Wahl, wenn er lokal angebaut wird, da er relativ wenig Energie und Wasser benötigt. Gut gemacht, weiter so!','009');
INSERT INTO ingredient VALUES(32,'Aubergine',410,'Auberginen haben einen vergleichsweise niedrigen CO2-Abdruck, besonders wenn sie regional angebaut werden. Super Wahl, weiter so!','003');
INSERT INTO ingredient VALUES(33,'Bambussprossen',405,'Bambus wächst extrem schnell und braucht nur wenig Wasser, was ihn zu einer guten Wahl fürs Klima macht. Gut gemacht, weiter so!','006');
INSERT INTO ingredient VALUES(34,'Reis',6000,'Reis ist lecker, aber der Anbau braucht viel Wasser und dabei entstehen Gase, die das Klima belasten. Vielleicht findest du eine klimafreundlichere Alternative!','033');
INSERT INTO ingredient VALUES(35,'Nudeln',1300,'Nudeln haben einen relativ niedrigen CO2-Abdruck, besonders wenn sie lokal produziert werden und lange haltbar sind. Super Entscheidung, weiter so!','029');
INSERT INTO ingredient VALUES(36,'Fladenbrot',1450,'Fladenbrot ist einfach und vielseitig. Wenn es frisch gebacken wird, hat es oft einen geringeren CO2-Abdruck als viele verarbeitete Brotsorten. Super Entscheidung, weiter so!','015');
INSERT INTO ingredient VALUES(40,'Heuschrecken',2500,'Heuschrecken sind eine echte Proteinbombe und haben einen deutlich niedrigeren CO2-Abdruck als viele andere tierische Lebensmittel. Mutige Wahl, weiter so!','017');
INSERT INTO ingredient VALUES(41,'Champignon',1300,'Champignons wachsen schnell und benötigen wenig Platz und Wasser, was ihren CO2-Abdruck vergleichsweise niedrig hält. Gut gemacht, weiter so!','010');
INSERT INTO ingredient VALUES(46,'Quinoa',2500,'Quinoa ist ein tolles, nährstoffreiches Getreide, wird aber oft über weite Strecken transportiert. Vielleicht findest du auch eine lokale Alternative. Weiter so!','032');
INSERT INTO ingredient VALUES(47,'Lachs',5100,replace('Lachs ist zwar lecker, aber die Fischzucht oder der Fang in freier Wildbahn verursacht oft hohe CO2-Emissionen. Vielleicht gibt es eine umweltfreundlichere Alternative!\n','\n',char(10)),'026');
INSERT INTO ingredient VALUES(48,'Thunfisch',6000,replace('Ein kleiner Hinweis: Thunfisch wird oft weit draussen im Meer gefangen, und der Fischfang verbraucht viel Energie. Vielleicht gibt’s eine Alternative aus der Nähe?\n\n','\n',char(10)),'042');
INSERT INTO ingredient VALUES(49,'Gurke',402,'Gurken bestehen zu einem Grossteil aus Wasser und brauchen relativ wenig Energie beim Anbau, vor allem, wenn sie regional produziert werden. Super Entscheidung, weiter so!','016');
INSERT INTO ingredient VALUES(50,'Kaviar',10000,'Aber Achtung: Kaviar ist extrem aufwendig in der Herstellung und hat einen sehr hohen CO2-Ausstoss. Fürs Klima wäre etwas Einfacheres besser.','023');
INSERT INTO ingredient VALUES(51,'Karotten',390,'Karotten sind robust und wachsen auch bei kühlem Klima. Das macht sie zu einer der umweltfreundlichsten Gemüsesorten. Gut gemacht, weiter so!','020');
INSERT INTO ingredient VALUES(53,'Ingwer',401,'Obwohl Ingwer oft aus fernen Ländern importiert wird, ist sein CO2-Abdruck überraschend niedrig, weil er in grossen Mengen transportiert wird. Tolle Wahl, weiter so!','018');
INSERT INTO ingredient VALUES(54,'Sojasauce',2100,'Sojasauce gibt vielen Gerichten einen tollen Geschmack, aber sie wird oft aus importierten Sojabohnen hergestellt. Das kann ihren CO2-Abdruck erhöhen. Trotzdem eine gute Wahl!','040');
INSERT INTO ingredient VALUES(55,'Wasabi',1500,'Wasabi ist etwas Besonderes, aber die Pflanze wächst langsam, braucht viel Pflege und Wasser. Das macht sie weniger klimafreundlich. Vielleicht gibt es eine bessere Alternative!','045');
INSERT INTO ingredient VALUES(57,'Sardellen',5000,replace('Sardellen sind zwar klein, aber ihr Fang und Transport können viel Energie kosten. Trotzdem besser als viele andere tierische Lebensmittel. Vielleicht findest du beim nächsten Mal eine noch klimafreundlichere Alternative!\n','\n',char(10)),'037');
INSERT INTO ingredient VALUES(59,'Kartoffel',430,replace('Kartoffeln sind ein Grundnahrungsmittel mit vergleichsweise niedrigem CO2-Abdruck, da sie wenig Dünger und Wasser benötigen. Super Entscheidung, weiter so!\n','\n',char(10)),'021');
INSERT INTO ingredient VALUES(60,'Süsskartoffel',398,'Süsskartoffeln wachsen schnell und brauchen weniger Dünger als viele andere Feldfrüchte. Das macht sie oft klimafreundlicher. Weiter so!','041');
INSERT INTO ingredient VALUES(61,'Maniok',396,'Maniok wächst in warmen Regionen und ist eine robuste Pflanze, die nicht viel Wasser braucht. Obwohl der Transport oft weit ist, ist er in grossen Mengen oft klimafreundlicher als erwartet. Super Entscheidung, weiter so!','028');
INSERT INTO ingredient VALUES(62,'Kapern',388,'Kapern stammen oft aus sonnigen, trockenen Regionen und benötigen wenig Wasser, was ihren CO2-Abdruck vergleichsweise gering hält. Weiter so!','019');
INSERT INTO ingredient VALUES(66,'Schweinefleisch',3250,replace('Schweinefleisch ist sehr beliebt, hat aber oft einen höheren CO2-Abdruck, weil Schweine viel Futter, Wasser und Platz brauchen. Vielleicht findest du beim nächsten Mal eine klimafreundlichere Alternative!\n','\n',char(10)),'039');
INSERT INTO ingredient VALUES(72,'Cocktailsauce',2700,'Cocktailsauce bringt viel Geschmack und braucht oft weniger Zutaten als viele andere Saucen. Super Entscheidung, weiter so!','011');
INSERT INTO ingredient VALUES(79,'Olive',1400,'Olivenbäume sind sehr widerstandsfähig und können in heissen, trockenen Regionen wachsen, was ihren CO2-Abdruck niedrig hält. Super Wahl, weiter so!','030');
INSERT INTO ingredient VALUES(82,'Artischocken',394,'Artischocken sind oft eine gute Wahl, wenn sie aus Europa kommen, da ihr Anbau relativ wenig Energie benötigt. Klasse Entscheidung, weiter so!','002');
INSERT INTO sqlite_sequence VALUES('ingredient_to_recipe',82);
INSERT INTO sqlite_sequence VALUES('ingredient_to_recipe',82);
INSERT INTO sqlite_sequence VALUES('highscore',12);
COMMIT;
