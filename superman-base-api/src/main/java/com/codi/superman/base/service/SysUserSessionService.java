package com.codi.superman.base.service;

import com.codi.base.exception.BaseAppException;
import com.codi.superman.base.domain.SysUserSession;

/**
 * 用户会话
 *
 * @author shi.pengyan
 * @date 2016年11月8日 上午10:56:21
 */
public interface SysUserSessionService {

    /**
     * 添加token
     *
     * @param userId   用户ID
     * @param userCode 用户编码
     * @param ua       UA
     * @param token    TOKEN
     * @return
     * @throws BaseAppException
     */
    SysUserSession addSession(Long userId, String userCode, String ua, String token) throws BaseAppException;

    /**
     * 查询当前会话
     *
     * @param userId 用户ID
     * @param token  token
     * @return
     * @throws BaseAppException
     */
    SysUserSession getSession(Long userId, String token) throws BaseAppException;

    /**
     * 验证Token
     *
     * @param token token
     * @return
     * @throws BaseAppException
     */
    boolean validate(String token) throws BaseAppException;

    /**
     * 禁用Token
     *
     * @param token
     * @return
     * @throws BaseAppException
     */
    boolean disableToken(String token) throws BaseAppException;
}
