package com.codi.superman.base.dao;

import com.codi.base.dao.BaseDAO;
import com.codi.base.exception.BaseAppException;
import com.codi.superman.base.domain.SysRole;

import java.util.List;

/**
 * SysRole Dao
 *
 * @author shi.pengyan
 * @date 2016-12-22 15:00
 */
public interface SysRoleDao extends BaseDAO<SysRole> {

    int insert(SysRole record) throws BaseAppException;

    SysRole selectByRoleId(Long roleId) throws BaseAppException;

    int updateRole(SysRole record) throws BaseAppException;

    int delRole(Long roleId) throws BaseAppException;

    int updateRoleState(Long roleId, String state) throws BaseAppException;

    Boolean checkRoleCode(String roleCode) throws BaseAppException;

    Long queryRolesCount() throws BaseAppException;

    List<SysRole> queryRoles(Integer pageIndex, Integer pageSize) throws BaseAppException;

    List<SysRole> queryRolesByUserId(Long userId) throws BaseAppException;
}
