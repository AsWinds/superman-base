package com.codi.superman.base.domain;

import com.codi.base.domain.BaseDomain;

import java.util.List;

/**
 * 通用DB
 *
 * @author shi.pengyan
 * @date 2017-02-04 14:40
 */
public class SysDB extends BaseDomain {

    private String tableName;
    private String primaryKeyName;
    private Object primaryKeyValue;

    private List<SysDBField> includeFields; // 包含的字段
    private List<SysDBField> excludeFields; // 排除的字段值

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPrimaryKeyName() {
        return primaryKeyName;
    }

    public void setPrimaryKeyName(String primaryKeyName) {
        this.primaryKeyName = primaryKeyName;
    }

    public Object getPrimaryKeyValue() {
        return primaryKeyValue;
    }

    public void setPrimaryKeyValue(Object primaryKeyValue) {
        this.primaryKeyValue = primaryKeyValue;
    }

    public List<SysDBField> getIncludeFields() {
        return includeFields;
    }

    public void setIncludeFields(List<SysDBField> includeFields) {
        this.includeFields = includeFields;
    }

    public List<SysDBField> getExcludeFields() {
        return excludeFields;
    }

    public void setExcludeFields(List<SysDBField> excludeFields) {
        this.excludeFields = excludeFields;
    }
}
