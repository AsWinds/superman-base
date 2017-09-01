-- shi.pengyan
-- 只有在开发环境下执行
-- Test Directory and priv
INSERT INTO `SYS_PRIV` (PRIV_ID, PARENT_PRIV_ID, PRIV_CODE, PRIV_NAME, TYPE, URL, PATH, DESCRIPTION)
VALUES (2, 0, 'DIRECTORY_TEST', 'Test', 0, NULL, NULL, 'Test Directory');

INSERT INTO `SYS_PRIV` (PRIV_ID, PARENT_PRIV_ID, PRIV_CODE, PRIV_NAME, TYPE, URL, PATH, DESCRIPTION)
VALUES (201, 2, 'MENU_TEST_PAGE', 'Test Page', 1, 'test/index', '/test/index', 'Test Page Management');

INSERT INTO `SYS_ROLE_PRIV` (ROLE_ID, PRIV_ID) VALUES (1, 2), (1, 201);
