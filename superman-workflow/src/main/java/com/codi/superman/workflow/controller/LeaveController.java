package com.codi.superman.workflow.controller;

import com.codi.base.domain.BaseController;
import com.codi.base.domain.BaseResult;
import com.codi.base.exception.BaseAppException;
import com.codi.base.util.StringUtil;
import com.codi.superman.base.domain.SysUser;
import com.codi.superman.base.util.SessionUtil;
import com.codi.superman.workflow.result.request.SysBizLeaveRequest;
import com.codi.superman.workflow.service.LeaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 请假相关流程
 *
 * @author shi.pengyan
 * @date 2017-04-26 13:43
 */
@Slf4j
@RestController
@RequestMapping("/sys/workflow/biz/leave")
public class LeaveController extends BaseController {

    @Autowired
    private LeaveService leaveService;


    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    public BaseResult apply(@RequestBody SysBizLeaveRequest bizLeaveRequest) throws BaseAppException {
        SysUser loginUser = SessionUtil.getSessionUser();

        bizLeaveRequest.setUserId(loginUser.getUserId());
        bizLeaveRequest.setUserCode(loginUser.getUserCode());
        leaveService.add(bizLeaveRequest, bizLeaveRequest.getAssignee());

        return BaseResult.success();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseResult update(@RequestBody SysBizLeaveRequest bizLeaveRequest) throws BaseAppException {
        SysUser loginUser = SessionUtil.getSessionUser();

        bizLeaveRequest.setUserId(loginUser.getUserId());
        bizLeaveRequest.setUserCode(loginUser.getUserCode());
        leaveService.update(bizLeaveRequest);

        return BaseResult.success();
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public BaseResult queryDetail(@RequestParam(name = "leaveId", required = false) Long leaveId,
                                  @RequestParam(name = "pid", required = false) String pid) {
        if (leaveId != null) {
            return BaseResult.success(leaveService.queryById(leaveId));
        }
        if (StringUtil.isNotEmpty(pid)) {
            return BaseResult.success(leaveService.queryByPid(pid));
        }

        return BaseResult.success(false);
    }

    @RequestMapping(value = "detailByPid", method = RequestMethod.GET)
    public BaseResult queryDetailByPid(@RequestParam(name = "pid") String pid) {
        return BaseResult.success(leaveService.queryByPid(pid));
    }


    @RequestMapping(value = "/outGoing", method = RequestMethod.POST)
    public BaseResult changeState(@RequestParam(name = "pid") String pid,
                                  @RequestParam(name = "outGoing") String outGoing,
                                  @RequestParam(name = "desc") String desc) throws BaseAppException {

        SysUser loginUser = SessionUtil.getSessionUser();
        leaveService.changeOutGoing(pid, loginUser.getUserCode(), outGoing, desc);

        return BaseResult.success();
    }


}
