INSERT INTO market (name) values('Mercado Hackthon');

INSERT INTO item_category (name, optional, daily_portions) VALUES
    ('Protein-rich foods', false, 3)
    ,('Starches', false, 3)
    ,('Fruit & Veg', false, 1)
    ,('Drinks', true, 1)
;


INSERT INTO item (external_id, name, carbon_serving, servings, serving_day, item_category_id) VALUES
         (1,'Farmed fish', 1.87, 1, 3, 1)
        ,(2,'Farmed prawns', 3.4, 1, 3, 1)
        ,(3,'Pork', 1.80, 1, 3, 1)
        ,(4,'Beef', 7.74, 1, 3, 1)
        ,(5,'Lamb', 4.34, 1, 3, 1)
        ,(6,'Nuts', 0.01, 3, 3, 1)
        ,(7,'Chicken', 1.36, 1, 3, 1)
        ,(8,'Eggs', 0.55, 2, 3, 1)
        ,(9,'Beans', 0.10, 1, 3, 1)
        ,(10,'Tofu', 0.15, 1, 3, 1)
        ,(11,'Peas', 0.01, 1, 3, 1)
        ,(12,'Wine', 0.31, 1, 1, 4)
;
