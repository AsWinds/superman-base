package com.codi.superman.base.service.impl;

import com.codi.base.exception.BaseAppException;
import com.codi.base.util.ListUtil;
import com.codi.base.util.MapUtils;
import com.codi.superman.base.dao.SysLogAuditDao;
import com.codi.superman.base.domain.SysLogAudit;
import com.codi.superman.base.result.model.SysLogAuditModel;
import com.codi.superman.base.service.SysLogAuditService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 日志审计
 *
 * @author shi.pengyan
 * @date 2017-01-09 14:24
 */
@Service("sysLogAuditService")
public class SysLogAuditServiceImpl extends AbstractServiceImpl implements SysLogAuditService {

    @Resource(name = "sysLogAuditDao")
    private SysLogAuditDao sysLogAuditDao;

    @Override
    public int addLog(String logType, Long userId, String desc) throws BaseAppException {
        SysLogAudit log = new SysLogAudit();
        log.setLogType(logType);
        log.setUserId(userId);
        log.setDescription(desc);

        return sysLogAuditDao.insert(log);
    }

    @Override
    public Long queryLogAuditsCount() throws BaseAppException {
        return sysLogAuditDao.queryLogAuditsCount();
    }

    @Override
    public List<SysLogAuditModel> queryLogAudits(Integer pageIndex, Integer pageSize) throws BaseAppException {
        List<Map<String, Object>> list = sysLogAuditDao.queryLogAudits(pageIndex, pageSize);
        List<SysLogAuditModel> result = null;
        if (ListUtil.isNotEmpty(list)) {
            result = new ArrayList<>(list.size());
            for (Map<String, Object> map : list) {
                SysLogAuditModel model = new SysLogAuditModel();
                model.setId(MapUtils.getLong(map, "ID"));
                model.setUserName(MapUtils.getStr(map, "USER_NAME"));
                model.setUserCode(MapUtils.getStr(map, "USER_CODE"));
                model.setCreateDate(MapUtils.getDate(map, "CREATE_DATE"));
                model.setDescription(MapUtils.getStr(map, "DESCRIPTION"));
                model.setLogType(MapUtils.getStr(map, "LOG_TYPE"));
                result.add(model);
            }
        }

        return result;
    }
}
