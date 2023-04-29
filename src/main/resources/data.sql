-- INSERT INTO customer (id, first_name, last_name, mobile_number) VALUES (1, 'Alan', 'Smith', 9111111111);
-- INSERT INTO customer (id, first_name, last_name, mobile_number) VALUES (2, 'Joe', 'Turing', 9111111112);
-- INSERT INTO customer (id, first_name, last_name, mobile_number) VALUES (3, 'John', 'Smith', 9111111113);
-- INSERT INTO customer (id, first_name, last_name, mobile_number) VALUES (4, 'Kathy', 'Sierra', 9111111114);

INSERT INTO customer (id, first_name, last_name) VALUES (1, 'Alan', 'Smith');
INSERT INTO customer (id, first_name, last_name) VALUES (2, 'Joe', 'Turing');
INSERT INTO customer (id, first_name, last_name) VALUES (3, 'John', 'Smith');
INSERT INTO customer (id, first_name, last_name) VALUES (4, 'Kathy', 'Sierra');

INSERT INTO mobile_number (id, mobile_number) VALUES (1, 9111111111);
INSERT INTO mobile_number (id, mobile_number) VALUES (2, 9111111112);
INSERT INTO mobile_number (id, mobile_number) VALUES (3, 9111111113);
INSERT INTO mobile_number (id, mobile_number) VALUES (4, 9111111114);
INSERT INTO mobile_number (id, mobile_number) VALUES (5, 9111111115);

INSERT INTO mobile_number_customer (mobile_number_id, customer_id) VALUES (1, 1);
INSERT INTO mobile_number_customer (mobile_number_id, customer_id) VALUES (2, 2);
INSERT INTO mobile_number_customer (mobile_number_id, customer_id) VALUES (3, 3);
INSERT INTO mobile_number_customer (mobile_number_id, customer_id) VALUES (4, 4);
INSERT INTO mobile_number_customer (mobile_number_id, customer_id) VALUES (5, 4);
