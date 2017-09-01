package com.codi.superman.base.dao.impl;

import com.codi.base.dao.BaseDAOImpl;
import com.codi.base.dao.plugin.page.PageView;
import com.codi.superman.base.common.Const;
import com.codi.superman.base.dao.SysParamDao;
import com.codi.superman.base.domain.SysParam;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * param dao impl
 *
 * @author shi.pengyan
 * @date 2016-12-25 18:39
 */
@Repository("sysParamDao")
public class SysParamDaoImpl extends BaseDAOImpl<SysParam> implements SysParamDao {
    @Override
    public SysParam insert(SysParam record) {
        this.insert(generateStatement("insertSelective"), record);
        return record;
    }

    @Override
    public int deleteByParamId(Long paramId) {
        return this.delete(generateStatement("deleteByParamId"), paramId);
    }

    @Override
    public SysParam selectParam(String paramCode) {
        SysParam sysParam = new SysParam();
        sysParam.setParamCode(paramCode);
        sysParam.setState(Const.STATE_A);

        return this.getObject(generateStatement("selectParam"), sysParam);
    }

    @Override
    public List<SysParam> selectParams(String paramCode, String state) {
        SysParam sysParam = new SysParam();
        sysParam.setParamCode(paramCode);
        sysParam.setState(state);

        return this.findList(generateStatement("selectParam"), sysParam);
    }

    @Override
    public int updateParam(SysParam record) {
        return this.update(generateStatement("updateParam"), record);
    }

    @Override
    public int updateParamState(Long paramId, String state) {
        SysParam sysParam = new SysParam();
        sysParam.setId(paramId);
        sysParam.setState(state);
        sysParam.setUpdateDate(new Date());

        return this.update(generateStatement("updateParam"), sysParam);
    }

    @Override
    public Long selectParamsCount() {
        return this.getSqlSession().selectOne(generateStatement("selectParamsCount"));
    }

    @Override
    public List<SysParam> selectParams(Integer pageIndex, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        if (pageIndex == null && pageSize == null) {

        } else {
            PageView pageView = this.getPageView(pageIndex, pageSize);
            map.put("pageView", pageView);
        }

        return this.findList(generateStatement("selectParams"), map);
    }
}
