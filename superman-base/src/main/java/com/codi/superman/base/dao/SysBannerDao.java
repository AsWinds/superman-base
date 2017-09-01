package com.codi.superman.base.dao;

import com.codi.base.dao.BaseDAO;
import com.codi.superman.base.domain.SysBanner;

import java.util.Date;
import java.util.List;

/**
 * 亲，写个类注释呗
 *
 * @author 周翔
 * @date 2017-03-28 10:18
 */
public interface SysBannerDao extends BaseDAO<SysBanner> {
    /**
     * 对banner记录进行删除
     *
     * @param id bannerID
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 对 banner 进行添加
     *
     * @param record
     * @return
     */
    int insert(SysBanner record);


    /**
     * 更新经过更改的banner
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(SysBanner record);


    /**
     * 用户得到的所有banner信息
     *
     * @return
     */
    List<SysBanner> selectAllBannersUser();

    /**
     * 管理员得到的所有banner信息
     *
     * @return
     */
    List<SysBanner> selectAllBannersAdmin();

    /**
     * 用户判断bannerOrder是否重复
     *
     * @param bannerOrder
     * @return
     */
    int selectCountByBannerOrder(int bannerOrder, Long id);

    Long selectIdByBannerOrder(int bannerOrder);
}
