package com.codi.superman.base.controller;

import com.codi.base.domain.BaseController;
import com.codi.base.domain.BaseResult;
import com.codi.base.exception.BaseAppException;
import com.codi.superman.base.annotation.RequiresPermissions;
import com.codi.superman.base.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户角色
 *
 * @author shi.pengyan
 * @date 2017-01-05 13:10
 */
@RestController
@RequestMapping("/sys/userrole")
public class SysUserRoleController extends BaseController {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 绑定用户和角色
     *
     * @param userId 用户ID
     * @param roleId 角色ID
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @RequiresPermissions("sys:userrole:add")
    public BaseResult addUserRole(@RequestParam(name = "userId") Long userId,
                                  @RequestParam(name = "roleId") Long roleId) throws BaseAppException {

        sysUserRoleService.addUserRole(userId, roleId);

        BaseResult result = new BaseResult();
        return result;
    }

    /**
     * 删除用户角色权限
     *
     * @param userId 用户ID
     * @param roleId 角色ID
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "{userId}/{roleId}", method = RequestMethod.DELETE)
    @RequiresPermissions("sys:userrole:delete")
    public BaseResult delUserRole(@PathVariable(name = "userId") Long userId,
                                  @PathVariable(name = "roleId") Long roleId) throws BaseAppException {

        sysUserRoleService.delUserRole(userId, roleId);

        BaseResult result = new BaseResult();
        return result;
    }

}
