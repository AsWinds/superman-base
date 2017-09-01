package com.codi.superman.base.controller;

import com.codi.base.domain.BaseController;
import com.codi.base.domain.BaseResult;
import com.codi.base.exception.BaseAppException;
import com.codi.superman.base.annotation.RequiresPermissions;
import com.codi.superman.base.domain.SysUser;
import com.codi.superman.base.service.*;
import com.codi.superman.base.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户 controller
 *
 * @author shi.pengyan
 * @date 2016-12-26 9:38
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysPrivService sysPrivService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysRolePrivService rolePrivService;


    /**
     * 新增用户
     *
     * @param sysUser
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @RequiresPermissions("sys:user:add")
    public BaseResult addUser(SysUser sysUser) throws BaseAppException {

        sysUserService.addUser(sysUser);

        BaseResult result = new BaseResult();
        //
        return result;
    }

    /**
     * 更新用户信息
     *
     * @param sysUser
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @RequiresPermissions("sys:user:update")
    public BaseResult updateUser(SysUser sysUser) throws BaseAppException {
        sysUserService.updateUser(sysUser);

        return BaseResult.success();
    }

    /**
     * 删除用户
     *
     * @param userId
     * @return
     * @throws BaseAppException
     */
    @RequestMapping("delete")
    @RequiresPermissions("sys:user:delete")
    public BaseResult delUser(@RequestParam(value = "userId") Long userId) throws BaseAppException {
        SysUser sysUser = SessionUtil.getSessionUser();

        sysUserService.delUser(userId, sysUser.getUserId());

        BaseResult result = new BaseResult();
        return result;
    }


    @RequestMapping(value = "lock/{userId}", method = RequestMethod.GET)
    @RequiresPermissions("sys:user:lock")
    public BaseResult lockUser(@PathVariable(value = "userId") Long userId) throws BaseAppException {
        SysUser sysUser = SessionUtil.getSessionUser();

        sysUserService.lockUser(userId, sysUser.getUserId());

        BaseResult result = new BaseResult();
        return result;
    }


    @RequestMapping(value = "unlock/{userId}", method = RequestMethod.GET)
    @RequiresPermissions("sys:user:unlock")
    public BaseResult unlockUser(@PathVariable(value = "userId") Long userId) throws BaseAppException {
        sysUserService.unlockUser(userId);

        BaseResult result = new BaseResult();
        return result;
    }

    /**
     * change user pwd
     *
     * @param oldPwd
     * @param newPwd
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "changePwd", method = RequestMethod.POST)
    public BaseResult changePwd(@RequestParam(value = "oldPwd") String oldPwd, @RequestParam(value = "newPwd") String newPwd) throws BaseAppException {
        SysUser sysUser = SessionUtil.getSessionUser();
        sysUserService.changePwd(sysUser.getUserCode(), oldPwd, newPwd);

        BaseResult result = new BaseResult();
        return result;
    }

    @RequestMapping(value = "resetPwd/{userId}", method = RequestMethod.GET)
    @RequiresPermissions("sys:user:resetPwd")
    public BaseResult resetPwd(@PathVariable(value = "userId") Long userId) throws BaseAppException {
        sysUserService.resetPwd(userId);

        BaseResult result = new BaseResult();
        return result;
    }

    @RequestMapping(value = "{userId}", method = RequestMethod.GET)
    public BaseResult getUser(@PathVariable(value = "userId") Long userId) throws BaseAppException {
        SysUser sysUser = sysUserService.getUser(userId);

        BaseResult result = new BaseResult();
        result.setResult(sysUser);

        return result;
    }

    /**
     * 通过roleId查询用户列表
     *
     * @param roleId
     * @return
     * @throws BaseAppException
     */
    @RequestMapping("userrole/{roleId}")
    public BaseResult getUsersByRoleId(@PathVariable(value = "roleId") Long roleId) throws BaseAppException {

        BaseResult result = new BaseResult();
        result.setResult(sysUserService.getUsersByRoleId(roleId));

        return result;
    }

    /**
     * 获取用户总数
     *
     * @return
     * @throws BaseAppException
     */
    @RequestMapping("/usersCount")
    @RequiresPermissions("sys:user:query")
    public BaseResult getUsersCount() throws BaseAppException {
        BaseResult result = new BaseResult();
        result.setSuccess(true);
        result.setResult(sysUserService.getUsersCount());
        return result;
    }


    /**
     * 获取用户列表，按 user name排序
     *
     * @param pageIndex 当前页码，从1开始
     * @param pageSize  一页显示的记录数，默认20
     * @return
     * @throws BaseAppException
     */
    @RequestMapping("/users")
    @RequiresPermissions("sys:user:query")
    public BaseResult getUsers(@RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
                               @RequestParam(name = "pageSize", defaultValue = "20") Integer pageSize) throws BaseAppException {
        List<SysUser> sysUsers = sysUserService.getUsers(pageIndex, pageSize);
        BaseResult result = new BaseResult();
        result.setResult(sysUsers);
        return result;
    }

    /**
     * 检查用户是否有权限
     *
     * @param path
     * @return
     */
    @RequestMapping(value = "/checkUserMenuPriv", method = RequestMethod.POST)
    public BaseResult checkUserMenuPriv(@RequestParam(name = "path") String path) throws BaseAppException {
        SysUser sysUser = SessionUtil.getSessionUser();

        BaseResult result = new BaseResult();
        result.setResult(sysUserService.checkUserMenuPriv(sysUser.getUserId(), path));

        return result;
    }


}
