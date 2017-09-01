package com.codi.superman.base;

import com.codi.base.exception.BaseAppException;
import com.codi.superman.BaseTest;
import com.codi.superman.base.domain.SysUser;
import com.codi.superman.base.service.SysLoginService;
import com.codi.superman.base.service.SysUserService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户服务测试
 *
 * @author shi.pengyan
 * @date 2016-12-20 14:44
 */
public class SysLoginServiceTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(SysLoginServiceTest.class);

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysLoginService sysLoginService;

    @Test
    public void userLogin() throws BaseAppException {
        SysUser sysUser = sysLoginService.login("admin", "admin");
        logger.debug("sysUser={}", sysUser);
        System.out.println(sysUser);
    }

}
