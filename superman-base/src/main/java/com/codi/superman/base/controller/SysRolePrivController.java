package com.codi.superman.base.controller;

import com.codi.base.domain.BaseController;
import com.codi.base.domain.BaseResult;
import com.codi.base.exception.BaseAppException;
import com.codi.superman.base.annotation.RequiresPermissions;
import com.codi.superman.base.service.SysRolePrivService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 角色权限
 *
 * @author shi.pengyan
 * @date 2017-01-05 13:10
 */
@RestController
@RequestMapping("/sys/rolepriv")
public class SysRolePrivController extends BaseController {

    @Autowired
    private SysRolePrivService sysRolePrivService;

    /**
     * 绑定角色和权限
     *
     * @param roleId 角色ID
     * @param privId 权限ID
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @RequiresPermissions("sys:rolepriv:add")
    public BaseResult addRolePriv(@RequestParam(value = "roleId") Long roleId,
                                  @RequestParam(value = "privId") Long privId) throws BaseAppException {

        sysRolePrivService.addRolePriv(roleId, privId);
        BaseResult result = new BaseResult();
        return result;
    }

    /**
     * 删除角色和权限
     *
     * @param roleId 角色ID
     * @param privId 权限ID
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "{roleId}/{privId}", method = RequestMethod.DELETE)
    @RequiresPermissions("sys:rolepriv:delete")
    public BaseResult delRolePriv(@PathVariable(value = "roleId") Long roleId,
                                  @PathVariable(value = "privId") Long privId) throws BaseAppException {
        sysRolePrivService.delRolePriv(roleId, privId);

        BaseResult result = new BaseResult();
        return result;
    }

}
