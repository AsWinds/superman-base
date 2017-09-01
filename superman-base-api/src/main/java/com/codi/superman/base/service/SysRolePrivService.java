package com.codi.superman.base.service;

import com.codi.base.exception.BaseAppException;
import com.codi.superman.base.domain.SysRolePriv;

import java.util.List;

/**
 * SysRolePriv Service
 *
 * @author shi.pengyan
 * @date 2016-12-25 15:51
 */
public interface SysRolePrivService {
    /**
     * 添加角色权限
     *
     * @param roleId
     * @param privId
     * @return
     * @throws BaseAppException
     */
    int addRolePriv(Long roleId, Long privId) throws BaseAppException;

    /**
     * 批量添加角色和权限
     *
     * @param rolePrivs
     * @return
     * @throws BaseAppException
     */
    int addRolePriv(List<SysRolePriv> rolePrivs) throws BaseAppException;

    /**
     * 禁用角色权限
     *
     * @param roleId
     * @param privId
     * @return
     * @throws BaseAppException
     */
    int disableRolePriv(Long roleId, Long privId) throws BaseAppException;

    /**
     * 启用角色权限
     *
     * @param roleId
     * @param privId
     * @return
     * @throws BaseAppException
     */
    int enableRolePriv(Long roleId, Long privId) throws BaseAppException;

    /**
     * 删除角色权限
     *
     * @param roleId
     * @param privId
     * @return
     * @throws BaseAppException
     */
    int delRolePriv(Long roleId, Long privId) throws BaseAppException;
}
