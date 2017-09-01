package com.codi.superman.base.service.impl;

import com.codi.base.exception.BaseAppException;
import com.codi.base.exception.ExceptionHandler;
import com.codi.base.util.Assert;
import com.codi.base.util.ListUtil;
import com.codi.base.util.MapUtils;
import com.codi.base.util.VersionUtil;
import com.codi.superman.base.common.Const;
import com.codi.superman.base.common.ErrorConst;
import com.codi.superman.base.dao.SysAppDao;
import com.codi.superman.base.dao.SysAppVersionDao;
import com.codi.superman.base.domain.SysAppVersion;
import com.codi.superman.base.result.model.SysAppVersionModel;
import com.codi.superman.base.service.SysAppVersionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * sys app version
 *
 * @author shi.pengyan
 * @date 2016-12-27 19:29
 */
@Service("sysAppVersionService")
@Transactional(readOnly = true)
public class SysAppVersionServiceImpl extends AbstractServiceImpl implements SysAppVersionService {

    @Resource(name = "sysAppVersionDao")
    private SysAppVersionDao sysAppVersionDao;
    @Resource(name = "sysAppDao")
    private SysAppDao sysAppDao;

    @Override
    @Transactional(readOnly = false)
    public SysAppVersion addAppVersion(SysAppVersion sysAppVerion) throws BaseAppException {
        Assert.notNull(sysAppVerion);
        int count = sysAppVersionDao.selectCountByVersionNumberAndAppId(sysAppVerion);
        if (count > 0) {
            ExceptionHandler.publish(ErrorConst.APP_VERSION_ERROR);
        }
        sysAppVerion.setState(Const.STATE_A);

        Date now = new Date();
        sysAppVerion.setUpdateDate(now);
        sysAppVerion.setCreateDate(now);

        sysAppVersionDao.insert(sysAppVerion);
        return sysAppVerion;
    }

    @Override
    @Transactional(readOnly = false)
    public int updateAppVersion(SysAppVersion sysAppVersion) throws BaseAppException {
        Assert.notNull(sysAppVersion);

        sysAppVersion.setUpdateDate(new Date());

        return sysAppVersionDao.updateVersion(sysAppVersion);
    }

    @Override
    @Transactional(readOnly = false)
    public int delAppVersion(Long versionId) throws BaseAppException {
        return sysAppVersionDao.deleteByVersionId(versionId);
    }

    @Override
    public Long queryAppVersionsCount() throws BaseAppException {
        return sysAppVersionDao.queryAppVersionsCount();
    }

    @Override
    public List<SysAppVersionModel> queryAppVersions(Integer pageIndex, Integer pageSize) throws BaseAppException {

        List<Map<String, Object>> list = sysAppVersionDao.queryAppVersions(pageIndex, pageSize);
        List<SysAppVersionModel> result = null;

        if (ListUtil.isNotEmpty(list)) {
            result = new ArrayList<>(list.size());
            for (Map<String, Object> map : list) {
                SysAppVersionModel model = new SysAppVersionModel();

                model.setVersionId(MapUtils.getLong(map, "versionId"));
                model.setAppId(MapUtils.getLong(map, "appId"));
                model.setAppName(MapUtils.getStr(map, "appName"));
                model.setVersionNumber(MapUtils.getStr(map, "versionNumber"));
                model.setVersionDesc(MapUtils.getStr(map, "versionDesc"));
                model.setDownloadPath(MapUtils.getStr(map, "downloadPath"));
                model.setState(MapUtils.getStr(map, "state"));
                model.setForceUpdate(MapUtils.getBoolean(map, "forceUpdate"));
                model.setCreateDate(MapUtils.getDate(map, "createDate"));
                model.setUpdateDate(MapUtils.getDate(map, "updateDate"));

                result.add(model);
            }
        }
        return result;
    }

    @Override
    public SysAppVersion queryLatestVersion(Long appId) throws BaseAppException {
        return sysAppVersionDao.queryLatestVersion(appId);
    }

    @Override
    public SysAppVersion queryLatestVersion(Long appId, String appVersion) throws BaseAppException {
        SysAppVersion latestVersion = sysAppVersionDao.queryLatestVersion(appId);

        if (latestVersion == null) {
            logger.warn("latest version is null");
            return null;
        }

        logger.debug("latest version={},appVersion={}", latestVersion.getVersionNumber(), appVersion);

        if (VersionUtil.compare(latestVersion.getVersionNumber(), appVersion) > 0) {
            return latestVersion;
        }

        return null;
    }

    @Override
    public SysAppVersion queryVersionByAppCode(String appCode, String versionNumber) throws BaseAppException {
        Long appId = sysAppDao.selectAppIdByCode(appCode);
        return sysAppVersionDao.selectByVersionNumberAndAppId(appId, versionNumber);
    }

    @Override
    public SysAppVersion queryLatestVersionByAppCode(String appCode) throws BaseAppException {
        Long appId = sysAppDao.selectAppIdByCode(appCode);
        return sysAppVersionDao.queryLatestVersion(appId);
    }

    @Override
    public SysAppVersion queryLatestVersionByAppCode(String appCode, String appVersion) throws BaseAppException {
        Long appId = sysAppDao.selectAppIdByCode(appCode);
        return this.queryLatestVersion(appId, appVersion);
    }
}
