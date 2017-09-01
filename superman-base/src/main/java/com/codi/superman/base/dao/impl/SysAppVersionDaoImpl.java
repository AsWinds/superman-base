package com.codi.superman.base.dao.impl;

import com.codi.base.dao.BaseDAOImpl;
import com.codi.base.dao.plugin.page.PageView;
import com.codi.superman.base.dao.SysAppVersionDao;
import com.codi.superman.base.domain.SysAppVersion;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Sys app version
 *
 * @author shi.pengyan
 * @date 2016-12-27 19:07
 */
@Repository("sysAppVersionDao")
public class SysAppVersionDaoImpl extends BaseDAOImpl<SysAppVersion> implements SysAppVersionDao {
    @Override
    public SysAppVersion insert(SysAppVersion record) {
        this.insert(generateStatement("insertSelective"), record);
        return record;
    }

    @Override
    public int deleteByVersionId(Long versionId) {
        return this.delete(generateStatement("deleteByVersionId"), versionId);
    }

    @Override
    public SysAppVersion selectByVersionId(Long versionId) {
        return this.getObject(generateStatement("selectByVersionId"), versionId);
    }

    @Override
    public int updateVersion(SysAppVersion record) {
        return this.update(generateStatement("updateVersion"), record);
    }

    @Override
    public Long queryAppVersionsCount() {
        return this.getSqlSession().selectOne(generateStatement("queryAppVersionsCount"));
    }

    @Override
    public List<Map<String, Object>> queryAppVersions(Integer pageIndex, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        if (pageIndex != null && pageSize != null) {
            PageView pageView = this.getPageView(pageIndex, pageSize);
            map.put("pageView", pageView);
        }
        return this.getSqlSession().selectList(generateStatement("queryAppVersions"), map);
    }

    @Override
    public SysAppVersion queryLatestVersion(Long appId) {
        return this.getObject(generateStatement("queryLatestVersion"), appId);
    }

    @Override
    public Boolean checkVersionExist(Long appId) {
        return getSqlSession().selectOne(generateStatement("checkVersionExist"), appId);
    }

    @Override
    public SysAppVersion selectByVersionNumberAndAppId(Long appId, String versionNumber) {
        Map<String, Object> map = new HashMap<>();
        map.put("appId", appId);
        map.put("versionNumber", versionNumber);
        return this.getObject(generateStatement("selectByVersionNumberAndAppId"), map);
    }

    @Override
    public int selectCountByVersionNumberAndAppId(SysAppVersion sysAppVersion) {
        return this.getSqlSession().selectOne(generateStatement("selectCountByVersionNumberAndAppId"), sysAppVersion);
    }
}
