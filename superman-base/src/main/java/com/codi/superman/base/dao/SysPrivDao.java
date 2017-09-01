package com.codi.superman.base.dao;

import com.codi.base.dao.BaseDAO;
import com.codi.superman.base.domain.SysPriv;

import java.util.List;

/**
 * 权限DAO
 *
 * @author shi.pengyan
 * @date 2016-12-21 20:33
 */
public interface SysPrivDao extends BaseDAO<SysPriv> {

    Boolean checkPrivCode(String privCode, Long privId);

    int insertPriv(SysPriv record);

    int insertSelective(SysPriv record);

    SysPriv selectByPrivId(Long privId);

    int deleteByPrivId(Long privId);

    int updateByPrimaryKeySelective(SysPriv record);

    int updateByPrimaryKey(SysPriv record);

    Long queryPrivsCount();

    List<SysPriv> queryPrivs(Integer pageIndex, Integer pageSize);


    /**
     * 通过RoleId查询权限
     *
     * @param roleIds
     * @param  privType 权限类型
     * @return
     */
    List<SysPriv> queryPrivsByRoleIds(List<Long> roleIds, Integer privType);

    /**
     * 获取根目录权限列表
     *
     * @return
     */
    List<SysPriv> queryRootPrivs();

    /**
     * 通过userId查询权限列表
     *
     * @param userId
     * @return
     */
    List<SysPriv> queryPrivsByUserId(Long userId);
}
