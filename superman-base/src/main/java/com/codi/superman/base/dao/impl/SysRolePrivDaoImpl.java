package com.codi.superman.base.dao.impl;

import com.codi.base.dao.BaseDAOImpl;
import com.codi.superman.base.common.Const;
import com.codi.superman.base.dao.SysRolePrivDao;
import com.codi.superman.base.domain.SysRolePriv;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *  sys role priv dao impl
 *
 * @author shi.pengyan
 * @date 2016-12-25 15:44
 */
@Repository("sysRolePrivDao")
public class SysRolePrivDaoImpl extends BaseDAOImpl<SysRolePriv> implements SysRolePrivDao {
    @Override
    public int insert(Long roleId, Long privId) {
        SysRolePriv sysRolePriv = new SysRolePriv();
        sysRolePriv.setRoleId(roleId);
        sysRolePriv.setPrivId(privId);

        sysRolePriv.setState(Const.STATE_A);

        Date now = new Date();
        sysRolePriv.setCreateDate(now);
        sysRolePriv.setUpdateDate(now);

        return this.insert(generateStatement("insert"), sysRolePriv);
    }

    @Override
    public int updateState(Long roleId, Long privId, String state) {
        SysRolePriv sysRolePriv = new SysRolePriv();
        sysRolePriv.setRoleId(roleId);
        sysRolePriv.setPrivId(privId);

        sysRolePriv.setState(state);
        Date now = new Date();
        sysRolePriv.setUpdateDate(now);

        return this.update(generateStatement("updateState"), sysRolePriv);
    }

    @Override
    public int delRolePriv(Long roleId, Long privId) {
        SysRolePriv sysRolePriv = new SysRolePriv();
        sysRolePriv.setRoleId(roleId);
        sysRolePriv.setPrivId(privId);

        return this.delete(generateStatement("delRolePriv"), sysRolePriv);
    }

    @Override
    public Boolean checkRoleExist(Long roleId) {
        return this.getSqlSession().selectOne(generateStatement("checkRoleExist"), roleId);
    }

    @Override
    public Boolean checkPrivExist(Long privId) {
        return this.getSqlSession().selectOne(generateStatement("checkPrivExist"), privId);
    }

    @Override
    public Boolean checkRolePrivExist(Long roleId, Long privId) {
        Map<String, Long> map = new HashMap<>();
        map.put("roleId", roleId);
        map.put("privId", privId);

        return this.getSqlSession().selectOne(generateStatement("checkRolePrivExist"), map);
    }
}
