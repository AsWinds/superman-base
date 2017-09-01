package com.codi.superman.workflow.service.impl;

import com.codi.base.domain.BaseServiceImpl;
import com.codi.base.util.Assert;
import com.codi.base.util.StringUtil;
import com.codi.superman.workflow.common.LeaveConst;
import com.codi.superman.workflow.dao.SysBizLeaveDao;
import com.codi.superman.workflow.domain.SysBizLeave;
import com.codi.superman.workflow.result.request.SysBizLeaveRequest;
import com.codi.superman.workflow.service.LeaveService;
import com.codi.superman.workflow.service.SysBizWorkFlowService;
import com.codi.superman.workflow.service.WorkFlowService;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2017-04-26 13:45
 */
@Slf4j
@Service
@Transactional
public class LeaveServiceImpl extends BaseServiceImpl implements LeaveService {

    @Resource
    private SysBizLeaveDao sysBizLeaveDao;

    @Autowired
    private SysBizWorkFlowService sysBizWorkFlowService;

    @Autowired
    private WorkFlowService workFlowService;


    private static final String PROCESS_KEY = "leave_process";

    @Override
    public SysBizLeave add(SysBizLeave sysBizLeave, String assignee) {
        log.debug("assignee={}", assignee);

        Date now = new Date();
        sysBizLeave.setState(LeaveConst.PENDING);
        sysBizLeave.setCreateDate(now);
        sysBizLeave.setUpdateDate(now);

        sysBizLeaveDao.insertSelective(sysBizLeave);


        sysBizWorkFlowService.add(sysBizLeave.getUserId(), sysBizLeave.getUserCode(),
            SysBizLeave.class.getSimpleName(), "" + sysBizLeave.getId(), null);


        //启动流程
        Map<String, Object> vars = Maps.newHashMap();
        vars.put("applyUserId", sysBizLeave.getUserId());
        vars.put("applyUser", sysBizLeave.getUserCode());
        vars.put("inputUser", assignee); //TODO 不是很优雅

        String bizKey = SysBizLeave.class.getSimpleName() + "." + sysBizLeave.getId();
        ProcessInstance processInstance = workFlowService.startProcessByKey(PROCESS_KEY, bizKey, vars);

        //完成申请
        Task task = workFlowService.queryTaskByProcessInstanceId(processInstance.getId());
        Assert.notNull(task);

        workFlowService.completeTask(task.getId());

        return sysBizLeave;
    }

    @Override
    public int update(SysBizLeaveRequest sysBizLeave) {

        sysBizLeave.setCreateDate(null);
        sysBizLeave.setUpdateDate(new Date());

        //完成申请
        Task task = workFlowService.queryTaskByProcessInstanceId(sysBizLeave.getPid());
        Assert.notNull(task);

        workFlowService.completeTask(task.getId());


        return sysBizLeaveDao.updateByPrimaryKeySelective(sysBizLeave);
    }

    @Override
    public int updateState(Long leaveId, Integer state) {
        SysBizLeave leave = new SysBizLeave();

        leave.setId(leaveId);
        leave.setState(state);
        leave.setStateDate(new Date());

        sysBizWorkFlowService.updateState(SysBizLeave.class.getSimpleName(), "" + leaveId, state);
        
        return sysBizLeaveDao.updateByPrimaryKeySelective(leave);
    }

    @Override
    public SysBizLeave queryById(Long leaveId) {
        return sysBizLeaveDao.selectByPrimaryKey(leaveId);
    }

    @Override
    public void changeOutGoing(String pid, String userCode, String outGoing, String desc) {
        Task task = workFlowService.queryTaskByProcessInstanceId(pid);

        //TODO 抛异常
        log.info("pid={},outGoing={},taskId={}", pid, outGoing, task.getId());


        //TODO check outGoing in out list;
        workFlowService.addCommentAndComplete(task.getId(), userCode, desc, outGoing);

        log.info("finish task outGoing");
    }

    @Override
    public SysBizLeave queryByPid(String pid) {
        ProcessInstance processInstance = workFlowService.queryProcessInstance(pid);

        log.debug("processInstance ={}", processInstance);
        String bizKey = processInstance.getBusinessKey();
        if (StringUtil.isNotEmpty(bizKey)) {
            // get leaveId
            String[] bizKeys = bizKey.split("\\.");

            Long leaveId = Long.valueOf(bizKeys[1]);

            return queryById(leaveId);
        }

        return null;
    }


}
