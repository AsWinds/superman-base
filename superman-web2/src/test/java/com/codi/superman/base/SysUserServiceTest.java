package com.codi.superman.base;

import com.codi.base.exception.BaseAppException;
import com.codi.base.util.Assert;
import com.codi.superman.BaseTest;
import com.codi.superman.base.domain.SysUser;
import com.codi.superman.base.service.SysUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户服务测试
 *
 * @author shi.pengyan
 * @date 2016-12-20 14:44
 */
public class SysUserServiceTest extends BaseTest {

    @Autowired
    private SysUserService sysUserService;

    private Long userId;

    @Override
    public void before() {
        super.before();
        userId = 1002L;
    }

    @Test
    public void addTest() throws BaseAppException {

        SysUser sysUser = new SysUser();
        sysUser.setUserCode("test11");
        sysUser.setUserName("test smith");
        sysUser.setMobile("10086");
        sysUser.setMemo("test11");

        sysUserService.addUser(sysUser);
    }

    @Test
    public void updateTest() throws BaseAppException {
        SysUser sysUser = sysUserService.getUser(userId);
        Assert.notNull(sysUser);

        sysUser.setMemo("abc");
        sysUserService.updateUser(sysUser);
    }

    @Test
    public void lockTest() throws BaseAppException {
        sysUserService.lockUser(userId, 1L);
    }

    @Test
    public void unlockTest() throws BaseAppException {
        sysUserService.unlockUser(userId);
    }

    @Test
    public void getUserTest() throws BaseAppException {
        logger.debug("user={}", sysUserService.getUser(userId));
    }

    @Test
    public void getUsersTest() throws BaseAppException {
        logger.debug("users={}", sysUserService.getUsers(1, 100));
    }

    @Test
    public void getUsersCountTest() throws BaseAppException {
        logger.debug("count={}", sysUserService.getUsersCount());
    }

    @Test
    public void delTest() throws BaseAppException {
        userId = 0L;
        sysUserService.delUser(userId, 1L);
    }
}
