package com.codi.superman.base.dao.impl;

import com.codi.base.dao.BaseDAOImpl;
import com.codi.superman.base.dao.SysAppDao;
import com.codi.superman.base.domain.SysApp;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2016-12-27 17:30
 */
@Repository("sysAppDao")
public class SysAppDaoImpl extends BaseDAOImpl<SysApp> implements SysAppDao {
    @Override
    public SysApp insert(SysApp record) {
        this.insert(generateStatement("insertSelective"), record);
        return record;
    }

    @Override
    public int deleteByAppId(Long appId) {
        return this.delete(generateStatement("deleteByAppId"), appId);
    }

    @Override
    public SysApp selectByAppId(Long appId) {
        return this.getObject(generateStatement("selectByAppId"), appId);
    }

    @Override
    public int updateApp(SysApp record) {
        return this.update(generateStatement("updateApp"), record);
    }

    @Override
    public List<SysApp> selectApps() {
        return this.findList(generateStatement("selectApps"));
    }

    @Override
    public Long selectAppIdByCode(String appCode) {
        return this.getSqlSession().selectOne(generateStatement("selectAppIdByCode"), appCode);
    }
}
