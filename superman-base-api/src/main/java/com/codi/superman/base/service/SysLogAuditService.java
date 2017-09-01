package com.codi.superman.base.service;

import com.codi.base.exception.BaseAppException;
import com.codi.superman.base.result.model.SysLogAuditModel;

import java.util.List;

/**
 * Sys Log Audit
 *
 * @author shi.pengyan
 * @date 2017-01-09 14:17
 */
public interface SysLogAuditService {

    /**
     * 添加日志
     *
     * @param logType
     * @param userId
     * @param desc
     * @return
     * @throws BaseAppException
     */
    int addLog(String logType, Long userId, String desc) throws BaseAppException;

    /**
     * 查询总数
     *
     * @return
     * @throws BaseAppException
     */
    Long queryLogAuditsCount() throws BaseAppException;

    /**
     * 分页查询
     *
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws BaseAppException
     */
    List<SysLogAuditModel> queryLogAudits(Integer pageIndex, Integer pageSize) throws BaseAppException;
}
