package com.codi.superman.base.dao.impl;

import com.codi.base.dao.BaseDAOImpl;
import com.codi.base.dao.plugin.page.PageView;
import com.codi.superman.base.dao.SysBulletinDao;
import com.codi.superman.base.domain.SysBulletin;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 周翔 on 2017/4/7.
 */
@Repository("sysBulletinDao")
public class SysBulletinDaoImpl extends BaseDAOImpl<SysBulletin> implements SysBulletinDao {

    @Override
    public int deleteByPrimaryKey(Long id) {
        return this.delete(this.generateStatement("deleteByPrimaryKey"), id);
    }

    @Override
    public int insert(SysBulletin record) {
        return this.insert(this.generateStatement("insert"), record);
    }


    @Override
    public SysBulletin selectByPrimaryKey(Long id) {
        return this.getObject(generateStatement("selectByPrimaryKey"), id);
    }


    @Override
    public int updateByPrimaryKeySelective(SysBulletin record) {
        return this.update(this.generateStatement("updateByPrimaryKeySelective"), record);
    }

    @Override
    public List<Map<String, Object>> selectAllBulletinAdmin(Long groupId, Integer pageIndex, Integer pageSize) {
        Map<String, Object> param = new HashMap<>();
        if (pageIndex == null && pageSize == null) {

        } else {
            PageView pageView = this.getPageView(pageIndex, pageSize);
            param.put("pageView", pageView);
            param.put("groupId", groupId);
        }
        return this.getSqlSession().selectList(generateStatement("selectAllBulletinAdmin"), param);
    }

    @Override
    public int selectBulletinCount() {
        return this.getSqlSession().selectOne(generateStatement("selectBulletinCount"));
    }

    @Override
    public int selectCountByGroupId(Long groupId) {
        return this.getSqlSession().selectOne(generateStatement("selectCountByGroupId"), groupId);
    }


    @Override
    public Map<String, Object> selectBulletinByGroupId(Long groupId, Integer pageIndex, Integer pageSize) {
        Map<String, Object> param = new HashMap<>();
        if (pageIndex == null && pageSize == null) {

        } else {
            PageView pageView = this.getPageView(pageIndex, pageSize);
            param.put("pageView", pageView);
            param.put("groupId", groupId);
        }
        List<SysBulletin> list = this.getSqlSession().selectList(generateStatement("selectBulletinByGroupId"), param);
        logger.debug(param);
        Map<String, Object> result = new HashMap<>();
        PageView pageView1 = (PageView) param.get("pageView");
        result.put("list", list);
        result.put("count", pageView1.getRowCount());
        return result;
    }

    @Override
    public SysBulletin selectBulletinByIdUser(Long id) {
        return this.getObject(this.generateStatement("selectBulletinByIdUser"), id);
    }
}
