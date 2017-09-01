package com.codi.superman.base.dao;

import com.codi.base.dao.BaseDAO;
import com.codi.superman.base.domain.SysAppVersion;

import java.util.List;
import java.util.Map;

/**
 * app version dao
 *
 * @author spy
 * @date 2016-12-27 17:13
 */
public interface SysAppVersionDao extends BaseDAO<SysAppVersion> {

    /**
     * 插入版本
     *
     * @param record
     * @return
     */
    SysAppVersion insert(SysAppVersion record);

    /**
     * 删除版本
     *
     * @param versionId
     * @return
     */
    int deleteByVersionId(Long versionId);

    /**
     * 查询版本
     *
     * @param versionId
     * @return
     */
    SysAppVersion selectByVersionId(Long versionId);

    /**
     * 更新版本
     *
     * @param record
     * @return
     */
    int updateVersion(SysAppVersion record);

    /**
     * 查询总数
     *
     * @return
     */
    Long queryAppVersionsCount();

    /**
     * 分页查询
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<Map<String, Object>> queryAppVersions(Integer pageIndex, Integer pageSize);

    /**
     * 查询最新的App version
     *
     * @param appId
     * @return
     */
    SysAppVersion queryLatestVersion(Long appId);

    /**
     * 判断appId是否被引用
     *
     * @param appId
     * @return
     */
    Boolean checkVersionExist(Long appId);

    /**
     * 通过appId和版本号查询一条版本记录
     *
     * @param appId
     * @param versionNumber
     * @return
     */
    SysAppVersion selectByVersionNumberAndAppId(Long appId, String versionNumber);

    /**
     * 用于判断是否有重复的版本记录
     *
     * @param sysAppVersion
     * @return
     */
    int selectCountByVersionNumberAndAppId(SysAppVersion sysAppVersion);
}
