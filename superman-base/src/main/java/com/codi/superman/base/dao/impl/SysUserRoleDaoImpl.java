package com.codi.superman.base.dao.impl;

import com.codi.base.dao.BaseDAOImpl;
import com.codi.base.exception.BaseAppException;
import com.codi.superman.base.common.Const;
import com.codi.superman.base.dao.SysUserRoleDao;
import com.codi.superman.base.domain.SysUserRole;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * user role dao impl
 *
 * @author shi.pengyan
 * @date 2016-12-25 12:55
 */

@Repository("sysUserRoleDao")
public class SysUserRoleDaoImpl extends BaseDAOImpl<SysUserRole> implements SysUserRoleDao {
    @Override
    public int insert(SysUserRole record) throws BaseAppException {
        return this.insert(generateStatement("insert"), record);
    }

    @Override
    public int updateState(Long userId, Long roleId, String state) throws BaseAppException {
        SysUserRole record = new SysUserRole();
        record.setUserId(userId);
        record.setRoleId(roleId);
        record.setState(state);
        record.setStateDate(new Date());

        return this.update(generateStatement("updateState"), record);
    }


    @Override
    public int delUserRole(Long userId, Long roleId) throws BaseAppException {
        SysUserRole record = new SysUserRole();
        record.setUserId(userId);
        record.setRoleId(roleId);
        return this.delete(generateStatement("delUserRole"), record);
    }

    @Override
    public List<SysUserRole> queryUserRole(Long userId) throws BaseAppException {
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserId(userId);
        sysUserRole.setState(Const.STATE_A);
        return this.findList(generateStatement("queryUserRole"), sysUserRole);
    }

    @Override
    public Boolean checkRoleExist(Long roleId) {
        return this.getSqlSession().selectOne(generateStatement("checkRoleExist"), roleId);
    }

    @Override
    public Boolean checkUserRoleExist(Long userId, Long roleId) {
        Map<String, Long> map = new HashMap<>();
        map.put("userId", userId);
        map.put("roleId", roleId);

        return this.getSqlSession().selectOne(generateStatement("checkUserRoleExist"), map);
    }
}
