package com.codi.superman.base.service;

import com.codi.base.exception.BaseAppException;
import com.codi.superman.base.domain.SysAppVersion;
import com.codi.superman.base.result.model.SysAppVersionModel;

import java.util.List;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2016-12-27 19:30
 */
public interface SysAppVersionService {

    SysAppVersion addAppVersion(SysAppVersion sysAppVerion) throws BaseAppException;

    int updateAppVersion(SysAppVersion sysAppVersion) throws BaseAppException;

    int delAppVersion(Long versionId) throws BaseAppException;

    Long queryAppVersionsCount() throws BaseAppException;

    List<SysAppVersionModel> queryAppVersions(Integer pageIndex, Integer pageSize) throws BaseAppException;

    /**
     * 查询最新的版本
     *
     * @param appId
     * @return
     * @throws BaseAppException
     */
    SysAppVersion queryLatestVersion(Long appId) throws BaseAppException;

    /**
     * 查询最新的版本
     *
     * @param appId
     * @param appVersion
     * @return
     * @throws BaseAppException
     */
    SysAppVersion queryLatestVersion(Long appId, String appVersion) throws BaseAppException;

    /**
     * 通过appCode和versionNumber查询版本记录
     *
     * @param appCode
     * @param versionNumber
     * @return
     */
    SysAppVersion queryVersionByAppCode(String appCode, String versionNumber) throws BaseAppException;

    ;

    /**
     * 通过appCode获取最新版本
     *
     * @param appCode
     * @return
     */
    SysAppVersion queryLatestVersionByAppCode(String appCode) throws BaseAppException;

    ;

    /**
     * 通过appCode获取最新版本(版本比较)
     *
     * @param appCode
     * @return
     */
    SysAppVersion queryLatestVersionByAppCode(String appCode, String appVersion) throws BaseAppException;

    ;
}
