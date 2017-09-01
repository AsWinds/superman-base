package com.codi.superman.base.controller;

import com.codi.base.domain.BaseController;
import com.codi.base.domain.BaseResult;
import com.codi.base.exception.BaseAppException;
import com.codi.base.util.Assert;
import com.codi.superman.base.annotation.RequiresPermissions;
import com.codi.superman.base.domain.SysCommonGroup;
import com.codi.superman.base.service.SysCommonGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 周翔 on 2017/4/12.
 */

@RestController
@RequestMapping("/sys/group")
public class SysCommonGroupController extends BaseController {
    @Autowired
    private SysCommonGroupService sysCommonGroupService;

    /**
     * 管理员通过业务分组得到分组信息
     *
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "byBiz", method = RequestMethod.GET)
    @RequiresPermissions("sys:group:biz")
    public BaseResult getGroupByBiz(@RequestParam(value = "biz") String biz) throws BaseAppException {
        return BaseResult.success(sysCommonGroupService.queryGroupByBizGroupCode(biz));
    }

    /**
     * 管理员获取分组总数
     *
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "count", method = RequestMethod.GET)
    @RequiresPermissions("sys:group:count")
    public BaseResult getGroupCount() throws BaseAppException {
        return BaseResult.success(sysCommonGroupService.selectGroupCount());
    }

    /**
     * 管理员获取分组信息
     *
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "groupAdmin", method = RequestMethod.GET)
    @RequiresPermissions("sys:group:query")
    public BaseResult getGroupAdmin(@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
                                    @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws BaseAppException {
        return BaseResult.success(sysCommonGroupService.queryGroupAdmin(pageIndex, pageSize));
    }

    /**
     * 管理员添加分组
     *
     * @param
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @RequiresPermissions("sys:group:add")
    public BaseResult addGroup(SysCommonGroup sysCommonGroup) throws BaseAppException {
        Assert.notNull(sysCommonGroup);
        sysCommonGroupService.addGroup(sysCommonGroup);
        return BaseResult.success(sysCommonGroup);
    }

    /**
     * 管理员修改分组
     *
     * @param
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @RequiresPermissions("sys:group:update")
    public BaseResult updateGroup(SysCommonGroup sysCommonGroup) throws BaseAppException {
        Assert.notNull(sysCommonGroup.getGroupId());
        Long groupId = sysCommonGroup.getGroupId();
        String groupName = sysCommonGroup.getGroupName();
        String bizCode = sysCommonGroup.getBizCode();
        String bizGroupCode = sysCommonGroup.getBizGroupCode();
        String description = sysCommonGroup.getDescription();
        sysCommonGroupService.updateGroup(groupId, groupName, description, bizCode, bizGroupCode);
        return BaseResult.success(sysCommonGroup);
    }

    /**
     * 管理员删除分组
     *
     * @param id
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @RequiresPermissions("sys:group:delete")
    public BaseResult deleteGroup(@RequestParam(value = "id") Long id) throws BaseAppException {
        sysCommonGroupService.deleteGroup(id);
        return BaseResult.success();
    }

}
