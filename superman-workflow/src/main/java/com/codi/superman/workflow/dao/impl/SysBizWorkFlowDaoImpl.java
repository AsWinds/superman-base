package com.codi.superman.workflow.dao.impl;

import com.codi.base.dao.BaseDAOImpl;
import com.codi.base.dao.plugin.page.PageView;
import com.codi.superman.workflow.dao.SysBizWorkFlowDao;
import com.codi.superman.workflow.domain.SysBizWorkFlow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2017-05-08 15:09
 */
@Slf4j
@Repository
public class SysBizWorkFlowDaoImpl extends BaseDAOImpl<SysBizWorkFlow> implements SysBizWorkFlowDao {
    @Override
    public int deleteByPrimaryKey(Long id) {
        return this.delete(generateStatement("deleteByPrimaryKey"), id);
    }

    @Override
    public int insert(SysBizWorkFlow record) {
        return this.insert(generateStatement("insert"), record);
    }

    @Override
    public int insertSelective(SysBizWorkFlow record) {
        return this.insert(generateStatement("insertSelective"), record);
    }

    @Override
    public SysBizWorkFlow selectByPrimaryKey(Long id) {
        return this.getObject(generateStatement("selectByPrimaryKey"), id);
    }

    @Override
    public int updateByPrimaryKeySelective(SysBizWorkFlow record) {
        return this.update(generateStatement("updateByPrimaryKeySelective"), record);
    }

    @Override
    public int updateByPrimaryKey(SysBizWorkFlow record) {
        return this.update(generateStatement("updateByPrimaryKey"), record);
    }

    @Override
    public List<SysBizWorkFlow> queryList(Long userId, Integer state, Integer pageIndex, Integer pageSize) {
        PageView pageView = this.getPageView(pageIndex, pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put("pageView", pageView);
        map.put("userId", userId);
        if (state != null) {
            map.put("state", state);
        }

        return getSqlSession().selectList(generateStatement("queryList"), map);
    }

    @Override
    public int updateByBiz(SysBizWorkFlow record) {
        return this.update(generateStatement("updateByBiz"), record);
    }
}
