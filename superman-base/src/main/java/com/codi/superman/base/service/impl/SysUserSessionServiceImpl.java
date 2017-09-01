package com.codi.superman.base.service.impl;

import com.codi.base.exception.BaseAppException;
import com.codi.superman.base.common.Const;
import com.codi.superman.base.dao.SysUserSessionDao;
import com.codi.superman.base.domain.SysUserSession;
import com.codi.superman.base.service.SysUserSessionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author shi.pengyan
 * @date 2016年11月8日 上午11:14:42
 */
@Service("sysUserSessionService")
@Transactional(readOnly = true)
public class SysUserSessionServiceImpl extends AbstractServiceImpl implements SysUserSessionService {

    @Resource(name = "sysUserSessionDao")
    private SysUserSessionDao sysUserSessionDao;

    @Override
    @Transactional(readOnly = false)
    public SysUserSession addSession(Long userId, String userCode, String ua, String token) throws BaseAppException {
        logger.debug("userId={},userCode={},ua={},token={}", userId, userCode, ua, token);
        SysUserSession session = new SysUserSession();

        Date now = new Date();

        session.setUserId(userId);
        session.setToken(token);
        session.setCreateDate(now);
        session.setLastUpdateDate(now);
        session.setState(Const.STATE_A);
        session.setUa(ua);

        sysUserSessionDao.insert(session);

//        String key = CacheUtil.getKey(Const.COMMON_SESSION_PREFIX, session.getToken());
//        CacheUtil.setStrObj(redisTemplate, key, session);

        return session;
    }

    @Override
    public SysUserSession getSession(Long userId, String token) throws BaseAppException {
        //TODO
        return null;
    }

    @Override
    public boolean validate(String token) throws BaseAppException {
//        String key = CacheUtil.getKey(Const.COMMON_SESSION_PREFIX, token);
//        SysUserSession userSession = CacheUtil.getStrObj(redisTemplate, key, SysUserSession.class);
//
//        if (userSession == null) {
//            userSession = sysUserSessionDao.query(token);
//            if (userSession != null) {
//                // DB中有，缓存中没有
//                Date now = new Date();
//                long delta = DateUtils.differDate(now, userSession.getLastUpdateDate(), DateUtils.DIFFER_IN_MINUTE);
//                if (delta < SysUserSessionDao.DEFAULT_SESSION_TIME) {
//                    // session is valid
//                    // update TIME
//                    userSession.setLastUpdateDate(now);
//                    if (delta > SysUserSessionDao.DEFAULT_SESSION_UPDATE_INTERVAL) {
//                        sysUserSessionDao.updateToken(userSession);
//                    }
//                    CacheUtil.setStrObj(redisTemplate, key, userSession);
//
//                    return true;
//                }
//                return false;
//            }
//            return false;
//        }
//
//        Date now = new Date();
//        long delta = DateUtils.differDate(now, userSession.getLastUpdateDate(), DateUtils.DIFFER_IN_MINUTE);
//
//        // session is valid
//        if (delta < SysUserSessionDao.DEFAULT_SESSION_TIME) {
//            // update TIME
//            userSession.setLastUpdateDate(now);
//            if (delta > SysUserSessionDao.DEFAULT_SESSION_UPDATE_INTERVAL) {
//                sysUserSessionDao.updateToken(userSession);
//            }
//            CacheUtil.setStrObj(redisTemplate, key, userSession);
//
//            return true;
//        }

        return true;
    }

    @Override
    public boolean disableToken(String token) throws BaseAppException {
        return false;
    }

//    //TODO
//    private Boolean checkSessionTimeout(SysUserSession sysUserSession, long delta, String key) throws BaseAppException {
//        Date now = new Date();
//        // session is valid
//        if (delta < SysUserSessionDao.DEFAULT_SESSION_TIME) {
//            // update TIME
//            sysUserSession.setLastUpdateDate(now);
//            if (delta > SysUserSessionDao.DEFAULT_SESSION_UPDATE_INTERVAL) {
//                sysUserSessionDao.updateToken(sysUserSession);
//            }
//            CacheUtil.setStrObj(redisTemplate, key, sysUserSession);
//
//            return true;
//        }
//
//        return true;
//    }

}
