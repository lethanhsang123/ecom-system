DROP SCHEMA IF EXISTS orders CASCADE;

CREATE SCHEMA orders;

CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

DROP TYPE IF EXISTS order_status;
CREATE TYPE order_status AS ENUM ('PENDING', 'PAID', 'APPROVED', 'CANCELLED', 'CANCELLING');

DROP TABLE IF EXISTS orders.orders CASCADE;

CREATE TABLE orders.orders
(
    id               uuid           NOT NULL,
    customer_id      uuid           NOT NULL,
    restaurant_id    uuid           NOT NULL,
    tracking_id      uuid           NOT NULL,
    price            numeric(10, 2) NOT NULL,
    order_status     order_status   NOT NULL,
    failure_messages character varying COLLATE pg_catalog."default",
    CONSTRAINT orders_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS orders.order_items CASCADE;

CREATE TABLE orders.order_items
(
    id         bigint         NOT NULL,
    order_id   uuid           NOT NULL,
    product_id uuid           NOT NULL,
    price      numeric(10, 2) NOT NULL,
    quantity   integer        NOT NULL,
    sub_total  numeric(10, 2) NOT NULL,
    CONSTRAINT order_items_pkey PRIMARY KEY (id, order_id)
);

ALTER TABLE orders.order_items
    ADD CONSTRAINT "FK_ORDER_ID" FOREIGN KEY (order_id)
        REFERENCES orders.orders (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
    NOT VALID;

DROP TABLE IF EXISTS orders.order_address CASCADE;

CREATE TABLE orders.order_address
(
    id          uuid                                           NOT NULL,
    order_id    uuid UNIQUE                                    NOT NULL,
    street      character varying COLLATE pg_catalog."default" NOT NULL,
    postal_code character varying COLLATE pg_catalog."default" NOT NULL,
    city        character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT order_address_pkey PRIMARY KEY (id, order_id)
);

ALTER TABLE orders.order_address
    ADD CONSTRAINT "FK_ORDER_ID" FOREIGN KEY (order_id)
        REFERENCES orders.orders (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
    NOT VALID;

DROP TABLE IF EXISTS orders.customers CASCADE;
CREATE TABLE orders.customers
(
    id         uuid                                           NOT NULL,
    username   character varying COLLATE pg_catalog."default" NOT NULL,
    first_name character varying COLLATE pg_catalog."default" NOT NULL,
    last_name  character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT customers_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS orders.restaurants CASCADE;

CREATE TABLE orders.restaurants
(
    id     uuid                                           NOT NULL,
    name   character varying COLLATE pg_catalog."default" NOT NULL,
    active boolean                                        NOT NULL,
    CONSTRAINT restaurants_pkey PRIMARY KEY (id)
);


INSERT INTO orders.customers (id, username, first_name, last_name)
VALUES ('8ff72214-4c25-4242-a945-be7c076b1d6f'::uuid, 'customer 1', 'first name 1', 'last name 1'),
       ('f5dbf819-7731-4ba6-99b5-a0adf69e801c'::uuid, 'customer 2', 'first name 2', 'last name 2');

INSERT INTO orders.restaurants (id, "name", active)
VALUES ('c059c155-bc8f-435d-bbd1-fa12dfec1901'::uuid, 'restaurant 1', true),
       ('8a988c9e-89e4-418e-a41e-0f1e2cac713f'::uuid, 'restaurant 2', true);

-- INSERT INTO orders.restaurant (product_id, restaurant_id, product_name, product_price, restaurant_active,
--                                restaurant_name)
-- VALUES ('db12bd67-0ab1-404c-adcc-2ee2dd337203'::uuid, 'c059c155-bc8f-435d-bbd1-fa12dfec1901'::uuid, 'product 1', 10000,
--         true, 'restaurant 1'),
--        ('0c618e9d-d392-46ca-91c2-24f3746fea82'::uuid, 'c059c155-bc8f-435d-bbd1-fa12dfec1901'::uuid, 'product 2', 30000,
--         true, 'restaurant 1'),
--        ('96ec8889-29a6-40c1-977e-78a04cfc7b41'::uuid, '8a988c9e-89e4-418e-a41e-0f1e2cac713f'::uuid, 'product 3', 20000,
--         true, 'restaurant 2'),
--        ('381b6149-f728-4ab9-bbfe-8818b44dd26a'::uuid, '8a988c9e-89e4-418e-a41e-0f1e2cac713f'::uuid, 'product 4', 40000,
--         true, 'restaurant 2');


