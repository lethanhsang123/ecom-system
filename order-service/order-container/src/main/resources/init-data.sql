-- INSERT INTO "order".customers (id, username, first_name, last_name)
-- VALUES ('8ff72214-4c25-4242-a945-be7c076b1d6f'::uuid, 'customer 1', 'first name 1', 'last name 1'),
--        ('f5dbf819-7731-4ba6-99b5-a0adf69e801c'::uuid, 'customer 2', 'first name 2', 'last name 2');
--
-- INSERT INTO "order".restaurants (id, "name", active)
-- VALUES ('c059c155-bc8f-435d-bbd1-fa12dfec1901'::uuid, 'restaurant 1', true),
--        ('8a988c9e-89e4-418e-a41e-0f1e2cac713f'::uuid, 'restaurant 2', true);
--
-- INSERT INTO "order".restaurant (product_id, restaurant_id, product_name, product_price, restaurant_active,
--                                restaurant_name)
-- VALUES ('db12bd67-0ab1-404c-adcc-2ee2dd337203'::uuid, 'c059c155-bc8f-435d-bbd1-fa12dfec1901'::uuid, 'product 1', 10000,
--         true, 'restaurant 1'),
--        ('0c618e9d-d392-46ca-91c2-24f3746fea82'::uuid, 'c059c155-bc8f-435d-bbd1-fa12dfec1901'::uuid, 'product 2', 30000,
--         true, 'restaurant 1'),
--        ('96ec8889-29a6-40c1-977e-78a04cfc7b41'::uuid, '8a988c9e-89e4-418e-a41e-0f1e2cac713f'::uuid, 'product 3', 20000,
--         true, 'restaurant 2'),
--        ('381b6149-f728-4ab9-bbfe-8818b44dd26a'::uuid, '8a988c9e-89e4-418e-a41e-0f1e2cac713f'::uuid, 'product 4', 40000,
--         true, 'restaurant 2');

INSERT INTO "order".customers (id, username, first_name, last_name)
VALUES ('69a73039-76bd-4b54-b94c-fc88d985d498'::uuid, 'customer 1', 'first name 1', 'last name 1'),
       ('2d7de17e-6295-41dc-99e2-d125acfc8689'::uuid, 'customer 2', 'first name 2', 'last name 2');
