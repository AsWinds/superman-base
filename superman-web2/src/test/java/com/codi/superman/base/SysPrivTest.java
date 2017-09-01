package com.codi.superman.base;

import com.codi.base.exception.BaseAppException;
import com.codi.superman.BaseTest;
import com.codi.superman.base.common.Const;
import com.codi.superman.base.domain.SysPriv;
import com.codi.superman.base.service.SysPrivService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2016-12-25 13:43
 */
public class SysPrivTest extends BaseTest {

    @Autowired
    private SysPrivService sysPrivService;

    private Long privId;

    @Before
    public void before() {
        privId = 1001L;
    }

    @Test
    public void addTest() throws BaseAppException {

        SysPriv sysPriv = new SysPriv();
        sysPriv.setPrivCode("USER_ROLE_PRIV");
        sysPriv.setPrivName("user role sysPriv");
        sysPriv.setType(Const.PRIV_TYPE_MENU);
        sysPriv.setUrl("/sys/menu");
        sysPriv.setPath("/sys/menuInBrowser");
        sysPriv.setDescription("haha哈");

        sysPrivService.addPriv(sysPriv);
    }

    @Test
    public void modifyTest() throws BaseAppException {
        SysPriv sysPriv = new SysPriv();
        sysPriv.setPrivId(privId);
        sysPriv.setPrivCode("USER_ROLE_PRIV2");
        sysPriv.setPrivName("user role priv2");
        sysPriv.setType(Const.PRIV_TYPE_MENU);
        sysPriv.setUrl("/sys/menu2");
        sysPriv.setPath("/sys/menuInBrowser2");
        sysPriv.setDescription("haha哈2");

        sysPrivService.modifyPriv(sysPriv);
    }

    @Test
    public void queryTest() throws BaseAppException {
        logger.debug("priv={}", sysPrivService.queryPriv(privId));
    }

    @Test
    public void queryPrivsCountTest() throws BaseAppException {
        logger.debug("total count={}", sysPrivService.queryPrivsCount());
    }

    @Test
    public void queryPrivsTest() throws BaseAppException {
        List<SysPriv> sysPrivs = sysPrivService.queryPrivs(1, 100);
        logger.debug("total count={}", sysPrivs);
    }

    @Test
    public void queryPrivsByRoleIds() throws BaseAppException {
        List<Long> roleIds = Arrays.asList(1001L);

        List<SysPriv> sysPrivs = sysPrivService.queryPrivsByRoleIds(roleIds);
        logger.debug("sysPrivs={}", sysPrivs);

    }
}
