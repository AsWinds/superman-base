package com.codi.superman.base;

import com.codi.base.exception.BaseAppException;
import com.codi.superman.BaseTest;
import com.codi.superman.base.service.SysUserRoleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2016-12-25 12:58
 */
public class SysUserRoleTest extends BaseTest {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    private Long userId;
    private Long roleId;

    @Override
    public void before() {
        super.before();
        userId = 1001L;
        roleId = 1001L;
    }

    @Test
    public void addTest() throws BaseAppException {
        logger.debug("abc");
        sysUserRoleService.addUserRole(userId, roleId);
    }

    @Test
    public void disableTest() throws BaseAppException {
        sysUserRoleService.disableUserRole(userId, roleId);
    }

    @Test
    public void enableTest() throws BaseAppException {
        sysUserRoleService.enableUserRole(userId, roleId);
    }

    @Test
    public void delTest() throws BaseAppException {
        sysUserRoleService.delUserRole(userId, roleId);
    }

}
