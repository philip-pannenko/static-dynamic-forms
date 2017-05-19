--TRUNCATE TABLE MENUS;
--DROP TABLE ORDERS;

CREATE TABLE MENUS (
    ID     SERIAL,
    NAME    varchar(255) NOT NULL,
    DOCUMENT text,
    PRIMARY KEY(ID)
);

GRANT ALL ON MENUS TO genericmenuuser;
GRANT ALL ON menus_id_seq TO genericmenuuser;

CREATE TABLE ORDERS (
    ID       SERIAL,
    MENU_ID  INT NOT NULL,
    DATA     JSONB,
    PRIMARY  KEY(ID)
);

GRANT ALL ON ORDERS TO genericmenuuser;
GRANT ALL ON orders_id_seq TO genericmenuuser;

SELECT * FROM MENUS;
SELECT * FROM ORDERS;

