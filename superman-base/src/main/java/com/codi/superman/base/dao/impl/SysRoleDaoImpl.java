package com.codi.superman.base.dao.impl;

import com.codi.base.dao.BaseDAOImpl;
import com.codi.base.dao.plugin.page.PageView;
import com.codi.base.exception.BaseAppException;
import com.codi.superman.base.dao.SysRoleDao;
import com.codi.superman.base.domain.SysRole;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SysRole Dao Impl
 *
 * @author shi.pengyan
 * @date 2016-12-22 15:20
 */
@Repository("sysRoleDao")
public class SysRoleDaoImpl extends BaseDAOImpl<SysRole> implements SysRoleDao {
    @Override
    public int insert(SysRole record) {
        return this.insert(generateStatement("insert"), record);
    }

    @Override
    public SysRole selectByRoleId(Long roleId) {
        return this.getObject(generateStatement("selectByRoleId"), roleId);
    }

    @Override
    public int updateRole(SysRole record) {
        return this.update(generateStatement("updateRole"), record);
    }

    @Override
    public int delRole(Long roleId) throws BaseAppException {
        return this.delete(generateStatement("deleteByPrimaryKey"), roleId);
    }

    @Override
    public int updateRoleState(Long roleId, String state) throws BaseAppException {
        SysRole record = new SysRole();
        record.setRoleId(roleId);
        record.setState(state);
        record.setUpdateDate(new Date());

        return this.update(generateStatement("updateRole"), record);
    }


    @Override
    public Boolean checkRoleCode(String roleCode) throws BaseAppException {
        return getSqlSession().selectOne(generateStatement("checkRoleCode"), roleCode);
    }

    @Override
    public Long queryRolesCount() throws BaseAppException {
        return this.getSqlSession().selectOne(generateStatement("queryRolesCount"));
    }

    @Override
    public List<SysRole> queryRoles(Integer pageIndex, Integer pageSize) throws BaseAppException {
        Map<String, Object> param = new HashMap<>();

        if (pageIndex == null && pageSize == null) {
        } else {
            PageView pageView = this.getPageView(pageIndex, pageSize);
            param.put("pageView", pageView);
        }

        return this.findList(generateStatement("queryRoles"), param);
    }

    @Override
    public List<SysRole> queryRolesByUserId(Long userId) throws BaseAppException {
        return this.findList(generateStatement("queryRolesByUserId"), userId);
    }
}
