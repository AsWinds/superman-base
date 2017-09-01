package com.codi.superman.base.controller;

import com.codi.base.domain.BaseController;
import com.codi.base.domain.BaseResult;
import com.codi.base.exception.BaseAppException;
import com.codi.superman.base.annotation.RequiresPermissions;
import com.codi.superman.base.domain.SysAppVersion;
import com.codi.superman.base.service.SysAppVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * app version controller
 *
 * @author shi.pengyan
 * @date 2016-12-27 20:21
 */
@RestController
@RequestMapping("/sys/appversion")
public class SysAppVersionController extends BaseController {

    @Autowired
    private SysAppVersionService sysAppVersionService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @RequiresPermissions("sys:appversion:add")
    public BaseResult addVersion(SysAppVersion sysAppVersion) throws BaseAppException {

        sysAppVersionService.addAppVersion(sysAppVersion);

        BaseResult result = new BaseResult();
        result.setResult(sysAppVersion);
        return result;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @RequiresPermissions("sys:appversion:update")
    public BaseResult updateVersion(SysAppVersion sysAppVersion) throws BaseAppException {
        sysAppVersionService.updateAppVersion(sysAppVersion);

        BaseResult result = new BaseResult();
        return result;
    }

    @RequestMapping(value = "delete/{versionId}", method = RequestMethod.DELETE)
    @RequiresPermissions("sys:appversion:delete")
    public BaseResult delVersion(@PathVariable(value = "versionId") Long versionId) throws BaseAppException {
        sysAppVersionService.delAppVersion(versionId);

        BaseResult result = new BaseResult();
        return result;
    }


    /**
     * 获取APP最新的版本
     *
     * @param appId
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "latest", method = RequestMethod.GET)
    public BaseResult queryLatest(@RequestParam(value = "appId") Long appId,
                                  @RequestParam(value = "appVersion", defaultValue = "1.0.0") String appVersion) throws BaseAppException {

        BaseResult result = new BaseResult();

        SysAppVersion latestVersion = sysAppVersionService.queryLatestVersion(appId, appVersion);

        if (latestVersion != null) {
            latestVersion.setVersionId(null);
            latestVersion.setState(null);
            latestVersion.setUpdateDate(null);
            result.setResult(latestVersion);
        }

        return result;
    }

    @RequestMapping(value = "versionsCount", method = RequestMethod.GET)
    @RequiresPermissions("sys:appversion:query")
    public BaseResult queryVersionsCount() throws BaseAppException {

        BaseResult result = new BaseResult();
        result.setResult(sysAppVersionService.queryAppVersionsCount());
        return result;
    }

    @RequestMapping(value = "versions", method = RequestMethod.GET)
    @RequiresPermissions("sys:appversion:query")
    public BaseResult queryVersions(@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
                                    @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws BaseAppException {

        logger.debug("pageIndex={},pageSize={}", pageIndex, pageSize);
        BaseResult result = new BaseResult();
        result.setResult(sysAppVersionService.queryAppVersions(pageIndex, pageSize));

        return result;
    }

    /**
     * 通过appcode和versionNumber获取版本信息
     *
     * @param appCode
     * @param versionNumber
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "versionByCode", method = RequestMethod.GET)
    public BaseResult queryVersionByAppCode(@RequestParam(value = "appCode") String appCode,
                                            @RequestParam(value = "versionNumber") String versionNumber) throws BaseAppException {
        BaseResult result = new BaseResult();
        result.setResult(sysAppVersionService.queryVersionByAppCode(appCode, versionNumber));
        return result;
    }

    /**
     * 通过appCode获取APP最新的版本
     *
     * @param appCode
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "latestByCode", method = RequestMethod.GET)
    public BaseResult queryLatestByCode(@RequestParam(value = "appCode") String appCode,
                                        @RequestParam(value = "appVersion", defaultValue = "1.0.0") String appVersion) throws BaseAppException {

        BaseResult result = new BaseResult();

        SysAppVersion latestVersion = sysAppVersionService.queryLatestVersionByAppCode(appCode, appVersion);

        if (latestVersion != null) {
            latestVersion.setVersionId(null);
            latestVersion.setUpdateDate(null);
            latestVersion.setState(null);
            result.setResult(latestVersion);
        }
        return result;
    }
}
