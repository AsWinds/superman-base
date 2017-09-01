package com.codi.superman.base.dao;

import com.codi.base.dao.BaseDAO;
import com.codi.superman.base.domain.SysApp;

import java.util.List;

/**
 * APP dao
 *
 * @author spy
 * @date 2016-12-27 17:13
 */
public interface SysAppDao extends BaseDAO<SysApp> {

    /**
     * 插入记录
     *
     * @param record
     * @return
     */
    SysApp insert(SysApp record);

    /**
     * 删除记录
     *
     * @param appId
     * @return
     */
    int deleteByAppId(Long appId);

    /**
     * 查询记录
     *
     * @param appId
     * @return
     */
    SysApp selectByAppId(Long appId);

    /**
     * 更新记录
     *
     * @param record
     * @return
     */
    int updateApp(SysApp record);

    /**
     * 查询所有APP
     *
     * @return
     */
    List<SysApp> selectApps();

    /**
     * 通过code查找id
     *
     * @param appCode
     * @return
     */
    Long selectAppIdByCode(String appCode);
}
