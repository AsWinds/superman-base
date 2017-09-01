package com.codi.superman.base.service;

import com.codi.base.exception.BaseAppException;
import com.codi.superman.base.domain.SysBanner;

import java.util.List;

/**
 * Created by 周翔 on 2017/3/28.
 */

public interface SysBannerService {
    /**
     * 用户得到所有banner
     *
     * @param
     * @return
     * @throws BaseAppException
     */
    List<SysBanner> queryBannersUser() throws BaseAppException;

    /**
     * 管理员得到所有banner
     * @return
     * @throws BaseAppException
     */
    List<SysBanner> queryBannersAdmin() throws BaseAppException;

    /**
     *添加banner
     * @return
     */
    int addBanner(SysBanner sysBanner) throws BaseAppException;

    /**
     * 删除banner
     * @param id
     * @return
     */
    int deleteBanner(Long id) throws BaseAppException;

    /**
     * 更新banner
     * @return
     */
    int updateBanner(SysBanner sysBanner) throws BaseAppException;

    /**
     * banner上线
     *
     * @return
     */
    int onlineBanner(SysBanner sysBanner) throws BaseAppException;
}
