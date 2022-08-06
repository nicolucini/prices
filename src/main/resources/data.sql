DROP TABLE IF EXISTS PRICES;
CREATE TABLE PRICES(
    brand_id bigint,
    start_date datetime,
    end_date datetime,
    price_list bigint,
    product_id bigint,
    priority int,
    price DECIMAL(10,2),
    currency varchar(3)
);
INSERT INTO PRICES (brand_id, start_date, end_date, price_list, product_id, priority, price, currency) VALUES (1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, 35455, 0, '35.50', 'EUR');
INSERT INTO PRICES (brand_id, start_date, end_date, price_list, product_id, priority, price, currency) VALUES (1, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 2, 35455, 1, '25.45', 'EUR');
INSERT INTO PRICES (brand_id, start_date, end_date, price_list, product_id, priority, price, currency) VALUES (1, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 3, 35455, 1, '30.50', 'EUR');
INSERT INTO PRICES (brand_id, start_date, end_date, price_list, product_id, priority, price, currency) VALUES (1, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 4, 35455, 1, '38.95', 'EUR');