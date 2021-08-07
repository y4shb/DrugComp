DROP DATABASE IF EXISTS medicines;
CREATE DATABASE medicines;
use medicines;

DROP TABLE IF EXISTS compositions;
CREATE TABLE compositions (
    id int,
    name varchar(255),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS ingredients;
CREATE TABLE ingredients (
    id int,
    name varchar(255),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS molecules;
CREATE TABLE molecules (
    id int,
    name varchar(255),
    rx_required boolean,

    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS composition_ingredients;
CREATE TABLE composition_ingredients (
    id int,
    composition_id int,
    ingredient_id int,
    unit varchar(255),
    strength float,

    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS molecule_ingredients;
CREATE TABLE molecule_ingredients (
    id int,
    molecule_id int,
    ingredient_id int,

    PRIMARY KEY (id)
);

INSERT INTO ingredients (id, name) VALUES (1, "paracetamol");
INSERT INTO ingredients (id, name) VALUES (2, "diclofenac");
INSERT INTO molecules (id, name, rx_required) VALUES (1, "paracetamol + diclofenac", true);
INSERT INTO molecule_ingredients (id, molecule_id, ingredient_id) VALUES (1, 1, 1);
INSERT INTO molecule_ingredients (id, molecule_id, ingredient_id) VALUES (2, 1, 2);


INSERT INTO compositions (id, name) VALUES (1, "paracetamol (200 MG) + diclofenac (5 MGG)");
INSERT INTO composition_ingredients (id, composition_id, ingredient_id, unit, strength) VALUES (1, 1, 1, "MG", 200);
INSERT INTO composition_ingredients (id, composition_id, ingredient_id, unit, strength) VALUES (2, 1, 2, "MGG", 5);

INSERT INTO compositions (id, name) VALUES (2, "paracetamol (500 MG) + diclofenac (5 MGG)");
INSERT INTO composition_ingredients (id, composition_id, ingredient_id, unit, strength) VALUES (3, 2, 1, "MG", 500);
INSERT INTO composition_ingredients (id, composition_id, ingredient_id, unit, strength) VALUES (4, 2, 2, "MGG", 5);

INSERT INTO compositions (id, name) VALUES (3, "paracetamol (500 MG) + diclofenac (25 MGG)");
INSERT INTO composition_ingredients (id, composition_id, ingredient_id, unit, strength) VALUES (5, 3, 1, "MG", 500);
INSERT INTO composition_ingredients (id, composition_id, ingredient_id, unit, strength) VALUES (6, 3, 2, "MGG", 25);

# Give all compositions where paracetamol is 500 MG
SELECT * FROM compositions INNER JOIN composition_ingredients
ON compositions.id = composition_ingredients.composition_id
WHERE (ingredient_id=1 AND strength=500);

# Give all compositions where paracetamol is 200 MG and rx_required is false
SELECT * FROM compositions 
INNER JOIN composition_ingredients ON compositions.id = composition_ingredients.composition_id
INNER JOIN molecule_ingredients ON composition_ingredients.ingredient_id = molecule_ingredients.ingredient_id
INNER JOIN molecules ON molecule_ingredients.molecule_id = molecules.id
WHERE (molecule_ingredients.ingredient_id=1 AND strength=200 AND molecules.rx_required = false);

SELECT * FROM molecule_ingredients;
SELECT * FROM composition_ingredients;
SELECT * FROM molecules;
SELECT * FROM compositions;
SELECT * FROM ingredients;