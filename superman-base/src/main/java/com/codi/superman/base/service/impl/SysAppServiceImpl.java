package com.codi.superman.base.service.impl;

import com.codi.base.exception.BaseAppException;
import com.codi.base.exception.ExceptionHandler;
import com.codi.base.util.Assert;
import com.codi.base.util.MD5;
import com.codi.superman.base.common.Const;
import com.codi.superman.base.common.ErrorConst;
import com.codi.superman.base.dao.SysAppDao;
import com.codi.superman.base.dao.SysAppVersionDao;
import com.codi.superman.base.domain.SysApp;
import com.codi.superman.base.service.SysAppService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2016-12-27 19:16
 */
@Service("sysAppService")
@Transactional(readOnly = true)
public class SysAppServiceImpl extends AbstractServiceImpl implements SysAppService {

    @Resource(name = "sysAppDao")
    private SysAppDao sysAppDao;

    @Resource(name = "sysAppVersionDao")
    private SysAppVersionDao sysAppVersionDao;


    @Override
    @Transactional(readOnly = false)
    public SysApp addApp(SysApp sysApp) throws BaseAppException {
        Assert.notNull(sysApp);
        String s = UUID.randomUUID().toString();
        String appSecret = MD5.MD5Encode(s);
        sysApp.setAppSecret(appSecret);
        sysApp.setState(Const.STATE_A);
        Date now = new Date();
        sysApp.setCreateDate(now);
        sysApp.setUpdateDate(now);

        sysAppDao.insert(sysApp);
        return sysApp;
    }

    @Override
    @Transactional(readOnly = false)
    public int delApp(Long appId) throws BaseAppException {
        // 判断是否被引用，不建议删除
        Boolean exist = sysAppVersionDao.checkVersionExist(appId);
        if (exist) {
            ExceptionHandler.publish(ErrorConst.APP_HAS_REF);
        }

        return sysAppDao.deleteByAppId(appId);
    }

    @Override
    @Transactional(readOnly = false)
    public int updateApp(SysApp sysApp) throws BaseAppException {
        Assert.notNull(sysApp);

        sysApp.setUpdateDate(new Date());
        sysApp.setCreateDate(null);
        sysApp.setState(null);

        return sysAppDao.updateApp(sysApp);
    }

    @Override
    public SysApp queryApp(Long appId) throws BaseAppException {
        return sysAppDao.selectByAppId(appId);
    }

    @Override
    public List<SysApp> queryApps() throws BaseAppException {
        return sysAppDao.selectApps();
    }
}
