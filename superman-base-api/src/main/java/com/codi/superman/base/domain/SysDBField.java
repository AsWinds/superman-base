package com.codi.superman.base.domain;

import com.codi.base.domain.BaseDomain;

/**
 * 数据表字段
 *
 * @author shi.pengyan
 * @date 2017-02-04 15:04
 */
public class SysDBField extends BaseDomain {

    private String columnName;
    private Object columnValue;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Object getColumnValue() {
        return columnValue;
    }

    public void setColumnValue(Object columnValue) {
        this.columnValue = columnValue;
    }
}
