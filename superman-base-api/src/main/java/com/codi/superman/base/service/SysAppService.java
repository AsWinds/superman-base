package com.codi.superman.base.service;

import com.codi.base.exception.BaseAppException;
import com.codi.superman.base.domain.SysApp;

import java.util.List;

/**
 *  Sys App Service
 *
 * @author shi.pengyan
 * @date 2016-12-27 19:12
 */
public interface SysAppService {

    SysApp addApp(SysApp sysApp) throws BaseAppException;

    int delApp(Long appId) throws BaseAppException;

    int updateApp(SysApp sysApp) throws BaseAppException;

    SysApp queryApp(Long AppId) throws BaseAppException;

    /**
     * 查询所有 SYS APP
     *
     * @return
     * @throws BaseAppException
     */
    List<SysApp> queryApps() throws BaseAppException;
}
