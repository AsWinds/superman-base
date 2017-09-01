package com.codi.superman.base.dao;

import com.codi.base.dao.BaseDAO;
import com.codi.superman.base.domain.SysRolePriv;

/**
 * SysRole SysPriv
 *
 * @author shi.pengyan
 * @date 2016-12-22 15:11
 */
public interface SysRolePrivDao extends BaseDAO<SysRolePriv> {

    /**
     * 增加角色权限关联
     *
     * @param roleId
     * @param privId
     * @return
     */
    int insert(Long roleId, Long privId);

    /**
     * 更新状态
     *
     * @param roleId
     * @param privId
     * @param state
     * @return
     */
    int updateState(Long roleId, Long privId, String state);

    /**
     * 删除关联
     *
     * @param roleId
     * @param privId
     * @return
     */
    int delRolePriv(Long roleId, Long privId);

    /**
     * 判断Role是否被引用
     *
     * @param roleId
     * @return
     */
    Boolean checkRoleExist(Long roleId);

    /**
     * 判断Priv是否被引用
     *
     * @param privId
     * @return
     */
    Boolean checkPrivExist(Long privId);

    /**
     * 判断角色权限是否存在
     *
     * @param roleId
     * @param privId
     * @return
     */
    Boolean checkRolePrivExist(Long roleId, Long privId);
}
