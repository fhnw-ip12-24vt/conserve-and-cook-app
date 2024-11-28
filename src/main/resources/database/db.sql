CREATE TABLE languages
(
    id INT NOT NULL,
    name VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE translations
(
    id INT NOT NULL,
    text VARCHAR(255) NOT NULL,
    language_id INT NOT NULL,
    FOREIGN KEY (language_id) REFERENCES languages(id)
);

CREATE TABLE regions
(
    id INT NOT NULL,
    title VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE recipe
(
    id INT NOT NULL,
    region_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (region_id) REFERENCES regions(id)
);

CREATE TABLE ingredient
(
    id INT NOT NULL,
    title CHAR(255) NOT NULL,
    image VARCHAR(255) NOT NULL,
    co2_score INT NOT NULL,
    water_score INT NOT NULL,
    transport_score INT NOT NULL,
    region_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (region_id) REFERENCES regions(id)
);

CREATE TABLE ingredient_to_recipe
(
    id INT NOT NULL,
    recipe_id INT NOT NULL,
    ingredient_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (recipe_id) REFERENCES recipe(id),
    FOREIGN KEY (ingredient_id) REFERENCES ingredient(id)
);

INSERT INTO regions (id, title) VALUES (1, 'europe');
INSERT INTO regions (id, title) VALUES (2, 'asia');
INSERT INTO regions (id, title) VALUES (3, 'africa');
INSERT INTO regions (id, title) VALUES (4, 'america');

INSERT INTO languages (id, name) VALUES (1, 'deutsch');
INSERT INTO languages (id, name) VALUES (2, 'französisch');
INSERT INTO languages (id, name) VALUES (3, 'english');
INSERT INTO languages (id, name) VALUES (4, 'italienisch');
