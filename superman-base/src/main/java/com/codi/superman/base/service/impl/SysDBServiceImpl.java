package com.codi.superman.base.service.impl;

import com.codi.superman.base.dao.SysDBDao;
import com.codi.superman.base.domain.SysDB;
import com.codi.superman.base.service.SysDBService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 公共DB层<br/>
 * 总数获取、唯一性校验
 *
 * @author shi.pengyan
 * @date 2017-02-04 15:29
 */
@Transactional(readOnly = true)
@Service("sysDBService")
public class SysDBServiceImpl extends AbstractServiceImpl implements SysDBService {

    @Resource(name = "sysDBDao")
    private SysDBDao sysDBDao;

    @Override
    public Long getCount(String tableName) {
        return sysDBDao.getCount(tableName);
    }

    @Override
    public Long getCount(String tableName, String keyName) {
        return sysDBDao.getCount(tableName, keyName);
    }

    @Override
    public Long getCount(String tableName, String keyName, String columnName, String columnValue) {
        return sysDBDao.getCount(tableName, keyName, columnName, columnValue);
    }

    @Override
    public Long getCount(SysDB sysDB) {
        return sysDBDao.getCount(sysDB);
    }

    @Override
    public Boolean isUnique(String tableName, String columnName, String columnValue) {
        return sysDBDao.isUnique(tableName, columnName, columnValue);
    }

    @Override
    public Boolean isUnique(String tableName, String includeColumnName, String includeColumnValue, String excludeColumnName, String excludeColumnValue) {
        return sysDBDao.isUnique(tableName, includeColumnName, includeColumnValue, excludeColumnName, excludeColumnValue);
    }

    @Override
    public Boolean isUnique(SysDB sysDB) {
        return sysDBDao.isUnique(sysDB);
    }
}
