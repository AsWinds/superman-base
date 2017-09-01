package com.codi.superman.base.dao;

import com.codi.base.dao.BaseDAO;
import com.codi.base.exception.BaseAppException;
import com.codi.superman.base.domain.SysUserRole;

import java.util.List;

/**
 * SysUser role dao
 *
 * @author shi.pengyan
 * @date 2016-12-22 15:10
 */
public interface SysUserRoleDao extends BaseDAO<SysUserRole> {

    int insert(SysUserRole record) throws BaseAppException;

    int updateState(Long userId, Long roleId, String state) throws BaseAppException;

    int delUserRole(Long userId, Long roleId) throws BaseAppException;

    /**
     * 查询用户的角色
     *
     * @param userId
     * @return
     * @throws BaseAppException
     */
    List<SysUserRole> queryUserRole(Long userId) throws BaseAppException;


    /**
     * 判断role是否被引用
     *
     * @param roleId
     * @return
     */
    Boolean checkRoleExist(Long roleId);

    /**
     * 判断用户角色是否存在
     *
     * @param userId
     * @param roleId
     * @return
     */
    Boolean checkUserRoleExist(Long userId, Long roleId);
}
