package com.codi.superman.base.service;

import com.codi.superman.base.domain.SysDB;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2017-02-04 15:28
 */
public interface SysDBService {

    /**
     * 获取记录总数,主键为ID的场景
     *
     * @param tableName
     * @return
     */
    Long getCount(String tableName);

    /**
     * 支持自定义主键
     *
     * @param tableName
     * @param keyName
     * @return
     */
    Long getCount(String tableName, String keyName);

    /**
     * 获取带限制条件的记录总数
     *
     * @param tableName
     * @param keyName
     * @param columnName
     * @param columnValue
     * @return
     */
    Long getCount(String tableName, String keyName, String columnName, String columnValue);

    /**
     * 【高级API】获取记录数
     *
     * @param sysDB
     * @return
     */
    Long getCount(SysDB sysDB);

    /**
     * 判断是否已存在columnName=columnValue的记录
     *
     * @param tableName
     * @param columnName
     * @param columnValue
     * @return
     */
    Boolean isUnique(String tableName, String columnName, String columnValue);

    /**
     * 判断是否已存在除columnName=columnValue的记录
     *
     * @param tableName
     * @param excludeColumnName
     * @param excludeColumnValue
     * @return
     */
    Boolean isUnique(String tableName, String includeColumnName, String includeColumnValue, String excludeColumnName, String excludeColumnValue);

    /**
     * 【高级API】判断是否已经存在记录
     *
     * @param sysDB
     * @return
     */
    Boolean isUnique(SysDB sysDB);
}
