package com.codi.superman.base.service;

import com.codi.base.exception.BaseAppException;
import com.codi.superman.base.domain.SysRole;

import java.util.List;

/**
 * 角色服务
 *
 * @author shi.pengyan
 * @date 2016-12-22 15:02
 */
public interface SysRoleService {

    /**
     * 添加角色
     *
     * @param roleCode
     * @param roleName
     * @param desc
     * @return
     * @throws BaseAppException
     */
    int addRole(String roleCode, String roleName, String desc) throws BaseAppException;

    /**
     * 删除Role
     *
     * @param roleId
     * @return
     * @throws BaseAppException
     */
    int delRole(Long roleId) throws BaseAppException;

    /**
     * 更新Role
     *
     * @param sysRole
     * @return
     * @throws BaseAppException
     */
    int updateRole(SysRole sysRole) throws BaseAppException;

    /**
     * 禁用role
     *
     * @param roleId
     * @return
     * @throws BaseAppException
     */
    int disableRole(Long roleId) throws BaseAppException;

    /**
     * 查询Role
     *
     * @param roleId
     * @return
     * @throws BaseAppException
     */
    SysRole getRole(Long roleId) throws BaseAppException;

    /**
     * 启用role
     *
     * @param roleId
     * @return
     * @throws BaseAppException
     */
    int enableRole(Long roleId) throws BaseAppException;


    /**
     * 检查角色编码
     *
     * @param roleCode
     * @return
     * @throws BaseAppException
     */
    Boolean checkRoleCode(String roleCode) throws BaseAppException;

    /**
     * 查询角色总数
     *
     * @return
     * @throws BaseAppException
     */
    Long queryRolesCount() throws BaseAppException;

    /**
     * 查询列表,如果参数都为null，则返回所有
     *
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws BaseAppException
     */
    List<SysRole> queryRoles(Integer pageIndex, Integer pageSize) throws BaseAppException;


    /**
     * 查询用户对应的角色
     *
     * @param userId
     * @return
     * @throws BaseAppException
     */
    List<SysRole> queryRoles(Long userId) throws BaseAppException;

}
