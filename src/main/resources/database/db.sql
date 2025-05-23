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
INSERT INTO translations VALUES(25,'That’s it!'|| char(10) ||'Have fun cooking!','tutorial.end',4);

-- Region
INSERT INTO translations VALUES(26,'Region Auswählen','region',1);
INSERT INTO translations VALUES(27,'Sélectionner une région','region',2);
INSERT INTO translations VALUES(28,'Selezionare la regione','region',3);
INSERT INTO translations VALUES(29,'Select a Region','region',4);

-- Result
INSERT INTO translations VALUES(30,'Punkte','points',1);
INSERT INTO translations VALUES(31,'Points','points',2);
INSERT INTO translations VALUES(32,'Punti','points',3);
INSERT INTO translations VALUES(33,'Points','points',4);

-- Ingredients
INSERT INTO translations (id, text, language_id, key) VALUES (100, 'Rindfleisch verursacht besonders viel CO2 wegen der Methan-Emissionen. Vielleicht findest du eine klimafreundlichere Alternative?', 1, 'comment.0');
INSERT INTO translations (id, text, language_id, key) VALUES (200, 'Le bœuf produit beaucoup de CO2 à cause des émissions de méthane. Peut-être trouveras-tu une alternative plus écologique ?', 2, 'comment.0');
INSERT INTO translations (id, text, language_id, key) VALUES (300, 'La carne di manzo produce molto CO2 a causa delle emissioni di metano. Forse puoi trovare un''alternativa più ecologica.', 3, 'comment.0');
INSERT INTO translations (id, text, language_id, key) VALUES (400, 'Beef produces a lot of CO2 due to methane emissions. Maybe you can find a more climate-friendly alternative.', 4, 'comment.0');
INSERT INTO translations (id, text, language_id, key) VALUES (101, 'Nur ein kleiner Hinweis: Auch Poulet stammt aus Tierhaltung, was mit CO2 verbunden ist. Vielleicht entdeckst eine noch klimafreundlichere pflanzliche Alternative.', 1, 'comment.1');
INSERT INTO translations (id, text, language_id, key) VALUES (201, 'Juste un petit rappel: le poulet provient également de l’élevage animal, ce qui génère des émissions de CO2. Peut-être qu’une alternative végétale encore plus écologique t’attend ?', 2, 'comment.1');
INSERT INTO translations (id, text, language_id, key) VALUES (301, 'Solo un piccolo promemoria: anche il pollo proviene da allevamenti che generano CO2. Forse puoi trovare un’alternativa vegetale più ecologica.', 3, 'comment.1');
INSERT INTO translations (id, text, language_id, key) VALUES (401, 'Just a quick note: Chicken also comes from animal farming, which produces CO2. Maybe you’ll discover an even more climate-friendly plant-based option?', 4, 'comment.1');
INSERT INTO translations (id, text, language_id, key) VALUES (102, 'Falafel sind eine super pflanzliche Proteinquelle und oft klimafreundlicher als viele andere Proteinquellen. Weiter so!', 1, 'comment.2');
INSERT INTO translations (id, text, language_id, key) VALUES (202, 'Les falafels sont une excellente source de protéines végétales et souvent plus respectueux du climat que d''autres sources. Continue comme ça !', 2, 'comment.2');
INSERT INTO translations (id, text, language_id, key) VALUES (302, 'I falafel sono un''ottima fonte di proteine vegetali e spesso più ecologici di molte altre. Continua così!', 3, 'comment.2');
INSERT INTO translations (id, text, language_id, key) VALUES (402, 'Falafel are a great plant-based protein source and often more climate-friendly than many others. Keep it up!', 4, 'comment.2');
INSERT INTO translations (id, text, language_id, key) VALUES (103, 'Käse ist zwar lecker, hat aber oft einen hohen CO2-Abdruck, weil für die Milchproduktion viel Futter und Wasser benötigt werden. Vielleicht gibt es eine klimafreundlichere Alternative!', 1, 'comment.3');
INSERT INTO translations (id, text, language_id, key) VALUES (203, 'Le fromage est délicieux, mais il a souvent une empreinte CO2 élevée car la production de lait nécessite beaucoup de nourriture et d''eau. Peut-être existe-t-il une alternative plus écologique !', 2, 'comment.3');
INSERT INTO translations (id, text, language_id, key) VALUES (303, 'Il formaggio è buono, ma spesso ha un''alta impronta di CO2 perché la produzione di latte richiede molto cibo e acqua. Forse esiste un''alternativa più ecologica!', 3, 'comment.3');
INSERT INTO translations (id, text, language_id, key) VALUES (403, 'Cheese is tasty, but it often has a high CO2 footprint because milk production requires lots of feed and water. Maybe there''s a more climate-friendly alternative!', 4, 'comment.3');
INSERT INTO translations (id, text, language_id, key) VALUES (104, 'Ananas schmeckt süss und frisch, aber oft wird sie von weit her importiert, was viel Energie für den Transport braucht. Vielleicht gibt es auch eine klimafreundlichere Variante?', 1, 'comment.4');
INSERT INTO translations (id, text, language_id, key) VALUES (204, 'L''ananas est sucrée et rafraîchissante, mais elle est souvent importée de loin, ce qui consomme beaucoup d''énergie. Peut-être existe-t-il un fruit plus écologique !', 2, 'comment.4');
INSERT INTO translations (id, text, language_id, key) VALUES (304, 'L''ananas è dolce e fresca, ma spesso viene importata da molto lontano, il che richiede molta energia. Forse c''è un frutto più ecologico!', 3, 'comment.4');
INSERT INTO translations (id, text, language_id, key) VALUES (404, 'Pineapple is sweet and refreshing, but it’s often imported from far away, which uses a lot of energy. Maybe there’s a more climate-friendly fruit!', 4, 'comment.4');
INSERT INTO translations (id, text, language_id, key) VALUES (105, 'Speck ist da ein Klassiker! Allerdings braucht die Verarbeitung von Fleisch mehr Energie. Für’s Klima geht das auch leichter – probier doch mal was Neues!', 1, 'comment.5');
INSERT INTO translations (id, text, language_id, key) VALUES (205, 'Le bacon est un classique! Mais la transformation de la viande nécessite plus d''énergie. Pour le climat, on peut faire plus simple – essaie autre chose !', 2, 'comment.5');
INSERT INTO translations (id, text, language_id, key) VALUES (305, 'La pancetta è un classico! Ma la lavorazione della carne richiede molta energia. Per il clima si può fare di meglio – prova qualcosa di nuovo!', 3, 'comment.5');
INSERT INTO translations (id, text, language_id, key) VALUES (405, 'Bacon is a classic! But processing meat takes a lot of energy. For the climate, there are easier options – why not try something new?', 4, 'comment.5');
INSERT INTO translations (id, text, language_id, key) VALUES (106, 'Tomaten haben in der Saison einen besonders niedrigen CO2-Abdruck, wenn sie regional angebaut werden. So müssen sie nicht energieintensiv beheizt werden. Weiter so!', 1, 'comment.6');
INSERT INTO translations (id, text, language_id, key) VALUES (206, 'Les tomates de saison ont une empreinte CO2 particulièrement faible lorsqu''elles sont cultivées localement. Pas besoin de serres chauffées ! Continue comme ça !', 2, 'comment.6');
INSERT INTO translations (id, text, language_id, key) VALUES (306, 'I pomodori di stagione hanno un''impronta di CO2 molto bassa se coltivati localmente. Non servono serre riscaldate! Continua così!', 3, 'comment.6');
INSERT INTO translations (id, text, language_id, key) VALUES (406, 'In-season tomatoes have a very low CO2 footprint when grown locally. No need for energy-hungry greenhouses. Keep it up!', 4, 'comment.6');
INSERT INTO translations (id, text, language_id, key) VALUES (107, 'Salat hat einen niedrigen CO2-Abdruck, wenn er saisonal und regional angebaut wird, da er wenig Energie benötigt. Weiter so!', 1, 'comment.7');
INSERT INTO translations (id, text, language_id, key) VALUES (207, 'La laitue a une faible empreinte CO2 si elle est cultivée localement et en saison, car elle demande peu d''énergie. Bien joué !', 2, 'comment.7');
INSERT INTO translations (id, text, language_id, key) VALUES (307, 'La lattuga ha una bassa impronta di CO2 se coltivata localmente e di stagione, perché richiede poca energia. Ben fatto!', 3, 'comment.7');
INSERT INTO translations (id, text, language_id, key) VALUES (407, 'Lettuce has a low CO2 footprint when grown locally and in season, as it needs little energy. Well done!', 4, 'comment.7');
INSERT INTO translations (id, text, language_id, key) VALUES (108, 'Zwiebeln wachsen fast überall und bleiben lange frisch – das spart Energie beim Transport und Lagern. Tolle Wahl, weiter so!', 1, 'comment.8');
INSERT INTO translations (id, text, language_id, key) VALUES (208, 'Les oignons poussent presque partout et restent frais longtemps – cela économise de l’énergie lors du transport et du stockage. Super choix !', 2, 'comment.8');
INSERT INTO translations (id, text, language_id, key) VALUES (308, 'Le cipolle crescono quasi ovunque e restano fresche a lungo – questo fa risparmiare energia per il trasporto e la conservazione. Ottima scelta!', 3, 'comment.8');
INSERT INTO translations (id, text, language_id, key) VALUES (408, 'Onions grow in many places and stay fresh for a long time – this saves energy when transporting and storing them. Great choice, keep it up!', 4, 'comment.8');
INSERT INTO translations (id, text, language_id, key) VALUES (109, 'Bohnen sind eine hervorragende Wahl, da sie wenig Wasser und Energie brauchen und den Boden sogar verbessern können. Tolle Entscheidung, weiter so!', 1, 'comment.9');
INSERT INTO translations (id, text, language_id, key) VALUES (209, 'Les haricots sont un excellent choix car ils nécessitent peu d’eau et d’énergie et peuvent même améliorer la qualité du sol. Très bon choix, continue comme ça !', 2, 'comment.9');
INSERT INTO translations (id, text, language_id, key) VALUES (309, 'I fagioli sono un’ottima scelta perché richiedono poca acqua ed energia e possono persino migliorare il suolo. Ottima decisione, continua così!', 3, 'comment.9');
INSERT INTO translations (id, text, language_id, key) VALUES (409, 'Beans are a great choice as they need little water and energy and can even improve the soil. Great decision, keep it up!', 4, 'comment.9');
INSERT INTO translations (id, text, language_id, key) VALUES (110, 'Mais wächst schnell und wird in vielen Regionen angebaut, aber er braucht oft viel Wasser und Dünger. Das kann seinen CO2-Abdruck erhöhen. Vielleicht findest du nächstes Mal eine noch klimafreundlichere Zutat!', 1, 'comment.10');
INSERT INTO translations (id, text, language_id, key) VALUES (210, 'Le maïs pousse rapidement et est cultivé dans de nombreuses régions, mais il nécessite souvent beaucoup d’eau et d’engrais. Cela peut augmenter son empreinte CO2. Peut-être trouveras-tu une alternative plus écologique la prochaine fois !', 2, 'comment.10');
INSERT INTO translations (id, text, language_id, key) VALUES (310, 'Il mais cresce rapidamente ed è coltivato in molte regioni, ma ha spesso bisogno di molta acqua e fertilizzanti. Questo può aumentare la sua impronta CO2. Forse la prossima volta troverai un’opzione più sostenibile!', 3, 'comment.10');
INSERT INTO translations (id, text, language_id, key) VALUES (410, 'Corn grows fast and is cultivated in many areas, but it often needs lots of water and fertilizer. That can increase its CO2 footprint. Maybe you’ll find a more climate-friendly option next time!', 4, 'comment.10');
INSERT INTO translations (id, text, language_id, key) VALUES (116, 'Avocados brauchen zwar viel Wasser, verursachen aber oft weniger CO2 als viele tierische Lebensmittel. Gute Wahl, weiter so!', 1, 'comment.16');
INSERT INTO translations (id, text, language_id, key) VALUES (216, 'Les avocats ont besoin de beaucoup d’eau, mais produisent souvent moins de CO2 que de nombreux produits animaux. Bon choix, continue comme ça!', 2, 'comment.16');
INSERT INTO translations (id, text, language_id, key) VALUES (316, 'Gli avocado richiedono molta acqua, ma spesso producono meno CO2 rispetto a molti alimenti di origine animale. Buona scelta, continua così!', 3, 'comment.16');
INSERT INTO translations (id, text, language_id, key) VALUES (416, 'Avocados need a lot of water, but their CO2 footprint is often much lower than that of animal products. Great choice, keep it up!', 4, 'comment.16');
INSERT INTO translations (id, text, language_id, key) VALUES (117, 'Knoblauch hat einen relativ geringen CO2-Abdruck, da er wenig Wasser und Nährstoffe benötigt und gut gelagert werden kann. Weiter so!', 1, 'comment.17');
INSERT INTO translations (id, text, language_id, key) VALUES (217, 'L''ail a une faible empreinte CO2 car il nécessite peu d''eau et de nutriments, et se conserve bien. Continue comme ça !', 2, 'comment.17');
INSERT INTO translations (id, text, language_id, key) VALUES (317, 'L''aglio ha una bassa impronta di CO2 perché richiede poca acqua e nutrienti, ed è facile da conservare. Continua così!', 3, 'comment.17');
INSERT INTO translations (id, text, language_id, key) VALUES (417, 'Garlic has a relatively low CO2 footprint because it needs little water or nutrients and stores well. Keep it up!', 4, 'comment.17');
INSERT INTO translations (id, text, language_id, key) VALUES (121, 'Sahne ist cremig und lecker, hat aber oft einen hohen CO2-Abdruck, weil sie aus Milch hergestellt wird, die viel Futter, Wasser und Energie für die Kühe erfordert. Vielleicht findest du eine leichtere, klimafreundlichere Alternative!', 1, 'comment.21');
INSERT INTO translations (id, text, language_id, key) VALUES (221, 'La crème est onctueuse et savoureuse, mais elle a souvent une empreinte CO2 élevée car elle provient du lait, nécessitant beaucoup de ressources. Peut-être existe-t-il une alternative plus légère et écologique ?', 2, 'comment.21');
INSERT INTO translations (id, text, language_id, key) VALUES (321, 'La panna è cremosa e gustosa, ma ha spesso un''impronta CO2 elevata perché deriva dal latte, che richiede molte risorse. Forse puoi trovare un''alternativa più leggera ed ecologica!', 3, 'comment.21');
INSERT INTO translations (id, text, language_id, key) VALUES (421, 'Cream is rich and tasty, but often has a high CO2 footprint because it comes from milk, which needs lots of resources. Maybe there’s a lighter, climate-friendlier alternative!', 4, 'comment.21');
INSERT INTO translations (id, text, language_id, key) VALUES (122, 'Erdbeeren aus der Region sind eine tolle Wahl, weil sie kurze Transportwege haben und oft ohne aufwendige Lagerung auskommen. Super Entscheidung, weiter so!', 1, 'comment.22');
INSERT INTO translations (id, text, language_id, key) VALUES (222, 'Les fraises locales sont un excellent choix car elles nécessitent peu de transport et peu de stockage. Super décision, continue comme ça !', 2, 'comment.22');
INSERT INTO translations (id, text, language_id, key) VALUES (322, 'Le fragole locali sono una scelta eccellente perché richiedono poco trasporto e conservazione. Ottima decisione, continua così!', 3, 'comment.22');
INSERT INTO translations (id, text, language_id, key) VALUES (422, 'Local strawberries are a great choice because they don’t need long transport or storage. Great decision, keep it up!', 4, 'comment.22');
INSERT INTO translations (id, text, language_id, key) VALUES (123, 'Eier sind lecker und vielseitig, aber sie stammen aus Tierhaltung, was meist mit einem höheren CO2-Ausstoss verbunden ist. Vielleicht entdeckst du noch eine pflanzlichere Alternative!', 1, 'comment.23');
INSERT INTO translations (id, text, language_id, key) VALUES (223, 'Les œufs sont savoureux et polyvalents, mais ils proviennent de l’élevage, souvent associé à une empreinte CO2 plus élevée. Peut-être qu’une alternative végétale t’attend ?', 2, 'comment.23');
INSERT INTO translations (id, text, language_id, key) VALUES (323, 'Le uova sono gustose e versatili, ma provengono da allevamenti, spesso con un''impronta CO2 elevata. Forse potresti provare un''alternativa vegetale!', 3, 'comment.23');
INSERT INTO translations (id, text, language_id, key) VALUES (423, 'Eggs are tasty and versatile, but they come from animal farming, which usually means a higher CO2 footprint. Maybe try a plant-based alternative!', 4, 'comment.23');
INSERT INTO translations (id, text, language_id, key) VALUES (124, 'Knoblauchsauce gibt deinem Gericht einen tollen Geschmack und ist oft einfacher herzustellen als viele andere Saucen. Gute Wahl, weiter so!', 1, 'comment.24');
INSERT INTO translations (id, text, language_id, key) VALUES (224, 'La sauce à l''ail donne un excellent goût à ton plat et est souvent plus simple à préparer que d''autres sauces. Bon choix, continue comme ça !', 2, 'comment.24');
INSERT INTO translations (id, text, language_id, key) VALUES (324, 'La salsa all''aglio dà un ottimo sapore ai tuoi piatti ed è spesso più facile da preparare rispetto ad altre salse. Buona scelta, continua così!', 3, 'comment.24');
INSERT INTO translations (id, text, language_id, key) VALUES (424, 'Garlic sauce adds great flavor to your meal and is often easier to make than many other sauces. Good choice, keep it up!', 4, 'comment.24');
INSERT INTO translations (id, text, language_id, key) VALUES (125, 'Bananen haben einen überraschend niedrigen CO2-Abdruck, weil sie in grossen Mengen per Schiff transportiert werden. Das ist deutlich klimafreundlicher als Flugtransporte. Super Wahl, weiter so!', 1, 'comment.25');
INSERT INTO translations (id, text, language_id, key) VALUES (225, 'Les bananes ont une empreinte CO2 étonnamment faible car elles sont transportées en grande quantité par bateau, ce qui est beaucoup plus écologique que l’avion. Super choix, continue comme ça !', 2, 'comment.25');
INSERT INTO translations (id, text, language_id, key) VALUES (325, 'Le banane hanno un''impronta CO2 sorprendentemente bassa perché vengono trasportate in grandi quantità via nave. È molto più ecologico che usare l''aereo. Ottima scelta, continua così!', 3, 'comment.25');
INSERT INTO translations (id, text, language_id, key) VALUES (425, 'Bananas have a surprisingly low CO2 footprint because they’re shipped in large quantities, which is much more climate-friendly than air transport. Great choice, keep it up!', 4, 'comment.25');
INSERT INTO translations (id, text, language_id, key) VALUES (127, 'Schokolade schmeckt zwar lecker, hat aber oft einen hohen CO2-Abdruck, weil der Kakao dafür weit transportiert und aufwendig verarbeitet wird. Vielleicht gibt es eine klimafreundlichere Alternative!', 1, 'comment.27');
INSERT INTO translations (id, text, language_id, key) VALUES (227, 'Le chocolat est délicieux, mais il a souvent une empreinte CO2 élevée car le cacao est transporté sur de longues distances et transformé de manière intensive. Peut-être existe-t-il une alternative plus écologique ?', 2, 'comment.27');
INSERT INTO translations (id, text, language_id, key) VALUES (327, 'Il cioccolato è buono, ma spesso ha un''impronta CO2 elevata perché il cacao viene trasportato da lontano e lavorato intensamente. Forse puoi trovare un''alternativa più sostenibile!', 3, 'comment.27');
INSERT INTO translations (id, text, language_id, key) VALUES (427, 'Chocolate is tasty, but it often has a high CO2 footprint because cocoa is transported far and processed heavily. Maybe there’s a more climate-friendly alternative!', 4, 'comment.27');
INSERT INTO translations (id, text, language_id, key) VALUES (128, 'Tofu ist eine tolle pflanzliche Proteinquelle, aber die Sojabohnen dafür werden oft weit transportiert und brauchen viel Wasser. Vielleicht findest du beim nächsten Mal eine noch klimafreundlichere Zutat!', 1, 'comment.28');
INSERT INTO translations (id, text, language_id, key) VALUES (228, 'Le tofu est une excellente source de protéines végétales, mais les fèves de soja sont souvent importées et nécessitent beaucoup d''eau. Peut-être une alternative plus écologique la prochaine fois ?', 2, 'comment.28');
INSERT INTO translations (id, text, language_id, key) VALUES (328, 'Il tofu è un''ottima fonte di proteine vegetali, ma i fagioli di soia sono spesso trasportati da lontano e richiedono molta acqua. Forse puoi trovare un''opzione più ecologica la prossima volta!', 3, 'comment.28');
INSERT INTO translations (id, text, language_id, key) VALUES (428, 'Tofu is a great plant-based protein, but the soybeans used are often shipped far and need a lot of water. Maybe next time, choose a more climate-friendly option!', 4, 'comment.28');
INSERT INTO translations (id, text, language_id, key) VALUES (131, 'Brokkoli ist eine klimafreundliche Wahl, wenn er lokal angebaut wird, da er relativ wenig Energie und Wasser benötigt. Gut gemacht, weiter so!', 1, 'comment.31');
INSERT INTO translations (id, text, language_id, key) VALUES (231, 'Le brocoli est un bon choix pour le climat s’il est cultivé localement, car il nécessite relativement peu d’énergie et d’eau. Bien joué, continue comme ça !', 2, 'comment.31');
INSERT INTO translations (id, text, language_id, key) VALUES (331, 'Il broccolo è una scelta rispettosa del clima se coltivato localmente, perché richiede relativamente poca energia e acqua. Ben fatto, continua così!', 3, 'comment.31');
INSERT INTO translations (id, text, language_id, key) VALUES (431, 'Broccoli is a climate-friendly choice when grown locally, since it needs relatively little energy and water. Well done, keep it up!', 4, 'comment.31');
INSERT INTO translations (id, text, language_id, key) VALUES (132, 'Auberginen haben einen vergleichsweise niedrigen CO2-Abdruck, besonders wenn sie regional angebaut werden. Super Wahl, weiter so!', 1, 'comment.32');
INSERT INTO translations (id, text, language_id, key) VALUES (232, 'Les aubergines ont une empreinte CO2 relativement faible, surtout si elles sont cultivées localement. Super choix, continue comme ça !', 2, 'comment.32');
INSERT INTO translations (id, text, language_id, key) VALUES (332, 'Le melanzane hanno un''impronta di CO2 relativamente bassa, soprattutto se coltivate localmente. Ottima scelta, continua così!', 3, 'comment.32');
INSERT INTO translations (id, text, language_id, key) VALUES (432, 'Eggplants have a relatively low CO2 footprint, especially when grown locally. Great choice, keep it up!', 4, 'comment.32');
INSERT INTO translations (id, text, language_id, key) VALUES (133, 'Bambus wächst sehr schnell und braucht wenig Wasser, was ihn zu einer guten Wahl fürs Klima macht. Gut gemacht, weiter so!', 1, 'comment.33');
INSERT INTO translations (id, text, language_id, key) VALUES (233, 'Le bambou pousse très vite et nécessite peu d''eau, ce qui en fait un bon choix pour le climat. Bien joué, continue comme ça !', 2, 'comment.33');
INSERT INTO translations (id, text, language_id, key) VALUES (333, 'Il bambù cresce molto rapidamente e richiede poca acqua, rendendolo una buona scelta per il clima. Ben fatto, continua così!', 3, 'comment.33');
INSERT INTO translations (id, text, language_id, key) VALUES (433, 'Bamboo grows very quickly and needs little water, making it a good choice for the climate. Well done, keep it up!', 4, 'comment.33');
INSERT INTO translations (id, text, language_id, key) VALUES (134, 'Reis ist lecker, aber der Anbau braucht viel Wasser und dabei entstehen Gase, die das Klima belasten. Vielleicht findest du eine klimafreundlichere Alternative!', 1, 'comment.34');
INSERT INTO translations (id, text, language_id, key) VALUES (234, 'Le riz est savoureux, mais sa culture nécessite beaucoup d''eau et produit des gaz nocifs pour le climat. Peut-être une alternative plus écologique ?', 2, 'comment.34');
INSERT INTO translations (id, text, language_id, key) VALUES (334, 'Il riso è gustoso, ma la sua coltivazione richiede molta acqua e produce gas dannosi per il clima. Forse un''alternativa più sostenibile?', 3, 'comment.34');
INSERT INTO translations (id, text, language_id, key) VALUES (434, 'Rice is tasty, but growing it needs a lot of water and releases gases that harm the climate. Maybe there’s a more climate-friendly alternative?', 4, 'comment.34');
INSERT INTO translations (id, text, language_id, key) VALUES (135, 'Nudeln haben einen relativ niedrigen CO2-Abdruck, besonders wenn sie lokal produziert werden und lange haltbar sind. Super Entscheidung, weiter so!', 1, 'comment.35');
INSERT INTO translations (id, text, language_id, key) VALUES (235, 'Les pâtes ont une empreinte CO2 relativement faible, surtout si elles sont produites localement et se conservent bien. Super décision, continue comme ça !', 2, 'comment.35');
INSERT INTO translations (id, text, language_id, key) VALUES (335, 'La pasta ha un''impronta CO2 relativamente bassa, soprattutto se è prodotta localmente e ha una lunga conservazione. Ottima decisione, continua così!', 3, 'comment.35');
INSERT INTO translations (id, text, language_id, key) VALUES (435, 'Pasta has a relatively low CO2 footprint, especially if it’s made locally and keeps well. Great decision, keep it up!', 4, 'comment.35');
INSERT INTO translations (id, text, language_id, key) VALUES (136, 'Fladenbrot ist einfach und vielseitig. Wenn es frisch gebacken wird, hat es oft einen geringeren CO2-Abdruck als viele verarbeitete Brotsorten. Super Entscheidung, weiter so!', 1, 'comment.36');
INSERT INTO translations (id, text, language_id, key) VALUES (236, 'Le pain plat est simple et polyvalent. Fraîchement cuit, il a souvent une empreinte CO2 inférieure à celle de nombreux pains transformés. Super choix, continue comme ça !', 2, 'comment.36');
INSERT INTO translations (id, text, language_id, key) VALUES (336, 'Il pane piatto è semplice e versatile. Se cotto fresco, ha spesso un''impronta CO2 più bassa rispetto a molti altri tipi di pane. Ottima scelta, continua così!', 3, 'comment.36');
INSERT INTO translations (id, text, language_id, key) VALUES (436, 'Flatbread is simple and versatile. When freshly baked, it often has a lower CO2 footprint than many processed breads. Great choice, keep it up!', 4, 'comment.36');
INSERT INTO translations (id, text, language_id, key) VALUES (140, 'Heuschrecken sind eine echte Proteinbombe und haben einen deutlich niedrigeren CO2-Abdruck als viele andere tierische Lebensmittel. Mutige Wahl, weiter so!', 1, 'comment.40');
INSERT INTO translations (id, text, language_id, key) VALUES (240, 'Les criquets sont riches en protéines et ont une empreinte CO2 bien plus faible que beaucoup d’autres produits animaux. Un choix courageux, continue comme ça !', 2, 'comment.40');
INSERT INTO translations (id, text, language_id, key) VALUES (340, 'Le cavallette sono una vera bomba di proteine e hanno un’impronta CO2 molto più bassa rispetto a molti altri alimenti animali. Scelta coraggiosa, continua così!', 3, 'comment.40');
INSERT INTO translations (id, text, language_id, key) VALUES (440, 'Grasshoppers are real protein powerhouses and have a much lower CO2 footprint than many other animal products. Brave choice, keep going!', 4, 'comment.40');
INSERT INTO translations (id, text, language_id, key) VALUES (141, 'Champignons wachsen schnell und benötigen wenig Platz und Wasser, was ihren CO2-Abdruck vergleichsweise niedrig hält. Gut gemacht, weiter so!', 1, 'comment.41');
INSERT INTO translations (id, text, language_id, key) VALUES (241, 'Les champignons poussent rapidement et ont besoin de peu d’espace et d’eau, ce qui réduit leur empreinte CO2. Bien joué, continue comme ça !', 2, 'comment.41');
INSERT INTO translations (id, text, language_id, key) VALUES (341, 'I funghi crescono rapidamente e hanno bisogno di poco spazio e acqua, quindi la loro impronta CO2 è bassa. Ben fatto, continua così!', 3, 'comment.41');
INSERT INTO translations (id, text, language_id, key) VALUES (441, 'Mushrooms grow quickly and need little space and water, which keeps their CO2 footprint low. Well done, keep going!', 4, 'comment.41');
INSERT INTO translations (id, text, language_id, key) VALUES (146, 'Quinoa ist ein tolles, nährstoffreiches Getreide, wird aber oft über weite Strecken transportiert. Vielleicht findest du auch eine lokale Alternative. Weiter so!', 1, 'comment.46');
INSERT INTO translations (id, text, language_id, key) VALUES (246, 'Le quinoa est une graine très nutritive, mais il est souvent transporté sur de longues distances. Peut-être existe-t-il une alternative locale ? Continue comme ça !', 2, 'comment.46');
INSERT INTO translations (id, text, language_id, key) VALUES (346, 'La quinoa è un cereale molto nutriente, ma spesso viene trasportata su lunghe distanze. Forse puoi trovare un’alternativa locale. Continua così!', 3, 'comment.46');
INSERT INTO translations (id, text, language_id, key) VALUES (446, 'Quinoa is a great, nutrient-rich grain, but it’s often transported over long distances. Maybe you can find a local alternative. Keep going!', 4, 'comment.46');
INSERT INTO translations (id, text, language_id, key) VALUES (147, 'Lachs ist zwar lecker, aber die Fischzucht oder der Fang in freier Wildbahn verursacht oft hohe CO2-Emissionen. Vielleicht gibt es eine umweltfreundlichere Alternative!', 1, 'comment.47');
INSERT INTO translations (id, text, language_id, key) VALUES (247, 'Le saumon est délicieux, mais l’élevage ou la pêche génère souvent beaucoup d’émissions de CO2. Il existe peut-être une alternative plus écologique !', 2, 'comment.47');
INSERT INTO translations (id, text, language_id, key) VALUES (347, 'Il salmone è gustoso, ma l’allevamento o la pesca in natura generano spesso molte emissioni di CO2. Forse c’è un’alternativa più ecologica!', 3, 'comment.47');
INSERT INTO translations (id, text, language_id, key) VALUES (447, 'Salmon is tasty, but fish farming or wild catching often causes high CO2 emissions. Maybe there’s a more eco-friendly alternative!', 4, 'comment.47');
INSERT INTO translations (id, text, language_id, key) VALUES (148, 'Ein kleiner Hinweis: Thunfisch wird oft weit draussen im Meer gefangen, und der Fischfang verbraucht viel Energie. Vielleicht gibt’s eine Alternative aus der Nähe?', 1, 'comment.48');
INSERT INTO translations (id, text, language_id, key) VALUES (248, 'Petit rappel: le thon est souvent pêché loin en mer, et la pêche consomme beaucoup d’énergie. Peut-être existe-t-il une alternative locale ?', 2, 'comment.48');
INSERT INTO translations (id, text, language_id, key) VALUES (348, 'Un piccolo promemoria: il tonno viene spesso pescato lontano in mare e la pesca consuma molta energia. Forse esiste un’alternativa locale?', 3, 'comment.48');
INSERT INTO translations (id, text, language_id, key) VALUES (448, 'Just a note: Tuna is often caught far out at sea, and fishing uses a lot of energy. Maybe there’s a local alternative?', 4, 'comment.48');
INSERT INTO translations (id, text, language_id, key) VALUES (149, 'Gurken bestehen zu einem Grossteil aus Wasser und brauchen relativ wenig Energie beim Anbau, vor allem, wenn sie regional produziert werden. Super Entscheidung, weiter so!', 1, 'comment.49');
INSERT INTO translations (id, text, language_id, key) VALUES (249, 'Les concombres sont composés en grande partie d''eau et nécessitent relativement peu d''énergie pour pousser, surtout s''ils sont cultivés localement. Super choix, continue comme ça !', 2, 'comment.49');
INSERT INTO translations (id, text, language_id, key) VALUES (349, 'I cetrioli sono composti principalmente da acqua e richiedono poca energia per crescere, soprattutto se coltivati localmente. Ottima scelta, continua così!', 3, 'comment.49');
INSERT INTO translations (id, text, language_id, key) VALUES (449, 'Cucumbers are mostly water and need relatively little energy to grow, especially when produced locally. Great choice, keep it up!', 4, 'comment.49');
INSERT INTO translations (id, text, language_id, key) VALUES (150, 'Aber Achtung: Kaviar ist extrem aufwendig in der Herstellung und hat einen sehr hohen CO2-Ausstoss. Fürs Klima wäre etwas Einfacheres besser.', 1, 'comment.50');
INSERT INTO translations (id, text, language_id, key) VALUES (250, 'Attention: le caviar est très complexe à produire et génère beaucoup d’émissions de CO2. Une alternative plus simple serait meilleure pour le climat.', 2, 'comment.50');
INSERT INTO translations (id, text, language_id, key) VALUES (350, 'Attenzione: il caviale è molto complesso da produrre e ha un''impronta CO2 molto alta. Qualcosa di più semplice sarebbe meglio per il clima.', 3, 'comment.50');
INSERT INTO translations (id, text, language_id, key) VALUES (450, 'Careful: Caviar is very complex to produce and has a very high CO2 output. Something simpler would be better for the climate.', 4, 'comment.50');
INSERT INTO translations (id, text, language_id, key) VALUES (151, 'Karotten sind robust und wachsen auch bei kühlem Klima. Das macht sie zu einer der umweltfreundlichsten Gemüsesorten. Gut gemacht, weiter so!', 1, 'comment.51');
INSERT INTO translations (id, text, language_id, key) VALUES (251, 'Les carottes sont robustes et poussent même dans les climats frais. Cela en fait l’un des légumes les plus écologiques. Bien joué, continue comme ça !', 2, 'comment.51');
INSERT INTO translations (id, text, language_id, key) VALUES (351, 'Le carote sono resistenti e crescono anche in climi freschi. Questo le rende una delle verdure più ecologiche. Ben fatto, continua così!', 3, 'comment.51');
INSERT INTO translations (id, text, language_id, key) VALUES (451, 'Carrots are hardy and grow even in cooler climates. That makes them one of the most eco-friendly vegetables. Well done, keep it up!', 4, 'comment.51');
INSERT INTO translations (id, text, language_id, key) VALUES (153, 'Obwohl Ingwer oft aus fernen Ländern importiert wird, ist sein CO2-Abdruck überraschend niedrig, weil er in grossen Mengen transportiert wird. Tolle Wahl, weiter so!', 1, 'comment.53');
INSERT INTO translations (id, text, language_id, key) VALUES (253, 'Bien que le gingembre soit souvent importé de loin, son empreinte CO2 est étonnamment faible car il est transporté en grande quantité. Super choix, continue comme ça !', 2, 'comment.53');
INSERT INTO translations (id, text, language_id, key) VALUES (353, 'Anche se lo zenzero viene spesso importato da lontano, ha un''impronta CO2 sorprendentemente bassa grazie al trasporto in grandi quantità. Ottima scelta, continua così!', 3, 'comment.53');
INSERT INTO translations (id, text, language_id, key) VALUES (453, 'Even though ginger is often imported from far away, its CO2 footprint is surprisingly low because it''s shipped in large quantities. Great choice, keep it up!', 4, 'comment.53');
INSERT INTO translations (id, text, language_id, key) VALUES (154, 'Sojasauce gibt vielen Gerichten einen tollen Geschmack, aber sie wird oft aus importierten Sojabohnen hergestellt. Das kann ihren CO2-Abdruck erhöhen. Trotzdem eine gute Wahl!', 1, 'comment.54');
INSERT INTO translations (id, text, language_id, key) VALUES (254, 'La sauce soja donne beaucoup de goût aux plats, mais elle est souvent produite à partir de fèves de soja importées, ce qui peut augmenter son empreinte CO2. Un bon choix quand même !', 2, 'comment.54');
INSERT INTO translations (id, text, language_id, key) VALUES (354, 'La salsa di soia dà molto sapore ai piatti, ma spesso è fatta con semi di soia importati, il che può aumentare la sua impronta CO2. Comunque una buona scelta!', 3, 'comment.54');
INSERT INTO translations (id, text, language_id, key) VALUES (454, 'Soy sauce adds great flavor to many dishes, but it’s often made from imported soybeans, which can raise its CO2 footprint. Still a good choice!', 4, 'comment.54');
INSERT INTO translations (id, text, language_id, key) VALUES (155, 'Wasabi ist etwas Besonderes, aber die Pflanze wächst langsam, braucht viel Pflege und Wasser. Das macht sie weniger klimafreundlich. Vielleicht gibt es eine bessere Alternative!', 1, 'comment.55');
INSERT INTO translations (id, text, language_id, key) VALUES (255, 'Le wasabi est spécial, mais il pousse lentement et nécessite beaucoup de soins et d’eau. Cela le rend moins respectueux du climat. Peut-être existe-t-il une meilleure alternative ?', 2, 'comment.55');
INSERT INTO translations (id, text, language_id, key) VALUES (355, 'Il wasabi è particolare, ma cresce lentamente e ha bisogno di molte cure e acqua. Questo lo rende meno rispettoso del clima. Forse c’è un’alternativa migliore!', 3, 'comment.55');
INSERT INTO translations (id, text, language_id, key) VALUES (455, 'Wasabi is special, but it grows slowly and needs a lot of care and water. That makes it less climate-friendly. Maybe there’s a better alternative!', 4, 'comment.55');
INSERT INTO translations (id, text, language_id, key) VALUES (157, 'Sardellen sind zwar klein, aber ihr Fang und Transport können viel Energie kosten. Vielleicht findest du beim nächsten Mal eine noch klimafreundlichere Alternative!', 1, 'comment.57');
INSERT INTO translations (id, text, language_id, key) VALUES (257, 'Les anchois sont petits, mais leur pêche et leur transport peuvent consommer beaucoup d’énergie. Peut-être trouveras-tu une alternative plus écologique la prochaine fois !', 2, 'comment.57');
INSERT INTO translations (id, text, language_id, key) VALUES (357, 'Le acciughe sono piccole, ma la pesca e il trasporto possono richiedere molta energia. Forse la prossima volta sceglierai un’alternativa più ecologica!', 3, 'comment.57');
INSERT INTO translations (id, text, language_id, key) VALUES (457, 'Anchovies may be small, but catching and transporting them can use a lot of energy. Maybe next time you’ll pick a more climate-friendly alternative!', 4, 'comment.57');
INSERT INTO translations (id, text, language_id, key) VALUES (159, 'Kartoffeln sind sehr beliebt und gut fürs Klima, weil sie beim Anbauen wenig Wasser und Dünger brauchen. Super Entscheidung, weiter so!', 1, 'comment.59');
INSERT INTO translations (id, text, language_id, key) VALUES (259, 'Les pommes de terre sont très appréciées et bonnes pour le climat, car elles nécessitent peu d’eau et d’engrais pour pousser. Super choix, continue comme ça!', 2, 'comment.59');
INSERT INTO translations (id, text, language_id, key) VALUES (359, 'Le patate sono molto amate e fanno bene al clima perché crescono con poca acqua e pochi fertilizzanti. Ottima scelta, continua così!', 3, 'comment.59');
INSERT INTO translations (id, text, language_id, key) VALUES (459, 'Potatoes are really popular and good for the climate because they grow with little water and fertilizer. Great choice, keep it up!', 4, 'comment.59');
INSERT INTO translations (id, text, language_id, key) VALUES (160, 'Süsskartoffeln wachsen schnell und brauchen weniger Dünger als viele andere Feldfrüchte. Das macht sie oft klimafreundlicher. Weiter so!', 1, 'comment.60');
INSERT INTO translations (id, text, language_id, key) VALUES (260, 'Les patates douces poussent rapidement et nécessitent moins d’engrais que d’autres cultures. Cela les rend souvent plus écologiques. Continue comme ça !', 2, 'comment.60');
INSERT INTO translations (id, text, language_id, key) VALUES (360, 'Le patate dolci crescono rapidamente e hanno bisogno di meno fertilizzanti rispetto ad altre colture. Per questo sono spesso più ecologiche. Continua così!', 3, 'comment.60');
INSERT INTO translations (id, text, language_id, key) VALUES (460, 'Sweet potatoes grow quickly and need less fertilizer than many other crops. That makes them often more climate-friendly. Keep going!', 4, 'comment.60');
INSERT INTO translations (id, text, language_id, key) VALUES (161, 'Maniok wächst in warmen Regionen und ist eine robuste Pflanze, die nicht viel Wasser braucht. Obwohl der Transport weit ist, ist er in grossen Mengen klimafreundlicher als man denkt. Super Entscheidung, weiter so!', 1, 'comment.61');
INSERT INTO translations (id, text, language_id, key) VALUES (261, 'Le manioc pousse dans les régions chaudes et est une plante robuste qui nécessite peu d’eau. Même s’il vient de loin, son transport en grande quantité est plus écologique qu’on ne le pense. Super choix !', 2, 'comment.61');
INSERT INTO translations (id, text, language_id, key) VALUES (361, 'La manioca cresce nelle regioni calde ed è una pianta resistente che ha bisogno di poca acqua. Anche se viene da lontano, il trasporto in grandi quantità è più ecologico di quanto si pensi. Ottima scelta!', 3, 'comment.61');
INSERT INTO translations (id, text, language_id, key) VALUES (461, 'Cassava grows in warm regions and is a sturdy plant that doesn’t need much water. Even though it travels far, large shipments make it more climate-friendly than expected. Great choice!', 4, 'comment.61');
INSERT INTO translations (id, text, language_id, key) VALUES (162, 'Kapern stammen oft aus sonnigen, trockenen Regionen und benötigen wenig Wasser, was ihren CO2-Abdruck vergleichsweise gering hält. Weiter so!', 1, 'comment.62');
INSERT INTO translations (id, text, language_id, key) VALUES (262, 'Les câpres viennent souvent de régions ensoleillées et sèches et nécessitent peu d’eau, ce qui leur donne une faible empreinte CO2. Continue comme ça !', 2, 'comment.62');
INSERT INTO translations (id, text, language_id, key) VALUES (362, 'I capperi provengono spesso da regioni soleggiate e secche e richiedono poca acqua, il che li rende ecologici. Continua così!', 3, 'comment.62');
INSERT INTO translations (id, text, language_id, key) VALUES (462, 'Capers often come from sunny, dry regions and need little water, which keeps their CO2 footprint relatively low. Keep it up!', 4, 'comment.62');
INSERT INTO translations (id, text, language_id, key) VALUES (166, 'Schweinefleisch ist sehr beliebt, hat aber oft einen höheren CO2-Abdruck, weil Schweine viel Futter, Wasser und Platz brauchen. Vielleicht findest du beim nächsten Mal eine klimafreundlichere Alternative!', 1, 'comment.66');
INSERT INTO translations (id, text, language_id, key) VALUES (266, 'La viande de porc est très populaire, mais elle a souvent une empreinte CO2 élevée car les porcs nécessitent beaucoup de nourriture, d’eau et d’espace. Peut-être qu’une alternative plus écologique conviendrait mieux !', 2, 'comment.66');
INSERT INTO translations (id, text, language_id, key) VALUES (366, 'La carne di maiale è molto amata, ma ha spesso un’impronta CO2 elevata perché i maiali richiedono molto cibo, acqua e spazio. Forse c’è un’alternativa più sostenibile!', 3, 'comment.66');
INSERT INTO translations (id, text, language_id, key) VALUES (466, 'Pork is very popular, but it often has a high CO2 footprint because pigs need lots of feed, water, and space. Maybe there’s a more climate-friendly option next time!', 4, 'comment.66');
INSERT INTO translations (id, text, language_id, key) VALUES (172, 'Cocktailsauce bringt viel Geschmack und braucht oft weniger Zutaten als viele andere Saucen. Super Entscheidung, weiter so!', 1, 'comment.72');
INSERT INTO translations (id, text, language_id, key) VALUES (272, 'La sauce cocktail apporte beaucoup de goût et nécessite souvent moins d’ingrédients que d’autres sauces. Super choix, continue comme ça !', 2, 'comment.72');
INSERT INTO translations (id, text, language_id, key) VALUES (372, 'La salsa cocktail dà molto sapore e spesso richiede meno ingredienti rispetto ad altre salse. Ottima scelta, continua così!', 3, 'comment.72');
INSERT INTO translations (id, text, language_id, key) VALUES (472, 'Cocktail sauce adds lots of flavor and often needs fewer ingredients than many other sauces. Great choice, keep it up!', 4, 'comment.72');
INSERT INTO translations (id, text, language_id, key) VALUES (179, 'Oliven wachsen an Bäumen, die wenig Wasser brauchen und gut in der Sonne gedeihen. Das ist gut fürs Klima. Super gemacht, weiter so', 1, 'comment.79');
INSERT INTO translations (id, text, language_id, key) VALUES (279, 'Les olives poussent sur des arbres qui n’ont pas besoin de beaucoup d’eau et aiment le soleil. C’est bon pour le climat. Bien joué, continue comme ça!', 2, 'comment.79');
INSERT INTO translations (id, text, language_id, key) VALUES (379, 'Le olive crescono su alberi che hanno bisogno di poca acqua e amano il sole. Fa bene al clima. Ben fatto, continua così!', 3, 'comment.79');
INSERT INTO translations (id, text, language_id, key) VALUES (479, 'Olives grow on trees that don’t need much water and love the sun. That’s good for the climate. Well done, keep it up!', 4, 'comment.79');
INSERT INTO translations (id, text, language_id, key) VALUES (182, 'Artischocken wachsen in Europa und brauchen beim Anbau nicht viel Energie. Das ist gut fürs Klima. Klasse Entscheidung, weiter so', 1, 'comment.82');
INSERT INTO translations (id, text, language_id, key) VALUES (282, 'Les artichauts poussent en Europe et n’ont pas besoin de beaucoup d’énergie pour pousser. C’est bon pour le climat. Super choix, continue comme ça!', 2, 'comment.82');
INSERT INTO translations (id, text, language_id, key) VALUES (382, 'I carciofi crescono in Europa e non hanno bisogno di molta energia per crescere. Fa bene al clima. Ottima scelta, continua così!', 3, 'comment.82');
INSERT INTO translations (id, text, language_id, key) VALUES (482, 'Artichokes grow in Europe and don’t need much energy to grow. That’s good for the climate. Great choice, keep it up!', 4, 'comment.82');

CREATE TABLE highscore
(
    score INTEGER not null,
    name  TEXT,
    id    integer not null
        constraint highscore_pk
            primary key autoincrement
);
CREATE TABLE ingredient
(
    id         INTEGER PRIMARY KEY,
    title      CHAR(255),
    co2_score  INTEGER,
    identifier TEXT
);
INSERT INTO ingredient VALUES(0,'Rindfleisch',13300,'034');
INSERT INTO ingredient VALUES(1,'Poulet',3400,'031');
INSERT INTO ingredient VALUES(2,'Falafel',2500,'014');
INSERT INTO ingredient VALUES(3,'Käse',8500,'022');
INSERT INTO ingredient VALUES(4,'Ananas',15100,'001');
INSERT INTO ingredient VALUES(5,'Speck',4600,'005');
INSERT INTO ingredient VALUES(6,'Tomate',200,'044');
INSERT INTO ingredient VALUES(7,'Salat',425,'036');
INSERT INTO ingredient VALUES(8,'Zwiebel',420,'046');
INSERT INTO ingredient VALUES(9,'Bohnen',1050,'008');
INSERT INTO ingredient VALUES(10,'Mais',1500,'027');
INSERT INTO ingredient VALUES(16,'Avocado',846,'004');
INSERT INTO ingredient VALUES(17,'Knoblauch',415,'024');
INSERT INTO ingredient VALUES(21,'Sahne',7600,'035');
INSERT INTO ingredient VALUES(22,'Erdbeer',300,'013');
INSERT INTO ingredient VALUES(23,'Eier',1950,'012');
INSERT INTO ingredient VALUES(24,'Knoblauchsauce',2800,'025');
INSERT INTO ingredient VALUES(25,'Banane',110,'007');
INSERT INTO ingredient VALUES(27,'Schokolade',13800,'038');
INSERT INTO ingredient VALUES(28,'Tofu',2100,'043');
INSERT INTO ingredient VALUES(31,'Brokkoli',500,'009');
INSERT INTO ingredient VALUES(32,'Aubergine',410,'003');
INSERT INTO ingredient VALUES(33,'Bambussprossen',405,'006');
INSERT INTO ingredient VALUES(34,'Reis',6000,'033');
INSERT INTO ingredient VALUES(35,'Nudeln',1300,'029');
INSERT INTO ingredient VALUES(36,'Fladenbrot',1450,'015');
INSERT INTO ingredient VALUES(40,'Heuschrecken',2500,'017');
INSERT INTO ingredient VALUES(41,'Champignon',1300,'010');
INSERT INTO ingredient VALUES(46,'Quinoa',2500,'032');
INSERT INTO ingredient VALUES(47,'Lachs',5100,'026');
INSERT INTO ingredient VALUES(48,'Thunfisch',6000,'042');
INSERT INTO ingredient VALUES(49,'Gurke',402,'016');
INSERT INTO ingredient VALUES(50,'Kaviar',10000,'023');
INSERT INTO ingredient VALUES(51,'Karotten',390,'020');
INSERT INTO ingredient VALUES(53,'Ingwer',401,'018');
INSERT INTO ingredient VALUES(54,'Sojasauce',2100,'040');
INSERT INTO ingredient VALUES(55,'Wasabi',1500,'045');
INSERT INTO ingredient VALUES(57,'Sardellen',5000,'037');
INSERT INTO ingredient VALUES(59,'Kartoffel',430,'021');
INSERT INTO ingredient VALUES(60,'Süsskartoffel',398,'041');
INSERT INTO ingredient VALUES(61,'Maniok',396,'028');
INSERT INTO ingredient VALUES(62,'Kapern',388,'019');
INSERT INTO ingredient VALUES(66,'Schweinefleisch',3250,'039');
INSERT INTO ingredient VALUES(72,'Cocktailsauce',2700,'011');
INSERT INTO ingredient VALUES(79,'Olive',1400,'030');
INSERT INTO ingredient VALUES(82,'Artischocken',394,'002');
COMMIT;
