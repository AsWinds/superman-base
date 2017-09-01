package com.codi.superman.base;

import com.codi.base.exception.BaseAppException;
import com.codi.superman.BaseTest;
import com.codi.superman.base.domain.SysAppVersion;
import com.codi.superman.base.service.SysAppVersionService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2016-12-28 9:51
 */
public class SysAppVersionTest extends BaseTest {

    @Autowired
    private SysAppVersionService sysAppVersionService;

    private Long appId;
    private Long versionId;

    @Override
    public void before() {
        super.before();
        appId = 1001L;
        versionId = 1002L;
    }

    @Test

    public void addTest() throws BaseAppException {
        SysAppVersion version = new SysAppVersion();

        version.setVersionNumber("1.0.0");
        version.setAppId(appId);
        version.setDownloadPath("http://www.cd121.com/index.html");
        version.setForceUpdate(true);
        version.setVersionDesc("1.更新啦");

        sysAppVersionService.addAppVersion(version);
    }

    @Test
    public void updateTest() throws BaseAppException {

        SysAppVersion version = new SysAppVersion();
        version.setVersionId(versionId);
        version.setVersionDesc("更新啦");

        sysAppVersionService.updateAppVersion(version);
    }

    @Test
    public void delTest() throws BaseAppException {
        sysAppVersionService.delAppVersion(versionId);
    }

    @Test
    public void queryObjTest() throws BaseAppException {
        logger.debug("count={}", sysAppVersionService.queryAppVersionsCount());

    }

    @Test
    public void queryListTest() throws BaseAppException {
        logger.debug("list={}", sysAppVersionService.queryAppVersions(1, 100));

    }

}
