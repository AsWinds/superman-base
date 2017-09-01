/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017-03-28 14:57:52                          */
/*==============================================================*/


DROP TABLE IF EXISTS SYS_BANNER;

/*==============================================================*/
/* Table: SYS_BANNER                                            */
/*==============================================================*/
CREATE TABLE SYS_BANNER
(
    ID           BIGINT(9) NOT NULL AUTO_INCREMENT,
    NAME         VARCHAR(200) NOT NULL,
    IMG_URL      VARCHAR(1000) NOT NULL,
    URL          VARCHAR(1000),
    BANNER_ORDER INT(2) NOT NULL DEFAULT 5,
    EFFECT_DATE  DATETIME DEFAULT CURRENT_TIMESTAMP,
    EXPIRE_DATE  DATETIME,
    CREATE_DATE  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UPDATE_DATE  DATETIME NOT NULL,
    DESCRIPTION  VARCHAR(1000) NOT NULL,
   PRIMARY KEY (ID)
)
AUTO_INCREMENT=1001 DEFAULT CHARSET=UTF8;

INSERT INTO `SYS_PRIV` (PRIV_ID, PARENT_PRIV_ID, PRIV_CODE, PRIV_NAME, TYPE, DESCRIPTION)
VALUES (400, 0, 'sys:banner:add', 'sys:banner:add', 2, 'data privliege');
INSERT INTO `SYS_PRIV` (PRIV_ID, PARENT_PRIV_ID, PRIV_CODE, PRIV_NAME, TYPE, DESCRIPTION)
VALUES (401, 0, 'sys:banner:delete', 'sys:banner:delete', 2, 'data privliege');
INSERT INTO `SYS_PRIV` (PRIV_ID, PARENT_PRIV_ID, PRIV_CODE, PRIV_NAME, TYPE, DESCRIPTION)
VALUES (402, 0, 'sys:banner:update', 'sys:banner:update', 2, 'data privliege');
INSERT INTO `SYS_PRIV` (PRIV_ID, PARENT_PRIV_ID, PRIV_CODE, PRIV_NAME, TYPE, DESCRIPTION)
VALUES (403, 0, 'sys:banner:query', 'sys:banner:query', 2, 'data privliege');
INSERT INTO `SYS_PRIV` (PRIV_ID, PARENT_PRIV_ID, PRIV_CODE, PRIV_NAME, TYPE, DESCRIPTION, URL, PATH)
VALUES (404, 1, 'MENU_BANNER', 'Banner', 1, 'banner Management', 'banner/banner', '/banner/index');
INSERT INTO `SYS_ROLE_PRIV` (`ROLE_ID`, `PRIV_ID`)
VALUES (1, 400);
INSERT INTO `SYS_ROLE_PRIV` (`ROLE_ID`, `PRIV_ID`)
VALUES (1, 401);
INSERT INTO `SYS_ROLE_PRIV` (`ROLE_ID`, `PRIV_ID`)
VALUES (1, 402);
INSERT INTO `SYS_ROLE_PRIV` (`ROLE_ID`, `PRIV_ID`)
VALUES (1, 403);
INSERT INTO `SYS_ROLE_PRIV` (`ROLE_ID`, `PRIV_ID`)
VALUES (1, 404);
