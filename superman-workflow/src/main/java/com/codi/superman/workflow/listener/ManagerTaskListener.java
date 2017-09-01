package com.codi.superman.workflow.listener;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2017-04-21 13:11
 */
@Slf4j
public class ManagerTaskListener implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        //TODO
        log.debug("manager task listener...");
    }
}
