package com.codi.superman.base;

import com.codi.base.exception.BaseAppException;
import com.codi.superman.BaseTest;
import com.codi.superman.base.domain.SysRole;
import com.codi.superman.base.service.SysRoleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * SysRoleTest
 *
 * @author shi.pengyan
 * @date 2016-12-22 15:27
 */
public class SysRoleTest extends BaseTest {

    @Autowired
    private SysRoleService sysRoleService;

    @Test
    public void addRoleTest() throws BaseAppException {
        sysRoleService.addRole("role1", "roleName", "测试角色");
    }

    @Test
    public void getRoleTest() {

    }

    @Test
    public void delRoleTest() {

    }

    @Test
    public void checkRoleCode() throws BaseAppException {
        System.out.println("xx" + sysRoleService.checkRoleCode("admin"));
    }

    @Test
    public void queryRoles() throws BaseAppException {
        List<SysRole> sysRoles = sysRoleService.queryRoles(1, 100);
        System.out.println(sysRoles);
    }
}
