package com.codi.superman.base.dao.impl;

import com.codi.base.dao.BaseDAOImpl;
import com.codi.base.dao.plugin.page.PageView;
import com.codi.superman.base.dao.SysLogAuditDao;
import com.codi.superman.base.domain.SysLogAudit;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 日志审计DAO impl
 *
 * @author shi.pengyan
 * @date 2017-01-09 14:25
 */
@Service("sysLogAuditDao")
public class SysLogAuditDaoImpl extends BaseDAOImpl<SysLogAudit> implements SysLogAuditDao {
    @Override
    public int insert(SysLogAudit record) {
        return this.insert(generateStatement("insertSelective"), record);
    }

    @Override
    public Long queryLogAuditsCount() {
        return this.getSqlSession().selectOne(generateStatement("queryLogAuditsCount"));
    }

    @Override
    public List<Map<String, Object>> queryLogAudits(Integer pageIndex, Integer pageSize) {
        PageView pageView = this.getPageView(pageIndex, pageSize);
        Map<String, Object> param = new HashMap<>();
        param.put("pageView", pageView);
        return this.getSqlSession().selectList(generateStatement("queryLogAudits"), param);
    }
}
