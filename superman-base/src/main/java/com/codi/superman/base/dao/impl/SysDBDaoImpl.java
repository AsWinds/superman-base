package com.codi.superman.base.dao.impl;

import com.codi.base.dao.BaseDAOImpl;
import com.codi.base.util.StringUtil;
import com.codi.superman.base.dao.SysDBDao;
import com.codi.superman.base.domain.SysDB;
import com.codi.superman.base.domain.SysDBField;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用DB
 *
 * @author shi.pengyan
 * @date 2017-02-04 14:41
 */
@Repository("sysDBDao")
public class SysDBDaoImpl extends BaseDAOImpl<SysDB> implements SysDBDao {

    private static final String DEFAULT_PRIMARY_KEY_NAME = "ID";

    @Override
    public Long getCount(String tableName) {
        return getCount(tableName, DEFAULT_PRIMARY_KEY_NAME);
    }

    @Override
    public Long getCount(String tableName, String keyName) {
        return getCount(tableName, keyName, null, null);
    }

    @Override
    public Long getCount(String tableName, String keyName, String columnName, String columnValue) {
        SysDB sysDB = new SysDB();

        sysDB.setTableName(tableName);
        sysDB.setPrimaryKeyName(keyName);

        if (StringUtil.isNotEmpty(columnName)) {
            List<SysDBField> includeFields = new ArrayList<>(1);
            SysDBField field = new SysDBField();
            field.setColumnName(columnName);
            field.setColumnValue(columnValue);
            includeFields.add(field);

            sysDB.setIncludeFields(includeFields);
        }

        return (Long) this.getSqlSession().selectOne(generateStatement("selectCount"), sysDB);
    }

    @Override
    public Long getCount(SysDB sysDB) {
        return (Long) this.getSqlSession().selectOne(generateStatement("selectCount"), sysDB);
    }

    @Override
    public Boolean isUnique(String tableName, String columnName, String columnValue) {
        SysDB sysDB = new SysDB();

        sysDB.setTableName(tableName);

        List<SysDBField> includeFields = new ArrayList<>(1);
        SysDBField field = new SysDBField();
        field.setColumnName(columnName);
        field.setColumnValue(columnValue);
        includeFields.add(field);

        sysDB.setIncludeFields(includeFields);

        return this.getSqlSession().selectOne(generateStatement("isUnique"), sysDB);
    }

    @Override
    public Boolean isUnique(String tableName, String includeColumnName, String includeColumnValue, String excludeColumnName, String excludeColumnValue) {
        SysDB sysDB = new SysDB();

        sysDB.setTableName(tableName);

        List<SysDBField> includeFields = new ArrayList<>(1);
        SysDBField field = new SysDBField();
        field.setColumnName(includeColumnName);
        field.setColumnValue(includeColumnValue);
        includeFields.add(field);
        sysDB.setIncludeFields(includeFields);

        List<SysDBField> excludeFields = new ArrayList<>(1);
        field = new SysDBField();
        field.setColumnName(excludeColumnName);
        field.setColumnValue(excludeColumnValue);

        excludeFields.add(field);

        sysDB.setExcludeFields(excludeFields);

        return this.getSqlSession().selectOne(generateStatement("isUnique"), sysDB);
    }

    @Override
    public Boolean isUnique(SysDB sysDB) {
        return this.getSqlSession().selectOne(generateStatement("isUnique"), sysDB);
    }
}
