/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017-04-07 10:43:13                          */
/*==============================================================*/


DROP TABLE IF EXISTS SYS_BULLETIN;



/*==============================================================*/
/* Table: SYS_BULLETIN                                          */
/*==============================================================*/

CREATE TABLE SYS_BULLETIN
(
    ID           BIGINT(9)    NOT NULL AUTO_INCREMENT,
    GROUP_ID     BIGINT(9),
    TITLE        VARCHAR(200) NOT NULL,
    AUTHOR       VARCHAR(200),
    INTRO        VARCHAR(1000),
    HOME_IMG_URL VARCHAR(1000),
    URL          VARCHAR(512),
    CONTENT      VARCHAR(10000),
    DEVICE_TYPE  INT(1)       NOT NULL
    COMMENT '0:PC, 1:APP',
    TYPE         INT(1)       NOT NULL
    COMMENT '0:常规文章 1:超链接',
    STATE        INT(1)       NOT NULL DEFAULT 1
    COMMENT '0:废弃;1:未审核; 2:已审核 可扩展其他状态',
    EFFECT_DATE  DATETIME,
    EXPIRE_DATE  DATETIME,
    CREATE_DATE  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UPDATE_DATE  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    DESCRIPTION  VARCHAR(1000),
    BIZ_ID       VARCHAR(100),
    PRIMARY KEY (ID)
)
    AUTO_INCREMENT = 1001
    DEFAULT CHARSET = UTF8;



INSERT INTO `SYS_PRIV` (PRIV_ID, PARENT_PRIV_ID, PRIV_CODE, PRIV_NAME, TYPE, DESCRIPTION)
VALUES (421, 0, 'sys:bulletin:add', 'sys:bulletin:add', 2, 'data privilege');
INSERT INTO `SYS_PRIV` (PRIV_ID, PARENT_PRIV_ID, PRIV_CODE, PRIV_NAME, TYPE, DESCRIPTION)
VALUES (422, 0, 'sys:bulletin:delete', 'sys:bulletin:delete', 2, 'data privilege');
INSERT INTO `SYS_PRIV` (PRIV_ID, PARENT_PRIV_ID, PRIV_CODE, PRIV_NAME, TYPE, DESCRIPTION)
VALUES (423, 0, 'sys:bulletin:update', 'sys:bulletin:update', 2, 'data privilege');
INSERT INTO `SYS_PRIV` (PRIV_ID, PARENT_PRIV_ID, PRIV_CODE, PRIV_NAME, TYPE, DESCRIPTION)
VALUES (424, 0, 'sys:bulletin:query', 'sys:bulletin:query', 2, 'data privilege');
INSERT INTO `SYS_PRIV` (PRIV_ID, PARENT_PRIV_ID, PRIV_CODE, PRIV_NAME, TYPE, DESCRIPTION)
VALUES (425, 0, 'sys:bulletin:count', 'sys:bulletin:count', 2, 'data privilege');
INSERT INTO `SYS_PRIV` (PRIV_ID, PARENT_PRIV_ID, PRIV_CODE, PRIV_NAME, TYPE, DESCRIPTION, URL, PATH)
VALUES (420, 1, 'MENU_BULLETIN', '公告', 1, 'bulletin Management', 'bulletin/bulletin', '/bulletin/index');
INSERT INTO `SYS_ROLE_PRIV` (`ROLE_ID`, `PRIV_ID`)
VALUES (1, 420);
INSERT INTO `SYS_ROLE_PRIV` (`ROLE_ID`, `PRIV_ID`)
VALUES (1, 421);
INSERT INTO `SYS_ROLE_PRIV` (`ROLE_ID`, `PRIV_ID`)
VALUES (1, 422);
INSERT INTO `SYS_ROLE_PRIV` (`ROLE_ID`, `PRIV_ID`)
VALUES (1, 423);
INSERT INTO `SYS_ROLE_PRIV` (`ROLE_ID`, `PRIV_ID`)
VALUES (1, 424);
INSERT INTO `SYS_ROLE_PRIV` (`ROLE_ID`, `PRIV_ID`)
VALUES (1, 425);
