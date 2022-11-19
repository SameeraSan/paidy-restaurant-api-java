
-- A Master table for holding status codes
CREATE TABLE IF NOT EXISTS public.STATUS
(
    code integer NOT NULL ,
    description character varying(100) NOT NULL,
    create_date TIMESTAMP DEFAULT now(),
    CONSTRAINT STATUS_pkey PRIMARY KEY (code)
);

-- Tables that restaurant has
CREATE TABLE IF NOT EXISTS public.tables
(
    id bigint NOT NULL ,
    name character varying(100) NOT NULL,
    status integer DEFAULT 1,
	capacity integer DEFAULT 10,
    CONSTRAINT tables_pkey PRIMARY KEY (id)
);

ALTER TABLE tables ADD CONSTRAINT fk_tables_status 
FOREIGN KEY (status) REFERENCES status (code);

-- Items that are available in the restaurant
CREATE TABLE IF NOT EXISTS public.ITEMS
(
    id bigint NOT NULL ,
    name character varying(100) NOT NULL,
    status integer DEFAULT 1,
	price NUMERIC(5, 2),
    CONSTRAINT items_pkey PRIMARY KEY (id)
);

ALTER TABLE ITEMS ADD CONSTRAINT fk_items_status 
FOREIGN KEY (status) REFERENCES status (code);

-- order table to hold items of tables
CREATE TABLE IF NOT EXISTS public.ORDERS
(
    id SERIAL NOT NULL,
    order_id character varying(15),
    table_id bigint NOT NULL ,
    item_id bigint NOT NULL ,
	status integer DEFAULT 3,
	cook_time integer, -- time in minutes and no seconds
	quantity integer DEFAULT 1,
	notes character varying(255),
	create_time TIMESTAMP DEFAULT now(),
	update_time TIMESTAMP DEFAULT now(),
    CONSTRAINT orders_pkey PRIMARY KEY (id)
);

ALTER TABLE ORDERS ADD CONSTRAINT fk_orders_status 
FOREIGN KEY (status) REFERENCES status (code);

ALTER TABLE ORDERS ADD CONSTRAINT fk_orders_table 
FOREIGN KEY (table_id) REFERENCES tables (id);

ALTER TABLE ORDERS ADD CONSTRAINT fk_orders_items 
FOREIGN KEY (item_id) REFERENCES items (id);


-- *********************************************************************************************** -- 
-- insert status
INSERT INTO STATUS (code,description) VALUES (1,'Active');
INSERT INTO STATUS (code,description) VALUES (2,'Inactive');
INSERT INTO STATUS (code,description) VALUES (3,'Received');
INSERT INTO STATUS (code,description) VALUES (4,'Preparing');
INSERT INTO STATUS (code,description) VALUES (5,'Completed');
INSERT INTO STATUS (code,description) VALUES (6,'DELIVERED');
INSERT INTO STATUS (code,description) VALUES (7,'Cancelled');

-- sample tables list
INSERT INTO tables(id,"name",status,capacity) VALUES (1,'Table 1',1,10);
INSERT INTO tables(id,"name",status,capacity) VALUES (2,'Table 2',1,20);
INSERT INTO tables(id,"name",status,capacity) VALUES (3,'Table 3',1,15);
INSERT INTO tables(id,"name",status,capacity) VALUES (4,'Table 4',1,10);
INSERT INTO tables(id,"name",status,capacity) VALUES (5,'Table 5',1,20);

-- sample items list
INSERT INTO items(id,"name",status,price) VALUES (1,'item 1',1,10.00);
INSERT INTO items(id,"name",status,price) VALUES (2,'item 2',1,15.00);
INSERT INTO items(id,"name",status,price) VALUES (3,'item 3',1,20.00);
INSERT INTO items(id,"name",status,price) VALUES (4,'item 4',1,25.00);
INSERT INTO items(id,"name",status,price) VALUES (5,'item 5',1,30.50);
INSERT INTO items(id,"name",status,price) VALUES (6,'item 6',1,35.75);
INSERT INTO items(id,"name",status,price) VALUES (7,'item 7',1,40.00);
INSERT INTO items(id,"name",status,price) VALUES (8,'item 8',1,50.00);
INSERT INTO items(id,"name",status,price) VALUES (9,'item 9',1,100.00);

