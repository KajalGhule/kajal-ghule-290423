CREATE TABLE customer (
	id BIGINT not null,
	first_name varchar(100) not null,
	last_name varchar(100) not null
);
ALTER TABLE customer ADD CONSTRAINT customer_uk1 UNIQUE (id);

CREATE SEQUENCE CUSTOMER_SEQ START WITH 5 INCREMENT BY 1;

CREATE TABLE mobile_number (
    id BIGINT not null,
    mobile_number varchar(15) not null
);

ALTER TABLE mobile_number ADD CONSTRAINT mobile_number_uk3 UNIQUE (id);
ALTER TABLE mobile_number ADD CONSTRAINT mobile_number_uk1 UNIQUE (mobile_number);

CREATE SEQUENCE MOBILE_NUMBER_SEQ START WITH 6 INCREMENT BY 1;

CREATE TABLE mobile_number_customer (
    mobile_number_id BIGINT not null,
    customer_id BIGINT not null
);

ALTER TABLE mobile_number_customer ADD CONSTRAINT mobile_number_uk2 UNIQUE (mobile_number_id);
ALTER TABLE mobile_number_customer ADD CONSTRAINT `mobile_number_customer_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON UPDATE CASCADE;
ALTER TABLE mobile_number_customer ADD CONSTRAINT `mobile_number_customer_ibfk_2` FOREIGN KEY (`mobile_number_id`) REFERENCES `mobile_number` (`id`) ON UPDATE CASCADE;
