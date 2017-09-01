package com.codi.superman.base.dao.impl;

import com.codi.base.dao.BaseDAOImpl;
import com.codi.base.exception.BaseAppException;
import com.codi.superman.base.dao.SysUserSessionDao;
import com.codi.superman.base.domain.SysUserSession;
import org.springframework.stereotype.Repository;

/**
 * @author shi.pengyan
 * @date 2016年11月8日 下午1:02:08
 */
@Repository("sysUserSessionDao")
public class SysUserSessionDaoImpl extends BaseDAOImpl<SysUserSession> implements SysUserSessionDao {

    @Override
    public int insert(SysUserSession record) throws BaseAppException {
        return insert(generateStatement("insert"), record);
    }

    @Override
    public SysUserSession query(String token) throws BaseAppException {
        return getObject(generateStatement("selectByToken"), token);
    }

    @Override
    public int updateToken(SysUserSession record) throws BaseAppException {
        return update(generateStatement("updateToken"), record);
    }

}
