package com.codi.superman.base.dao.impl;

import com.codi.base.dao.BaseDAOImpl;
import com.codi.superman.base.dao.SysBannerDao;
import com.codi.superman.base.domain.SysBanner;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 周翔 on 2017/3/28.
 */
@Repository("sysBannerDao")
public class SysBannerDaoImpl extends BaseDAOImpl<SysBanner> implements SysBannerDao {
    @Override
    public int deleteByPrimaryKey(Long id) {
        return this.delete(generateStatement("deleteByPrimaryKey"), id);
    }

    @Override
    public int insert(SysBanner record) {
        return this.insert(generateStatement("insert"), record);
    }


    @Override
    public List<SysBanner> selectAllBannersUser() {
        return this.findList(this.generateStatement("selectAllBannersUser"));
    }

    @Override
    public List<SysBanner> selectAllBannersAdmin() {
        return this.findList(this.generateStatement("selectAllBannersAdmin"));
    }

    @Override
    public int selectCountByBannerOrder(int bannerOrder, Long id) {
        Map<String, Object> map = new HashMap<>();
        map.put("bannerOrder", bannerOrder);
        map.put("id", id);
        return this.getSqlSession().selectOne(generateStatement("selectCountByBannerOrder"), map);
    }

    @Override
    public Long selectIdByBannerOrder(int bannerOrder) {

        return this.getSqlSession().selectOne(generateStatement("selectIdByBannerOrder"), bannerOrder);
    }

    @Override
    public int updateByPrimaryKeySelective(SysBanner record) {
        return this.update(this.generateStatement("updateByPrimaryKeySelective"), record);
    }


}
