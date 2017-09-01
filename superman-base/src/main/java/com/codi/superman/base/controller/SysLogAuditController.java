package com.codi.superman.base.controller;

import com.codi.base.domain.BaseController;
import com.codi.base.domain.BaseResult;
import com.codi.base.exception.BaseAppException;
import com.codi.superman.base.annotation.RequiresPermissions;
import com.codi.superman.base.result.model.SysLogAuditModel;
import com.codi.superman.base.service.SysLogAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 日志审计
 *
 * @author shi.pengyan
 * @date 2017-01-09 14:34
 */
@RestController
@RequestMapping("/sys/logAudit")
public class SysLogAuditController extends BaseController {

    @Autowired
    private SysLogAuditService sysLogAuditService;

    /**
     * 查询总数
     *
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "/logAuditsCount", method = RequestMethod.GET)
    @RequiresPermissions("sys:logAudit:query")
    public BaseResult getLogAuditsCount() throws BaseAppException {
        BaseResult result = new BaseResult();
        result.setResult(sysLogAuditService.queryLogAuditsCount());
        return result;
    }

    /**
     * 分页查询
     *
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "/logAudits", method = RequestMethod.GET)
    @RequiresPermissions("sys:logAudit:query")
    public BaseResult getLogAudits(@RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
                                   @RequestParam(name = "pageSize", defaultValue = "20") Integer pageSize) throws BaseAppException {
        List<SysLogAuditModel> list = sysLogAuditService.queryLogAudits(pageIndex, pageSize);

        BaseResult result = new BaseResult();
        result.setResult(list);

        return result;
    }
}
