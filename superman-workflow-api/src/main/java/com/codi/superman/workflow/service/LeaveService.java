package com.codi.superman.workflow.service;

import com.codi.superman.workflow.domain.SysBizLeave;
import com.codi.superman.workflow.result.request.SysBizLeaveRequest;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2017-04-26 13:45
 */
public interface LeaveService {

    SysBizLeave add(SysBizLeave sysBizLeave, String assignee);

    int update(SysBizLeaveRequest sysBizLeave);

    int updateState(Long leaveId, Integer state);

    SysBizLeave queryById(Long leaveId);

    void changeOutGoing(String pid, String userCode, String outGoing, String desc);

    SysBizLeave queryByPid(String pid);
}
