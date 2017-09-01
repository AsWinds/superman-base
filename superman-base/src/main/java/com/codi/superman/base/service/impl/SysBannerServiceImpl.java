package com.codi.superman.base.service.impl;

import com.codi.base.exception.BaseAppException;
import com.codi.base.exception.ExceptionHandler;
import com.codi.base.util.Assert;
import com.codi.superman.base.common.ErrorConst;
import com.codi.superman.base.dao.SysBannerDao;
import com.codi.superman.base.domain.SysBanner;
import com.codi.superman.base.service.SysBannerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by 周翔 on 2017/3/28.
 */
@Service("bannerService")
@Transactional(readOnly = true)
public class SysBannerServiceImpl extends AbstractServiceImpl implements SysBannerService {
    @Resource(name = "sysBannerDao")
    private SysBannerDao sysBannerDao;

    public List<SysBanner> queryBannersUser() throws BaseAppException {
        return sysBannerDao.selectAllBannersUser();
    }

    @Override
    public List<SysBanner> queryBannersAdmin() throws BaseAppException {
        return sysBannerDao.selectAllBannersAdmin();
    }

    @Override
    @Transactional(readOnly = false)
    public int addBanner(SysBanner sysBanner) throws BaseAppException {
        Assert.notNull(sysBanner.getImgUrl());
//        Assert.notNull(sysBanner.getUrl());
        Assert.notNull(sysBanner.getName());
        Assert.notNull(sysBanner.getBannerOrder());
        if (sysBannerDao.selectCountByBannerOrder(sysBanner.getBannerOrder(), 0L) > 0) {
            logger.debug("BannerOrder has already exist");
            ExceptionHandler.publish(ErrorConst.BANNER_ORDER_EXIST);
        }
        if (sysBanner.getBannerOrder() < 0 || sysBanner.getBannerOrder() > 99) {
            logger.error("BannerOrder can not exceed two digits");
            ExceptionHandler.publish(ErrorConst.BANNER_ORDER_ERROR);
        }
        Date now = new Date();
        sysBanner.setCreateDate(now);
        sysBanner.setUpdateDate(now);
        sysBanner.setStateDate(now);
        return sysBannerDao.insert(sysBanner);
    }

    @Override
    @Transactional(readOnly = false)
    public int deleteBanner(Long id) throws BaseAppException {
        return sysBannerDao.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public int updateBanner(SysBanner sysBanner) throws BaseAppException {
        Assert.notNull(sysBanner.getImgUrl());
//        Assert.notNull(sysBanner.getUrl());
        Assert.notNull(sysBanner.getName());
        Assert.notNull(sysBanner.getBannerOrder());
        if (sysBannerDao.selectCountByBannerOrder(sysBanner.getBannerOrder(), sysBanner.getId()) > 0) {
            logger.debug("BannerOrder has already exist");
            ExceptionHandler.publish(ErrorConst.BANNER_ORDER_EXIST);
        }
        if (sysBanner.getBannerOrder() < 0 || sysBanner.getBannerOrder() > 99) {
            logger.debug("BannerOrder can not exceed two digits");
            ExceptionHandler.publish(ErrorConst.BANNER_ORDER_ERROR);
        }
        Date now = new Date();
        sysBanner.setUpdateDate(now);
        return sysBannerDao.updateByPrimaryKeySelective(sysBanner);
    }

    @Override
    @Transactional(readOnly = false)
    public int onlineBanner(SysBanner sysBanner) throws BaseAppException {
        if ("A".equals(sysBanner.getState())) {
            Date now = new Date();
            sysBanner.setStateDate(now);
        }
        return sysBannerDao.updateByPrimaryKeySelective(sysBanner);
    }
}
