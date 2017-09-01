package com.codi.superman.base.dao.impl;

import com.codi.base.dao.BaseDAOImpl;
import com.codi.base.dao.plugin.page.PageView;
import com.codi.superman.base.dao.SysCommonGroupDao;
import com.codi.superman.base.domain.SysCommonGroup;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 周翔 on 2017/4/12.
 */
@Repository("sysCommonGroupDao")
public class SysCommonGroupDaoImpl extends BaseDAOImpl<SysCommonGroup> implements SysCommonGroupDao {
    @Override
    public int deleteByPrimaryKey(Long groupId) {
        return this.delete(this.generateStatement("deleteByPrimaryKey"), groupId);
    }

    @Override
    public int insert(SysCommonGroup record) {
        return this.insert(this.generateStatement("insert"), record);
    }

    @Override
    public int updateByPrimaryKeySelective(SysCommonGroup record) {
        return this.update(this.generateStatement("updateByPrimaryKeySelective"), record);
    }

    @Override
    public List<SysCommonGroup> selectAllGroup(Integer pageIndex, Integer pageSize) {
        Map<String, Object> param = new HashMap<>();
        if (pageIndex == null && pageSize == null) {

        } else {
            PageView pageView = this.getPageView(pageIndex, pageSize);
            param.put("pageView", pageView);
        }
        return this.findList(this.generateStatement("selectAllGroup"), param);
    }

    @Override
    public List<SysCommonGroup> selectAllGroup() {
        return this.findList(this.generateStatement("selectAllGroup"));
    }
    @Override
    public List<SysCommonGroup> selectByBizGroupCode(String bizGroupCode) {
        return this.findList(this.generateStatement("selectByBizGroupCode"), bizGroupCode);
    }

    @Override
    public Long selectGroupIdByBizCode(String bizCode) {
        Long groupId = this.getSqlSession().selectOne(generateStatement("selectGroupIdByBizCode"), bizCode);
        return groupId;
    }

    @Override
    public int selectCountByBizCode(String bizCode) {
        return this.getSqlSession().selectOne(generateStatement("selectCountByBizCode"), bizCode);
    }

    @Override
    public int selectGroupCount() {
        return this.getSqlSession().selectOne(generateStatement("selectGroupCount"));
    }
}
