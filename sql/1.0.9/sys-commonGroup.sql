/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017-04-07 10:43:13                          */
/*==============================================================*/

DROP TABLE IF EXISTS SYS_COMMON_GROUP;

/*==============================================================*/
/* Table: SYS_COMMON_GROUP                                      */
/*==============================================================*/

CREATE TABLE SYS_COMMON_GROUP
(
    GROUP_ID       BIGINT(9)    NOT NULL AUTO_INCREMENT,
    GROUP_NAME     VARCHAR(200) NOT NULL,
    BIZ_GROUP_CODE VARCHAR(200) NOT NULL,
    BIZ_CODE       VARCHAR(200) NOT NULL,
    CREATE_DATE    DATETIME              DEFAULT CURRENT_TIMESTAMP,
    UPDATE_DATE    DATETIME              DEFAULT CURRENT_TIMESTAMP,
    DESCRIPTION    VARCHAR(1000),
    PRIMARY KEY (GROUP_ID)
)
    AUTO_INCREMENT = 1001
    DEFAULT CHARSET = UTF8;

INSERT INTO `SYS_PRIV` (PRIV_ID, PARENT_PRIV_ID, PRIV_CODE, PRIV_NAME, TYPE, DESCRIPTION)
VALUES (431, 0, 'sys:group:add', 'sys:group:add', 2, 'data privilege');
INSERT INTO `SYS_PRIV` (PRIV_ID, PARENT_PRIV_ID, PRIV_CODE, PRIV_NAME, TYPE, DESCRIPTION)
VALUES (432, 0, 'sys:group:delete', 'sys:group:delete', 2, 'data privilege');
INSERT INTO `SYS_PRIV` (PRIV_ID, PARENT_PRIV_ID, PRIV_CODE, PRIV_NAME, TYPE, DESCRIPTION)
VALUES (433, 0, 'sys:group:update', 'sys:group:update', 2, 'data privilege');
INSERT INTO `SYS_PRIV` (PRIV_ID, PARENT_PRIV_ID, PRIV_CODE, PRIV_NAME, TYPE, DESCRIPTION)
VALUES (434, 0, 'sys:group:query', 'sys:group:query', 2, 'data privilege');
INSERT INTO `SYS_PRIV` (PRIV_ID, PARENT_PRIV_ID, PRIV_CODE, PRIV_NAME, TYPE, DESCRIPTION)
VALUES (435, 0, 'sys:group:biz', 'sys:group:biz', 2, 'data privilege');
INSERT INTO `SYS_PRIV` (PRIV_ID, PARENT_PRIV_ID, PRIV_CODE, PRIV_NAME, TYPE, DESCRIPTION)
VALUES (436, 0, 'sys:group:count', 'sys:group:count', 2, 'data privilege');
INSERT INTO `SYS_PRIV` (PRIV_ID, PARENT_PRIV_ID, PRIV_CODE, PRIV_NAME, TYPE, DESCRIPTION, URL, PATH)
VALUES (430, 1, 'MENU_GROUP', '分组', 1, 'group Management', 'group/group', '/group/index');
INSERT INTO `SYS_ROLE_PRIV` (`ROLE_ID`, `PRIV_ID`)
VALUES (1, 430);
INSERT INTO `SYS_ROLE_PRIV` (`ROLE_ID`, `PRIV_ID`)
VALUES (1, 431);
INSERT INTO `SYS_ROLE_PRIV` (`ROLE_ID`, `PRIV_ID`)
VALUES (1, 432);
INSERT INTO `SYS_ROLE_PRIV` (`ROLE_ID`, `PRIV_ID`)
VALUES (1, 433);
INSERT INTO `SYS_ROLE_PRIV` (`ROLE_ID`, `PRIV_ID`)
VALUES (1, 434);
INSERT INTO `SYS_ROLE_PRIV` (`ROLE_ID`, `PRIV_ID`)
VALUES (1, 435);
INSERT INTO `SYS_ROLE_PRIV` (`ROLE_ID`, `PRIV_ID`)
VALUES (1, 436);

