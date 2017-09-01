package com.codi.superman.base.controller;

import com.codi.base.domain.BaseController;
import com.codi.base.domain.BaseResult;
import com.codi.base.exception.BaseAppException;
import com.codi.base.util.Assert;
import com.codi.superman.base.annotation.RequiresPermissions;
import com.codi.superman.base.domain.SysBanner;
import com.codi.superman.base.service.SysBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 周翔 on 2017/3/28.
 * banner controller
 */

@RestController
@RequestMapping("/sys/banner")
public class SysBannerController extends BaseController {
    @Autowired
    private SysBannerService sysBannerService;

    /**
     * 用户所有的banner信息
     *
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "banners", method = RequestMethod.GET)
    public BaseResult getBannersUser() throws BaseAppException {

        return BaseResult.success(sysBannerService.queryBannersUser());
    }

    /**
     * 管理员所有的banner信息
     *
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "bannersAdmin", method = RequestMethod.GET)
    @RequiresPermissions("sys:banner:query")
    public BaseResult getBannersAdmin() throws BaseAppException {

        return BaseResult.success(sysBannerService.queryBannersAdmin());
    }


    /**
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @RequiresPermissions("sys:banner:add")
    public BaseResult addBanner(SysBanner sysBanner) throws BaseAppException {
        Assert.notNull(sysBanner);
        sysBannerService.addBanner(sysBanner);
        return BaseResult.success(sysBanner);
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @RequiresPermissions("sys:banner:delete")
    public BaseResult deleteBanner(@RequestParam(value = "id") Long id) throws BaseAppException {

        sysBannerService.deleteBanner(id);
        return BaseResult.success();
    }


    @RequestMapping(value = "update", method = RequestMethod.POST)
    @RequiresPermissions("sys:banner:update")
    public BaseResult updateBanner(SysBanner sysBanner) throws BaseAppException {
        Assert.notNull(sysBanner);
        Assert.notNull(sysBanner.getId());
        sysBannerService.updateBanner(sysBanner);
        return BaseResult.success();
    }

    /**
     * banner上线
     *
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "online", method = RequestMethod.POST)
    public BaseResult onlineBanner(SysBanner sysBanner) throws BaseAppException {
        Assert.notNull(sysBanner);
        Assert.notNull(sysBanner.getState());
        sysBannerService.onlineBanner(sysBanner);
        return BaseResult.success();
    }
}
