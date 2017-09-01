package com.codi.superman.base.controller;

import com.codi.base.domain.BaseController;
import com.codi.base.domain.BaseResult;
import com.codi.base.exception.BaseAppException;
import com.codi.base.util.Assert;
import com.codi.superman.base.annotation.RequiresPermissions;
import com.codi.superman.base.domain.SysApp;
import com.codi.superman.base.service.SysAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * APP
 *
 * @author shi.pengyan
 * @date 2016-12-27 20:21
 */
@RestController
@RequestMapping("/sys/app")
public class SysAppController extends BaseController {

    @Autowired
    private SysAppService sysAppService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @RequiresPermissions("sys:app:add")
    public BaseResult addApp(SysApp sysApp) throws BaseAppException {
        Assert.notNull(sysApp);

        sysAppService.addApp(sysApp);

        BaseResult result = new BaseResult();
        result.setResult(sysApp);
        return result;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @RequiresPermissions("sys:app:update")
    public BaseResult updateApp(SysApp sysApp) throws BaseAppException {
        Assert.notNull(sysApp);
        Assert.notNull(sysApp.getAppId());

        sysAppService.updateApp(sysApp);

        BaseResult result = new BaseResult();
        return result;
    }

    @RequestMapping(value = "delete/{appId}", method = RequestMethod.DELETE)
    @RequiresPermissions("sys:app:delete")
    public BaseResult deleteApp(@PathVariable(value = "appId") Long appId) throws BaseAppException {
        sysAppService.delApp(appId);

        BaseResult result = new BaseResult();
        return result;
    }

    @RequestMapping(value = "apps", method = RequestMethod.GET)
    @RequiresPermissions("sys:app:query")
    public BaseResult queryApps() throws BaseAppException {
        BaseResult result = new BaseResult();

        result.setResult(sysAppService.queryApps());
        return result;
    }

}
