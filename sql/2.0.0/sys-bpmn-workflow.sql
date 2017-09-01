DROP TABLE IF EXISTS SYS_BIZ_WORKFLOW;

/*==============================================================*/
/* Table: SYS_BIZ_WORKFLOW                                      */
/*==============================================================*/
CREATE TABLE SYS_BIZ_WORKFLOW
(
   ID                   BIGINT(20) NOT NULL AUTO_INCREMENT,
   USER_ID              BIGINT(20),
   USER_CODE            VARCHAR(100),
   STATE                INT(1) COMMENT '0:草稿
            1:审批中
            2:通过
            3:拒绝',
   BIZ_TYPE             VARCHAR(100),
   BIZ_KEY              VARCHAR(100),
   STATE_DATE           DATETIME DEFAULT CURRENT_TIMESTAMP,
   CREATE_DATE          DATETIME,
   UPDATE_DATE          DATETIME,
   DESCRIPTION          VARCHAR(1000),
   PRIMARY KEY (ID)
)
AUTO_INCREMENT=1001 DEFAULT CHARSET=UTF8;


DROP TABLE IF EXISTS SYS_BIZ_LEAVE;

/*==============================================================*/
/* Table: SYS_BIZ_LEAVE                                         */
/*==============================================================*/
CREATE TABLE SYS_BIZ_LEAVE
(
   ID                   BIGINT(20) NOT NULL AUTO_INCREMENT,
   USER_ID              BIGINT(20),
   USER_CODE            VARCHAR(100),
   TYPE                 INT(2) COMMENT '0:调休
            1:病假
            2:事假
            3:年假
            4:路途假
            5:婚假
            6:陪产假
            7:产假
            8:丧假
            9:其他
            ',
   REASON               VARCHAR(1000),
   STATE                INT(1) COMMENT '0:草稿
            1:审批中
            2:通过
            3:拒绝',
   STATE_DATE           DATETIME DEFAULT CURRENT_TIMESTAMP,
   BEGIN_DATE           DATETIME DEFAULT CURRENT_TIMESTAMP,
   END_DATE             DATETIME,
   CREATE_DATE          DATETIME,
   UPDATE_DATE          DATETIME,
   DESCRIPTION          VARCHAR(1000),
   PRIMARY KEY (ID)
)
AUTO_INCREMENT=1001 DEFAULT CHARSET=UTF8;
