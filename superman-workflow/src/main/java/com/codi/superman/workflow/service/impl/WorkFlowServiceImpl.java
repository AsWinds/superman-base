package com.codi.superman.workflow.service.impl;

import com.codi.base.util.Assert;
import com.codi.superman.workflow.service.WorkFlowService;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2017-04-18 19:54
 */
@Slf4j
@Transactional
@Service
public class WorkFlowServiceImpl implements WorkFlowService {

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private FormService formService;

    @Autowired
    private HistoryService historyService;


    @Override
    public void deployProcess(File processFile) {
        DeploymentBuilder builder = repositoryService.createDeployment();
        InputStream in = null;
        try {
            in = new FileInputStream(processFile);
            ZipInputStream zipInputStream = new ZipInputStream(in);

            builder.addZipInputStream(zipInputStream);
            builder.deploy();
        } catch (FileNotFoundException e) {
            log.error("fail to find file", e);
        }
    }

    @Override
    public ProcessInstance startProcessByKey(String processKey) {
        return runtimeService.startProcessInstanceByKey(processKey);
    }

    @Override
    public ProcessInstance queryProcessInstance(String processInstanceId) {
        return runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
    }

    @Override
    public ProcessInstance startProcessByKey(String processkey, Map<String, Object> vars) {
        return runtimeService.startProcessInstanceByKey(processkey, vars);
    }

    @Override
    public ProcessInstance startProcessByKey(String processKey, String bizKey, Map<String, Object> vars) {
        return runtimeService.startProcessInstanceByKey(processKey, bizKey, vars);
    }

    @Override
    public Task queryTaskByTaskId(String taskId) {
        log.debug("taskId={}", taskId);
        return taskService.createTaskQuery().taskId(taskId).singleResult();
    }

    @Override
    public Task queryTaskByProcessInstanceId(String pid) {
        log.info("processInstanceId={}", pid);
        return taskService.createTaskQuery().processInstanceId(pid).singleResult();
    }

    @Override
    public String queryTaskImage(String taskId) {
        return null;
    }

    @Override
    public Map<String, Integer> queryCurrentActivityCoordinate(String taskId) {
        log.info("taskId={}", taskId);

        Task task = queryTaskByTaskId(taskId);

        Assert.notNull(task, "should not be null");

        log.info("processInstanceId={}", task.getProcessInstanceId());
        ProcessInstance processInstance = queryProcessInstance(task.getProcessInstanceId());

        String activityId = processInstance.getActivityId();
        log.info("activityId={}", activityId);


        //
//        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) queryProcessDefinition(task.getProcessDefinitionId());
//        List<ActivityImpl> activities = processDefinitionEntity.getActivities();

        //ActivityImpl 是放在运行时中的
        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(task.getProcessDefinitionId());
        ActivityImpl activity = processDefinitionEntity.findActivity(activityId);

        Assert.notNull(activity, "activit should not be null");

        Map<String, Integer> map = Maps.newHashMap();

        map.put("x", activity.getX());
        map.put("y", activity.getY());
        map.put("width", activity.getWidth());
        map.put("height", activity.getHeight());

        return map;
    }

    @Override
    public Map<String, Object> queryTaskForm(String taskId) {
        log.debug("taskId={}", taskId);

        Task task = queryTaskByTaskId(taskId);

        String formKey = task.getFormKey();


        return null;
    }

    @Override
    public Long queryTaskBizId(String taskId) {
        Long bizId = null;

        Object tmp = taskService.getVariable(taskId, "bizId");
        if (tmp != null) {
            bizId = (Long) tmp;
        } else {
            Task task = queryTaskByTaskId(taskId);
            ExecutionEntity execution = (ExecutionEntity) runtimeService.createExecutionQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
            String bizKey = execution.getBusinessKey();

            String[] bizKeys = bizKey.split(".");
            bizId = Long.valueOf(bizKeys[1]);
        }

        log.debug("bizId={}", bizId);

        return bizId;
    }

    @Override
    public void completeTask(String taskId) {
        log.info("taskId={}", taskId);
        taskService.complete(taskId);
    }

    @Override
    public void completeTask(String taskId, String outGoing) {
        log.debug("taskId={},outGoing={}", taskId, outGoing);

        taskService.setVariable(taskId, "outGoing", outGoing);
        taskService.complete(taskId);
    }

    @Override
    public ActivityImpl queryAcitivityImplByTaskId(String taskId) {

        log.info("taskId={}", taskId);

        Task task = queryTaskByTaskId(taskId);

        Assert.notNull(task, "should not be null");

        log.info("processInstanceId={}", task.getProcessInstanceId());
        ProcessInstance processInstance = queryProcessInstance(task.getProcessInstanceId());

        String activityId = processInstance.getActivityId();
        log.info("activityId={}", activityId);


        //
//        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) queryProcessDefinition(task.getProcessDefinitionId());
//        List<ActivityImpl> activities = processDefinitionEntity.getActivities();

        //ActivityImpl 是放在运行时中的
        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(task.getProcessDefinitionId());
        ActivityImpl activity = processDefinitionEntity.findActivity(activityId);

        log.debug("activity is {}", activity);

        return activity;
    }

    @Override
    public List<String> queryTaskTransNames(String taskId) {
        List<String> list = new ArrayList<>();
        // 通过任务获取活动对象
        ActivityImpl acitivity = this.queryAcitivityImplByTaskId(taskId);

        // 通过活动获取所有的出口信息集合
        List<PvmTransition> trans = acitivity.getOutgoingTransitions();
        // 迭代所有出口信息集合, 获取出每个出口的name属性值
        for (PvmTransition tran : trans) {
            String name = (String) tran.getProperty("name");
            if (StringUtils.isNotBlank(name)) {
                list.add(name);
            }
        }
        // 兼容, 如果集合到这里长度还是为0, 那么说明,
        // 当前任务的出口只有一个, 并且这个出口是没有name属性值的
        if (list.size() == 0) {
            list.add("办理任务");
        }
        log.debug("task out going trans is {}", list);

        return list;
    }

    @Override
    public List<String> queryTaskTransNamesByPid(String pid) {
        log.debug("pid={}", pid);
        Task task = taskService.createTaskQuery().processInstanceId(pid).singleResult();
        Assert.notNull(task, "Task is null, please check");

        return queryTaskTransNames(task.getId());
    }

    @Override
    public void transferUserInTask(String taskId, String userCode) {
        log.info("taskId={},userCode={}", taskId, userCode);
        //TODO 校验用户存在与否
        //TODO 校验用户是否有相关的权限

        taskService.setAssignee(taskId, userCode);
    }

    @Override
    public void addComment(String taskId, String userId, String comment) {
        Task task = queryTaskByTaskId(taskId);

        if (StringUtils.isNotBlank(userId)) {
            // 设置用户上下文
            Authentication.setAuthenticatedUserId(userId);
        }
        taskService.addComment(taskId, task.getProcessInstanceId(), comment);
    }

    @Override
    public void addCommentAndComplete(String taskId, String userId, String comment, String outGoing) {
        Assert.notNull(outGoing);

        Task task = queryTaskByTaskId(taskId);

        if (StringUtils.isNotBlank(userId)) {
            // 设置用户上下文
            Authentication.setAuthenticatedUserId(userId);
        }


        // outGoing // 可能需要同时完成task；添加comment应该在complete之前

        taskService.addComment(taskId, task.getProcessInstanceId(), comment);

        completeTask(taskId, outGoing);
    }

    @Override
    public List<Comment> queryComments(String taskId) {
        return taskService.getTaskComments(taskId);
    }

    @Override
    public List<Comment> queryProcessInstanceComments(String pid) {
        return taskService.getProcessInstanceComments(pid);
    }

    @Override
    public Long getPrimaryKey(String bizKey) {
        // get biz id
        String[] bizKeys = bizKey.split("\\.");
        return Long.valueOf(bizKeys[1]);
    }

    @Override
    public Long queryProcessIntanceCount() {

        return runtimeService.createProcessInstanceQuery().count();
    }

    @Override
    public List<ProcessInstance> queryProcessIntanceList(Integer pageIndex, Integer pageSize) {

        return runtimeService.createProcessInstanceQuery()
            .orderByProcessInstanceId()
            .desc()
            .listPage(getBeginIndex(pageIndex, pageSize), pageSize);
    }

    @Override
    public Long queryProcessDefinitionCount() {
        return repositoryService.createProcessDefinitionQuery().latestVersion().count();
    }

    @Override
    public List<ProcessDefinition> queryProcessDefinitions(Integer pageIndex, Integer pageSize) {
        if (pageSize > 100) {
            pageSize = 10;
        }
        List<ProcessDefinition> result = repositoryService.createProcessDefinitionQuery()
            .latestVersion()
//            .orderByProcessDefinitionVersion()
            .orderByProcessDefinitionName()
            .asc()
            .listPage(getBeginIndex(pageIndex, pageSize), pageSize);

        log.debug("list={}", result);

        return result;
    }

    @Override
    public ProcessDefinition queryProcessDefinition(String processDefinitionId) {
        return repositoryService.createProcessDefinitionQuery()
            .processDefinitionId(processDefinitionId)
            .singleResult();
    }

    @Override
    public void deleteProcessDefinition(String deploymentId) {
        log.info("delete process definition={}", deploymentId);
        //TODO 检测是否在用

        repositoryService.deleteDeployment(deploymentId);
    }

    @Override
    public InputStream queryProcessDefinitionImage(String deploymentId, String resourceName) {

        //TODO 本地缓存
        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).singleResult();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(pd.getId());

//
//        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
//        //流程定义
//        BpmnModel bpmnModel = processEngine.getRepositoryService().getBpmnModel(task.getProcessDefinitionId());
//
//        //正在活动节点
//        List<String> activeActivityIds = processEngine.getRuntimeService().getActiveActivityIds(task.getExecutionId());
//        ProcessDiagramGenerator pdg = processEngine.getProcessEngineConfiguration().getProcessDiagramGenerator();

        //得到图片输出流（这样加可防止生成的流程图片乱码）
        InputStream inputStream = processEngine.getProcessEngineConfiguration().getProcessDiagramGenerator()
            .generateDiagram(bpmnModel, "png",
                processEngine.getProcessEngineConfiguration().getActivityFontName(),
                processEngine.getProcessEngineConfiguration().getLabelFontName(),
                processEngine.getProcessEngineConfiguration().getAnnotationFontName(),
                processEngine.getProcessEngineConfiguration().getClassLoader(), 1.0d);

        return inputStream;
//        return repositoryService.getResourceAsStream(deploymentId, resourceName);
    }

    @Override
    public List<HistoricProcessInstance> queryHistoryProcess(String pid, Integer pageIndex, Integer pageSize) {
        return historyService.createHistoricProcessInstanceQuery()
            .processInstanceId(pid)
            .orderByProcessInstanceStartTime()
            .desc()
            .listPage(getBeginIndex(pageIndex, pageSize), pageSize);
    }

    @Override
    public Long queryHistoryProcessCount(String pid) {
        //TODO 参数与上面保持一致
        return historyService.createHistoricProcessInstanceQuery()
            .processInstanceId(pid)
            .count();
    }

    @Override
    public List<HistoricTaskInstance> queryHistoryTask(String taskId, Integer pageIndex, Integer pageSize) {


        return historyService.createHistoricTaskInstanceQuery()
            .taskId(taskId)
            .orderByTaskCreateTime()
            .desc()
            .listPage(getBeginIndex(pageIndex, pageSize), pageSize);
    }

    @Override
    public Long queryHistoryTaskCount(String taskId) {
        return historyService.createHistoricTaskInstanceQuery()
            .taskId(taskId)
            .count();
    }

    @Override
    public Long queryPersonalTaskCount(String userCode) {
        return taskService.createTaskQuery().taskAssignee(userCode).count();
    }

    @Override
    public List<Task> queryPersonalTask(String userCode, Integer pageIndex, Integer pageSize) {

        return taskService.createTaskQuery().taskAssignee(userCode)
            .orderByTaskCreateTime().desc()
            .listPage(getBeginIndex(pageIndex, pageSize), pageSize);
    }


    @Override
    public ProcessDefinition queryProcessDefinitionByTaskId(String taskId) {
        // taskId -> Task
        Task task = queryTaskByTaskId(taskId);

        Assert.notNull(true, "Task should not be null");

        // task -> processDefinitionId
        log.debug("processDefinitionId={}", task.getProcessDefinitionId());

        // processDefinitionId -> processDefinetion
        return queryProcessDefinition(task.getProcessDefinitionId());
    }


    private Integer getBeginIndex(Integer pageIndex, Integer pageSize) {
        return (pageIndex - 1) * pageSize;
    }

}
