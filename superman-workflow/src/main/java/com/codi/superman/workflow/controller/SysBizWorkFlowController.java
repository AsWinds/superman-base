package com.codi.superman.workflow.controller;

import com.codi.base.domain.BaseResult;
import com.codi.base.exception.BaseAppException;
import com.codi.superman.base.domain.SysUser;
import com.codi.superman.base.util.SessionUtil;
import com.codi.superman.workflow.service.SysBizWorkFlowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2017-05-08 15:12
 */
@Slf4j
@RestController
@RequestMapping("/sys/workflow/biz/wf")
public class SysBizWorkFlowController {

    @Autowired
    private SysBizWorkFlowService sysBizWorkFlowService;

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public BaseResult queryCount(@RequestParam(name = "state", defaultValue = "1") Integer state) throws BaseAppException {
        SysUser sysUser = SessionUtil.getSessionUser();

        return BaseResult.success(sysBizWorkFlowService.queryCount(sysUser.getUserId(), state));
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public BaseResult queryList(
        @RequestParam(name = "state", defaultValue = "1") Integer state,
        @RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
        @RequestParam(name = "pageSize", defaultValue = "20") Integer pageSize) throws BaseAppException {

        SysUser sysUser = SessionUtil.getSessionUser();

        return BaseResult.success(sysBizWorkFlowService.queryList(sysUser.getUserId(), state, pageIndex, pageSize));
    }

}
