package com.codi.superman.base.dao;


import com.codi.base.dao.BaseDAO;
import com.codi.superman.base.domain.SysLogAudit;

import java.util.List;
import java.util.Map;

/**
 * 日志审计Dao
 *
 * @author shi.pengyan
 * @date 2017-01-09 14:14
 */
public interface SysLogAuditDao extends BaseDAO<SysLogAudit> {

    /**
     *插入日志
     * @param record
     * @return
     */
    int insert(SysLogAudit record);

    /**
     * 查询总数
     * @return
     */
    Long queryLogAuditsCount();

    /**
     * 分页查询
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<Map<String, Object>> queryLogAudits(Integer pageIndex, Integer pageSize);

}
