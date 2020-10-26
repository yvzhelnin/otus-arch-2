INSERT INTO warehouse.brand(name)
VALUES ('Atomic');
INSERT INTO warehouse.brand(name)
VALUES ('Salomon');
INSERT INTO warehouse.brand(name)
VALUES ('Rossignol');
INSERT INTO warehouse.brand(name)
VALUES ('GT');
INSERT INTO warehouse.brand(name)
VALUES ('El-sport');

INSERT INTO warehouse.model(article, brand_code, name, book_value, equipment_category)
VALUES ('R8HK8WFCKF', 1, 'Atomic PRO S1 RUS', 9000, 'CROSS_COUNTRY_SKIING');
INSERT INTO warehouse.model(article, brand_code, name, book_value, equipment_category)
VALUES ('GLCGLAEIQC', 1, 'Atomic PRO S2 RUS', 10000, 'CROSS_COUNTRY_SKIING');
INSERT INTO warehouse.model(article, brand_code, name, book_value, equipment_category)
VALUES ('ZJ074LVI3R', 1, 'Atomic REDSTER S5', 11000, 'CROSS_COUNTRY_SKIING');
INSERT INTO warehouse.model(article, brand_code, name, book_value, equipment_category)
VALUES ('0JMM65DP3L', 1, 'Atomic PRO CS1', 12000, 'CROSS_COUNTRY_SKIING');
INSERT INTO warehouse.model(article, brand_code, name, book_value, equipment_category)
VALUES ('H298RV94Q3', 1, 'Atomic REDSTER S9 med KG', 13000, 'CROSS_COUNTRY_SKIING');
INSERT INTO warehouse.model(article, brand_code, name, book_value, equipment_category)
VALUES ('05Z8V4XYO4', 1, 'Atomic REDSTER C7 SKINTEC', 14000, 'CROSS_COUNTRY_SKIING');
INSERT INTO warehouse.model(article, brand_code, name, book_value, equipment_category)
VALUES ('8X0OIPLQFO', 2, 'Salomon RS SKATE', 15000, 'CROSS_COUNTRY_SKIING');

INSERT INTO warehouse.model(article, brand_code, name, book_value, equipment_category)
VALUES ('RIHW440', 3, 'Rossignol X-2 FW', 3000, 'CROSS_COUNTRY_SKIING_BOOTS');
INSERT INTO warehouse.model(article, brand_code, name, book_value, equipment_category)
VALUES ('RIGW180', 3, 'Rossignol X-5 OT', 4000, 'CROSS_COUNTRY_SKIING_BOOTS');
INSERT INTO warehouse.model(article, brand_code, name, book_value, equipment_category)
VALUES ('RIH0010', 3, 'Rossignol X-IUM CARBON PREMIUM SKATE', 5000, 'CROSS_COUNTRY_SKIING_BOOTS');

INSERT INTO warehouse.model(article, brand_code, name, book_value, equipment_category)
VALUES ('RAIJY01', 3, 'Rossignol FUN GIRL +XPRESS 7 GW', 30000, 'MOUNTAIN_SKIING');
INSERT INTO warehouse.model(article, brand_code, name, book_value, equipment_category)
VALUES ('RAJJY03', 3, 'Rossignol HERO JR +XPRESS 7 GW', 40000, 'MOUNTAIN_SKIING');
INSERT INTO warehouse.model(article, brand_code, name, book_value, equipment_category)
VALUES ('RAJJY02', 3, 'Rossignol HERO JR +KID 4 GW', 45000, 'MOUNTAIN_SKIING');
INSERT INTO warehouse.model(article, brand_code, name, book_value, equipment_category)
VALUES ('RAJJC02', 3, 'Rossignol EXPERIENCE PRO W +XPRESS 7 GW', 47000, 'MOUNTAIN_SKIING');
INSERT INTO warehouse.model(article, brand_code, name, book_value, equipment_category)
VALUES ('AASS02454', 1, 'Atomic CLOUD 9 + M 10 GW', 48000, 'MOUNTAIN_SKIING');
INSERT INTO warehouse.model(article, brand_code, name, book_value, equipment_category)
VALUES ('RAJLJK2', 3, 'Rossignol REACT 6 COMPACT +XPRESS 11 GW', 49000, 'MOUNTAIN_SKIING');

INSERT INTO warehouse.model(article, brand_code, name, book_value, equipment_category)
VALUES ('AE5022859', 1, 'Atomic HAWX MAGNA 85 W', 20000, 'MOUNTAIN_SKIING_BOOTS');
INSERT INTO warehouse.model(article, brand_code, name, book_value, equipment_category)
VALUES ('AE5022860', 1, 'Atomic HAWX MAGNA 100', 25000, 'MOUNTAIN_SKIING_BOOTS');
INSERT INTO warehouse.model(article, brand_code, name, book_value, equipment_category)
VALUES ('AE5022340', 1, 'Atomic HAWX PRIME 120 S', 30000, 'MOUNTAIN_SKIING_BOOTS');

INSERT INTO warehouse.model(article, brand_code, name, book_value, equipment_category)
VALUES ('G28300M20', 4, 'GT AGGRESSOR 27 SPORT', 30000, 'BICYCLE');
INSERT INTO warehouse.model(article, brand_code, name, book_value, equipment_category)
VALUES ('G27300M10', 4, 'GT AVALANCHE 27 COMP', 40000, 'BICYCLE');
INSERT INTO warehouse.model(article, brand_code, name, book_value, equipment_category)
VALUES ('G27200M10', 4, 'GT AVALANCHE 29 ELITE', 50000, 'BICYCLE');

INSERT INTO warehouse.model(article, brand_code, name, book_value, equipment_category)
VALUES ('ESC120W', 5, 'El-sport Charger 120W', 10000, 'SCOOTER');
INSERT INTO warehouse.model(article, brand_code, name, book_value, equipment_category)
VALUES ('ESC500W', 5, 'El-Sport T8 500W 48V / 10Ah', 15000, 'SCOOTER');
INSERT INTO warehouse.model(article, brand_code, name, book_value, equipment_category)
VALUES ('EST9600W', 5, 'El-Sport T9 600W', 20000, 'SCOOTER');

DO
$$
    DECLARE
        cur_model RECORD;
    BEGIN
        FOR cur_model IN SELECT article FROM warehouse.model
            LOOP
                current_article := cur.article;

                FOR i IN 1..10 LOOP
                    EXECUTE 'INSERT INTO warehouse.equipment (model_article) ' ||
                            'VALUES (''' || current_article || ''')';
                END LOOP;
            END LOOP;
    END
$$;