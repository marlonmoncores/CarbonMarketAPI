INSERT INTO market (name) VALUES
('Lanchonete Hackthon');

INSERT INTO item_category (id, name, optional, daily_portions) VALUES
    (1, 'Protein-rich foods', false, 3)
    ,(2, 'Starches', false, 3)
    ,(3, 'Fruit & Veg', false, 1)
    ,(4, 'Drinks', true, 1)
;

INSERT INTO item (id, external_id, name, carbon_serving, servings, serving_day, item_category_id, water_serving) VALUES
         (1,83044,'Farmed fish', 1.87, 1, 3, 1, 508)
        ,(2,83045,'Farmed prawns', 3.4, 1, 3, 1, 3.4)
        ,(3,83046,'Pork', 1.80, 1, 3, 1, 263)
        ,(4,83047,'Beef', 7.74, 1, 3, 1, 220)
        ,(5,83048,'Lamb', 4.34, 1, 3, 1, 197)
        ,(6,83049,'Nuts', 0.01, 3, 3, 1, 133)
        ,(7,83050,'Chicken', 1.36, 1, 3, 1, 91)
        ,(8,83051,'Eggs', 0.55, 2, 3, 1, 68)
        ,(9,83052,'Beans', 0.10, 1, 3, 1,24)
        ,(10,83053,'Tofu', 0.15, 1, 3, 1,7)
        ,(11,83054,'Peas', 0.01, 1, 3, 1,7)
        ,(12,83055,'Rice', 0.33, 1, 3, 2, 168)
        ,(13,71021,'Bread', 0.05, 1, 3, 2, 24)
        ,(14,71022,'Oatmeal', 0.10, 1, 3, 2, 20)
        ,(15,71023,'Potatoes', 0.04, 1, 3, 2,6)
        ,(16,71024,'Avocado', 0.19, 1, 3, 1, 45)
        ,(17,964153,'Berries and grapes', 0.12, 3, 3, 1, 33)
        ,(18,969877,'Tomatoes', 0.16, 1, 3, 1, 29)
        ,(19,969912,'Apples', 0.03, 2, 3, 1,14)
        ,(20,964152,'Bananas', 0.06, 1, 3, 1,9)
        ,(21,71029,'Citrus fruit', 0.03, 1, 3, 1,6)
        ,(22,9738,'Wine', 0.31, 1, 1, 4,13)
        ,(23,9739,'Beer', 0.67, 1, 3, 1,9)
        ,(24,9731,'Coffee', 0.42, 1, 3, 1,0.38)
        ,(25,9732,'Tea', 0.04, 1, 3, 1, 0.04)
;

