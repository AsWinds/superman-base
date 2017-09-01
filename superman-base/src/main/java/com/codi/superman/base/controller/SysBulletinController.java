package com.codi.superman.base.controller;

import com.codi.base.domain.BaseController;
import com.codi.base.domain.BaseResult;
import com.codi.base.exception.BaseAppException;
import com.codi.base.util.Assert;
import com.codi.superman.base.annotation.RequiresPermissions;
import com.codi.superman.base.domain.SysBulletin;
import com.codi.superman.base.service.SysBulletinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 周翔 on 2017/4/7.
 */

@RestController
@RequestMapping("/sys/bulletin")
public class SysBulletinController extends BaseController {
    @Autowired
    private SysBulletinService sysBulletinService;

    /**
     * 管理员获取公告信息
     *
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "bulletinAdmin", method = RequestMethod.GET)
    @RequiresPermissions("sys:bulletin:query")
    public BaseResult getBulletinsAdmin(@RequestParam(value = "groupId") Long groupId,
                                        @RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
                                        @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws BaseAppException {
        return BaseResult.success(sysBulletinService.queryBulletinsAdmin(groupId, pageIndex, pageSize));
    }


    /**
     * 管理员获取公告总数
     *
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "count", method = RequestMethod.GET)
    @RequiresPermissions("sys:bulletin:count")
    public BaseResult getBulletinCount(@RequestParam(value = "groupId") Long groupId) throws BaseAppException {
        return BaseResult.success(sysBulletinService.selectBulletinCount(groupId));
    }

    /**
     * 管理员通过id获取公告信息
     *
     * @param id
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "query", method = RequestMethod.GET)
    public BaseResult getBulletinById(@RequestParam(value = "id") Long id) throws BaseAppException {
        return BaseResult.success(sysBulletinService.queryBulletinById(id));
    }

    /**
     * 管理员添加公告
     *
     * @param sysBulletin
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @RequiresPermissions("sys:bulletin:add")
    public BaseResult addBulletin(SysBulletin sysBulletin) throws BaseAppException {
        Assert.notNull(sysBulletin);
        sysBulletinService.addBulletin(sysBulletin);
        return BaseResult.success();
    }

    /**
     * 管理员修改公告
     *
     * @param sysBulletin
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @RequiresPermissions("sys:bulletin:update")
    public BaseResult updateBulletin(SysBulletin sysBulletin) throws BaseAppException {
        Assert.notNull(sysBulletin.getId());
        sysBulletinService.updateBulletin(sysBulletin);
        return BaseResult.success();
    }

    /**
     * 管理员删除公告
     *
     * @param id
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @RequiresPermissions("sys:bulletin:delete")
    public BaseResult deleteBulletin(@RequestParam(value = "id") Long id) throws BaseAppException {
        sysBulletinService.deleteBulletin(id);
        return BaseResult.success();
    }

    /**
     * 审核公告
     *
     *
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "review", method = RequestMethod.POST)
    public BaseResult reviewBulletin(SysBulletin sysBulletin) throws BaseAppException {
        sysBulletinService.reviewBulletin(sysBulletin);
        return BaseResult.success();
    }

    /**
     * 手机APP获取公告的标题,时间,id的低层次接口
     *
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "List", method = RequestMethod.GET)
    public BaseResult getBulletinsLowAPP(@RequestParam(value = "bizCode") String bizCode,
                                         @RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
                                         @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws BaseAppException {
        return BaseResult.success(sysBulletinService.queryBulletinAPP(bizCode, pageIndex, pageSize));
    }

    /**
     * 手机公告的高层次接口，获取标题，时间，id
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "APP", method = RequestMethod.GET)
    public BaseResult getBulletinAPP(@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
                                     @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws BaseAppException {
        String bizCode = "bulletin_app";
        return this.getBulletinsLowAPP(bizCode, pageIndex, pageSize);
    }

    /**
     * 要闻直播的高层次接口
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "newsBroadcast", method = RequestMethod.GET)
    public BaseResult getNewsAPP(@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
                                 @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws BaseAppException {
        String bizCode = "bulletin_news_broadcast";
        return this.getBulletinsLowAPP(bizCode, pageIndex, pageSize);
    }

    /**
     * 基金学堂的高层次接口
     *
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "fundSchool", method = RequestMethod.GET)
    public BaseResult getLearnAPP(@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
                                  @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws BaseAppException {
        String bizCode = "bulletin_fund_school";
        return this.getBulletinsLowAPP(bizCode, pageIndex, pageSize);
    }


    /**
     * 手机APP获取公告的内容
     *
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "content", method = RequestMethod.GET)
    public BaseResult getBulletinsContentAPP(@RequestParam(value = "id") Long id) throws BaseAppException {
        return BaseResult.success(sysBulletinService.queryBulletinByIdAPP(id));
    }



}
