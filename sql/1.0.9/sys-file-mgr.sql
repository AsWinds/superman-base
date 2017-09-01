-- file upload menu
INSERT INTO `SYS_PRIV` (PRIV_ID, PARENT_PRIV_ID, PRIV_CODE, PRIV_NAME, TYPE, URL, PATH, DESCRIPTION)
VALUES
    (410, 1, 'MENU_FILE_UPLOAD', '文件上传', 1, 'fileUpload/index', '/fileUpload/index', 'File Upload Management');

-- file priv
INSERT INTO `SYS_PRIV` (PRIV_ID, PARENT_PRIV_ID, PRIV_CODE, PRIV_NAME, TYPE, DESCRIPTION)
VALUES (411, 0, 'sys:file:save', 'sys:file:save', 2, 'upload file priviledge');
INSERT INTO `SYS_PRIV` (PRIV_ID, PARENT_PRIV_ID, PRIV_CODE, PRIV_NAME, TYPE, DESCRIPTION)
VALUES (412, 0, 'sys:file:delete', 'sys:file:delete', 2, 'delete upload file priviledge');
INSERT INTO `SYS_PRIV` (PRIV_ID, PARENT_PRIV_ID, PRIV_CODE, PRIV_NAME, TYPE, DESCRIPTION)
VALUES (413, 0, 'sys:file:query', 'sys:file:query', 2, 'query upload file priviledge');
INSERT INTO `SYS_PRIV` (PRIV_ID, PARENT_PRIV_ID, PRIV_CODE, PRIV_NAME, TYPE, DESCRIPTION)
VALUES (414, 0, 'sys:file:update', 'sys:file:update', 2, 'update upload file');

INSERT INTO `SYS_ROLE_PRIV` (ROLE_ID, PRIV_ID) VALUES (1, 410), (1, 411), (1, 412), (1, 413), (1, 414);


DROP TABLE IF EXISTS SYS_FILE_BUCKET;

/*==============================================================*/
/* Table: SYS_FILE_BUCKET                                       */
/*==============================================================*/
CREATE TABLE SYS_FILE_BUCKET
(
    BUCKET_ID   BIGINT(9)    NOT NULL AUTO_INCREMENT,
    BUCKET_NAME VARCHAR(500) NOT NULL,
    BUCKET_CODE VARCHAR(100) NOT NULL,
    HOST_NAME   VARCHAR(500) NOT NULL,
    IS_PUBLIC   TINYINT(1)            DEFAULT 1,
    CREATE_DATE DATETIME              DEFAULT CURRENT_TIMESTAMP,
    UPDATE_DATE DATETIME              DEFAULT CURRENT_TIMESTAMP,
    DESCRIPTION VARCHAR(1000),
    PRIMARY KEY (BUCKET_ID)
)
    AUTO_INCREMENT = 1001
    DEFAULT CHARSET = UTF8;

-- SYS_FILE　domain

DROP TABLE IF EXISTS SYS_FILE;

/*==============================================================*/
/* Table: SYS_FILE                                              */
/*==============================================================*/
CREATE TABLE SYS_FILE
(
    ID               BIGINT(9)     NOT NULL AUTO_INCREMENT,
    BUCKET_ID        BIGINT(9),
    FILE_NAME        VARCHAR(500)  NOT NULL,
    ORIGIN_FILE_NAME VARCHAR(500),
    HASH             VARCHAR(500),
    FILE_URL         VARCHAR(1000) NOT NULL,
    MIME_TYPE        VARCHAR(20)   NOT NULL
    COMMENT 'img,zip,txt 等等',
    FILE_SIZE        VARCHAR(1000),
    STATE            INT(1)        NOT NULL DEFAULT 1
    COMMENT '1:在用; 0:废弃;可扩展其他状态',
    CREATE_DATE      DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UPDATE_DATE      DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    DESCRIPTION      VARCHAR(1000),
    PRIMARY KEY (ID)
)
    AUTO_INCREMENT = 1001
    DEFAULT CHARSET = UTF8;

