package com.codi.superman.base;

import com.codi.base.exception.BaseAppException;
import com.codi.superman.BaseTest;
import com.codi.superman.base.service.SysRolePrivService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2016-12-25 15:58
 */
public class SysRolePrivTest extends BaseTest {

    @Autowired
    private SysRolePrivService rolePrivService;

    private Long roleId;
    private Long privId;

    @Before
    public void before() {
        super.before();
        roleId = 1001L;
        privId = 1001L;
    }

    @Test
    public void addTest() throws BaseAppException {
        rolePrivService.addRolePriv(roleId, privId);
    }

    @Test
    public void disableRolePrivTest() throws BaseAppException {
        rolePrivService.disableRolePriv(roleId, privId);
    }

    @Test
    public void enableRolePrivTest() throws BaseAppException {
        rolePrivService.enableRolePriv(roleId, privId);
    }

    @Test
    public void delRolePrivTest() throws BaseAppException {
        rolePrivService.delRolePriv(roleId, privId);
    }

}
