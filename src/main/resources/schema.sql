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