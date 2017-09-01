INSERT INTO `SYS_PRIV` (PRIV_ID, PARENT_PRIV_ID, PRIV_CODE, PRIV_NAME, TYPE, DESCRIPTION, URL, PATH)
VALUES (460, 1, 'MENU_PICTURE', '图片管理', 1, 'picture Management', 'pictureUpload/picture', '/pictureUpload/index');
INSERT INTO `SYS_ROLE_PRIV` (`ROLE_ID`, `PRIV_ID`)
VALUES (1, 460);
