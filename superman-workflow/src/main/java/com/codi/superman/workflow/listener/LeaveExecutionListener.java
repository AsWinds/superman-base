package com.codi.superman.workflow.listener;

import com.codi.base.spring.SpringContextHolder;
import com.codi.superman.workflow.common.LeaveConst;
import com.codi.superman.workflow.service.LeaveService;
import com.codi.superman.workflow.service.WorkFlowService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2017-04-21 13:43
 */
@Slf4j
public class LeaveExecutionListener implements ExecutionListener {

//    @Autowired
//    private LeaveService leaveService;

    @Override
    public void notify(DelegateExecution execution) throws Exception {
        log.debug("notify ...{}", execution);

        // 针对End的Listener
        Object outGoingObj = execution.getVariable("outGoing");
        log.info("outGoing={}", outGoingObj);

        // 通知业务放要么申请成功，要么申请失败
        WorkFlowService workFlowService = SpringContextHolder.getBean(WorkFlowService.class);
        LeaveService leaveService = SpringContextHolder.getBean(LeaveService.class);


        log.debug("leaveService={}", leaveService);
        String bizKey = execution.getProcessBusinessKey();
        Long leaveId = workFlowService.getPrimaryKey(bizKey);

        if (outGoingObj != null) {
            String outGoing = (String) outGoingObj;
            if ("end".equalsIgnoreCase(outGoing)) {
                leaveService.updateState(leaveId, LeaveConst.REJECT);
            }
        } else {
            leaveService.updateState(leaveId, LeaveConst.PASS);
        }
    }
}
