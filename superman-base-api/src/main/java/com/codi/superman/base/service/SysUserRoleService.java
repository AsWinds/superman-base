package com.codi.superman.base.service;

import com.codi.base.exception.BaseAppException;
import com.codi.superman.base.domain.SysUserRole;

import java.util.List;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2016-12-22 15:30
 */
public interface SysUserRoleService {

    /**
     * 添加用户角色
     *
     * @param userId
     * @param roleId
     * @return
     * @throws BaseAppException
     */
    int addUserRole(Long userId, Long roleId) throws BaseAppException;

    /**
     * 批量添加用户角色
     *
     * @param userroles
     * @return
     * @throws BaseAppException
     */
    int addUserRole(List<SysUserRole> userroles) throws BaseAppException;

    /**
     * 禁用用户角色
     *
     * @param userId
     * @param roleId
     * @return
     * @throws BaseAppException
     */
    int disableUserRole(Long userId, Long roleId) throws BaseAppException;

    /**
     * 启用用户角色
     *
     * @param userId
     * @param roleId
     * @return
     * @throws BaseAppException
     */
    int enableUserRole(Long userId, Long roleId) throws BaseAppException;

    /**
     * 删除用户角色
     *
     * @param userId
     * @param roleId
     * @return
     * @throws BaseAppException
     */
    int delUserRole(Long userId, Long roleId) throws BaseAppException;

    /**
     * 查询用户对应的角色列表
     *
     * @param userId
     * @return
     * @throws BaseAppException
     */
    List<SysUserRole> queryUserRole(Long userId) throws BaseAppException;
}
