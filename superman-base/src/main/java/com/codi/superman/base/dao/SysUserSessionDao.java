package com.codi.superman.base.dao;

import com.codi.base.dao.BaseDAO;
import com.codi.base.exception.BaseAppException;
import com.codi.superman.base.domain.SysUserSession;

/**
 * SysUser Session DAO
 *
 * @author shi.pengyan
 * @date 2016年11月8日 下午1:00:02
 */
public interface SysUserSessionDao extends BaseDAO<SysUserSession> {

    /**
     * 会话有效期30min
     */
    int DEFAULT_SESSION_TIME = 30;

    /**
     * 会话更新时间
     **/
    int DEFAULT_SESSION_UPDATE_INTERVAL = 1;

    /**
     * 插入User Session
     *
     * @param record
     * @return
     * @throws BaseAppException
     */
    int insert(SysUserSession record) throws BaseAppException;

    /**
     * 查询用户session
     *
     * @param token
     * @return
     * @throws BaseAppException
     */
    SysUserSession query(String token) throws BaseAppException;

    /**
     * 更新token
     *
     * @param record
     * @return
     */
    int updateToken(SysUserSession record) throws BaseAppException;

}
