package com.codi.superman.base.controller;

import com.codi.base.domain.BaseController;
import com.codi.base.domain.BaseResult;
import com.codi.base.exception.BaseAppException;
import com.codi.base.util.Assert;
import com.codi.superman.base.annotation.RequiresPermissions;
import com.codi.superman.base.domain.SysRole;
import com.codi.superman.base.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 角色
 *
 * @author shi.pengyan
 * @date 2016-12-26 9:39
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends BaseController {

    @Autowired
    private SysRoleService sysRoleService;


    /**
     * 新增Role
     *
     * @param roleCode
     * @param roleName
     * @param desc
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @RequiresPermissions("sys:role:add")
    public BaseResult addRole(@RequestParam(value = "roleCode") String roleCode,
                              @RequestParam(value = "roleName") String roleName,
                              @RequestParam(value = "description") String desc) throws BaseAppException {
        sysRoleService.addRole(roleCode, roleName, desc);

        BaseResult result = new BaseResult();

        return result;
    }

    /**
     * 更新Role
     *
     * @param sysRole
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @RequiresPermissions("sys:role:update")
    public BaseResult updateRole(SysRole sysRole) throws BaseAppException {
        Assert.notNull(sysRole);
        Assert.notNull(sysRole.getRoleId());

        sysRoleService.updateRole(sysRole);

        return new BaseResult();
    }

    /**
     * 删除角色
     *
     * @param roleId
     * @return
     * @throws BaseAppException
     */
    @RequestMapping("delete/{roleId}")
    @RequiresPermissions("sys:role:delete")
    public BaseResult delRole(@PathVariable(value = "roleId") Long roleId) throws BaseAppException {
        sysRoleService.delRole(roleId);

        return new BaseResult();
    }

    /**
     * 查询Role
     *
     * @param roleId
     * @return
     * @throws BaseAppException
     */
    @RequestMapping("{roleId}")
    public BaseResult getRole(@PathVariable(value = "roleId") Long roleId) throws BaseAppException {
        BaseResult result = new BaseResult();

        SysRole role = sysRoleService.getRole(roleId);
        role.setCreateDate(null);
        role.setUpdateDate(null);
        role.setState(null);

        result.setResult(role);

        return result;
    }


    /**
     * 通过userId查询用户角色列表
     *
     * @param userId
     * @return
     * @throws BaseAppException
     */
    @RequestMapping("userrole/{userId}")
    public BaseResult getRolesByUserId(@PathVariable(value = "userId") Long userId) throws BaseAppException {
        BaseResult result = new BaseResult();
        result.setResult(sysRoleService.queryRoles(userId));
        return result;
    }

    /**
     * 查询Role总数
     *
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "rolesCount", method = RequestMethod.GET)
    @RequiresPermissions("sys:role:query")
    public BaseResult getRolesCount() throws BaseAppException {
        BaseResult result = new BaseResult();

        result.setResult(sysRoleService.queryRolesCount());
        return result;
    }

    /**
     * 查询角色列表
     *
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "roles", method = RequestMethod.GET)
    @RequiresPermissions("sys:role:query")
    public BaseResult getRoles(@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
                               @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws BaseAppException {
        BaseResult result = new BaseResult();
        result.setResult(sysRoleService.queryRoles(pageIndex, pageSize));

        return result;
    }


}
