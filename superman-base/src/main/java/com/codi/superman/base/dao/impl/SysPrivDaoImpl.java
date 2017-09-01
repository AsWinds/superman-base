package com.codi.superman.base.dao.impl;

import com.codi.base.dao.BaseDAOImpl;
import com.codi.base.dao.plugin.page.PageView;
import com.codi.superman.base.dao.SysPrivDao;
import com.codi.superman.base.domain.SysPriv;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2016-12-25 13:37
 */
@Repository("sysPrivDao")
public class SysPrivDaoImpl extends BaseDAOImpl<SysPriv> implements SysPrivDao {

    @Override
    public Boolean checkPrivCode(String privCode, Long privId) {
        Map<String, Object> param = new HashMap<>();
        param.put("privCode", privCode);

        if (privId != null) {
            param.put("privId", privId);
        }

        return getSqlSession().selectOne(generateStatement("checkPrivCode"), param);
    }

    @Override
    public int insertPriv(SysPriv record) {
        return this.insert(generateStatement("insert"), record);
    }

    @Override
    public int insertSelective(SysPriv record) {
        return this.insert(generateStatement("insertSelective"), record);
    }

    @Override
    public SysPriv selectByPrivId(Long privId) {
        return this.getObject(generateStatement("selectByPrivId"), privId);
    }

    @Override
    public int deleteByPrivId(Long privId) {
        return this.delete(generateStatement("deleteByPrivId"), privId);
    }

    @Override
    public int updateByPrimaryKeySelective(SysPriv record) {
        return this.update(generateStatement("updateByPrimaryKeySelective"), record);
    }

    @Override
    public int updateByPrimaryKey(SysPriv record) {
        return 0;
    }

    @Override
    public Long queryPrivsCount() {
        return this.getSqlSession().selectOne("queryPrivsCount");
    }

    @Override
    public List<SysPriv> queryPrivs(Integer pageIndex, Integer pageSize) {
        Map<String, Object> param = new HashMap<>();
        if (pageIndex == null && pageSize == null) {

        } else {
            PageView pageView = this.getPageView(pageIndex, pageSize);
            param.put("pageView", pageView);
        }
        return this.findList(generateStatement("queryPrivs"), param);
    }

    @Override
    public List<SysPriv> queryPrivsByRoleIds(List<Long> roleIds, Integer privType) {
        Map<String, Object> param = new HashMap<>();

        param.put("roleIds", roleIds);
        param.put("privType", privType);

        return this.findList(generateStatement("queryPrivsByRoleIds"), param);
    }

    @Override
    public List<SysPriv> queryRootPrivs() {
        return this.findList(generateStatement("queryRootPrivs"));
    }

    @Override
    public List<SysPriv> queryPrivsByUserId(Long userId) {
        return this.findList(generateStatement("queryPrivsByUserId"), userId);
    }
}
