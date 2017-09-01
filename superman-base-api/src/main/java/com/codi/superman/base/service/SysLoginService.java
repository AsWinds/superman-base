package com.codi.superman.base.service;

import com.codi.base.exception.BaseAppException;
import com.codi.superman.base.domain.SysUser;

/**
 * 登陆服务
 *
 * @author shi.pengyan
 * @date 2016-12-26 10:05
 */
public interface SysLoginService {
    /*锁定用户时间*/
    int LOCK_USER_MINTUE = 30;

    /**
     * 登陆服务
     *
     * @param userCode
     * @param pwd
     * @return
     * @throws BaseAppException
     */
    SysUser login(String userCode, String pwd) throws BaseAppException;


    /**
     * 注销接口
     *
     * @param loginUser 登陆用户
     * @throws BaseAppException
     */
    void logout(SysUser loginUser) throws BaseAppException;
}
