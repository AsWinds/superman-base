package com.codi.superman.base;

import com.codi.base.exception.BaseAppException;
import com.codi.superman.BaseTest;
import com.codi.superman.base.common.Const;
import com.codi.superman.base.domain.SysApp;
import com.codi.superman.base.service.SysAppService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2016-12-28 9:14
 */
public class SysAppTest extends BaseTest {

    @Autowired
    private SysAppService sysAppService;

    private Long appId;

    @Override
    public void before() {
        super.before();
        appId = 1001L;
    }

    @Test
    public void addTest() throws BaseAppException {
        SysApp sysApp = new SysApp();
        sysApp.setAppName("IOS");
        sysApp.setAppType(Const.APP_TYPE_ANDROID);
        sysApp.setDescription("androidxx");

        sysAppService.addApp(sysApp);
    }

    @Test
    public void updateTest() throws BaseAppException {
        SysApp sysApp = new SysApp();
        sysApp.setAppId(appId);
        sysApp.setAppName("IOSxxx");
        sysApp.setDescription("xxxxccc");

        sysAppService.updateApp(sysApp);
    }

    @Test
    public void queryAppTest() throws BaseAppException {
        logger.debug("sysapp={}", sysAppService.queryApp(appId));
    }

    @Test
    public void queryAppsTest() throws BaseAppException {
        logger.debug("apps={}", sysAppService.queryApps());
    }

    @Test
    public void delTest() throws BaseAppException {
        sysAppService.delApp(appId);
    }

}
