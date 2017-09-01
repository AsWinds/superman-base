package com.codi.superman.workflow.service;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 流程管理
 *
 * @author shi.pengyan
 * @date 2017-04-18 19:46
 */
public interface WorkFlowService {

    /**
     * 部署流程
     *
     * @param processFile
     */
    void deployProcess(final File processFile);

    /**
     * 启动流程
     *
     * @param processKey
     */
    ProcessInstance startProcessByKey(final String processKey);


    /**
     * 查询流程实例
     *
     * @param processInstanceId
     * @return
     */
    ProcessInstance queryProcessInstance(final String processInstanceId);

    /**
     * 启动流程
     *
     * @param processkey
     * @param vars
     * @return
     */
    ProcessInstance startProcessByKey(final String processkey, final Map<String, Object> vars);


    /**
     * 启动流程
     *
     * @param processKey
     * @param bizKey
     * @param vars
     * @return
     */
    ProcessInstance startProcessByKey(final String processKey, final String bizKey, final Map<String, Object> vars);

    /**
     * 通过TaskId查询流程定义
     *
     * @param taskId
     * @return
     */
    ProcessDefinition queryProcessDefinitionByTaskId(String taskId);


    /**
     * 查询流程定义总数
     *
     * @return
     */
    Long queryProcessDefinitionCount();

    /**
     * 分页查询流程列表
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<ProcessDefinition> queryProcessDefinitions(Integer pageIndex, Integer pageSize);

    /**
     * 通过pDId 查询流程定义
     *
     * @param processDefinitionId
     * @return
     */
    ProcessDefinition queryProcessDefinition(String processDefinitionId);

    /**
     * 删除流程定义
     *
     * @param deploymentId
     */
    void deleteProcessDefinition(String deploymentId);


    /**
     * 查看流程图
     *
     * @param deploymentId
     * @param imageName
     */
    InputStream queryProcessDefinitionImage(String deploymentId, String imageName);


    //-----------------------------历史流程

    /**
     * 查询历史处理流程
     *
     * @param pid
     * @return
     */
    List<HistoricProcessInstance> queryHistoryProcess(String pid, Integer pageIndex, Integer pageSize);

    /**
     * 查询历史流程总数
     *
     * @param pid
     * @return
     */
    Long queryHistoryProcessCount(String pid);

    /**
     * 查询历史任务
     *
     * @param taskId
     * @return
     */
    List<HistoricTaskInstance> queryHistoryTask(String taskId, Integer pageIndex, Integer pageSize);


    /**
     * 查询历史任务总数
     *
     * @param taskId
     * @return
     */
    Long queryHistoryTaskCount(String taskId);

//    -----------------------------任务相关

    /**
     * 查询私有任务总数
     *
     * @param userCode
     * @return
     */
    Long queryPersonalTaskCount(String userCode);

    /**
     * 分页查询私有任务
     *
     * @param userCode
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<Task> queryPersonalTask(String userCode, Integer pageIndex, Integer pageSize);


    /**
     * 通过TaskID查询Task
     *
     * @param taskId
     * @return
     */
    Task queryTaskByTaskId(String taskId);

    /**
     * 通过ProcessInstanceId查询Task
     *
     * @param pid
     * @return
     */
    Task queryTaskByProcessInstanceId(String pid);


    /**
     * 查询任务图像，同时高亮
     *
     * @param taskId
     * @return
     */
    String queryTaskImage(String taskId);

    /**
     * 获取当前活动的坐标
     *
     * @param taskId
     * @return
     */
    Map<String, Integer> queryCurrentActivityCoordinate(String taskId);

    /**
     * 通过taskId查询Task Form
     *
     * @param taskId
     * @return
     */
    Map<String, Object> queryTaskForm(String taskId);

    /**
     * 获取任务中关联的业务ID
     *
     * @param taskId
     * @return
     */
    Long queryTaskBizId(String taskId);


    /**
     * 完成任务
     *
     * @param taskId
     */
    void completeTask(String taskId);

    /**
     * 完成任务，转到下一个task
     *
     * @param taskId
     * @param outGoing
     */
    void completeTask(String taskId, String outGoing);

    /**
     * 通过taskId查询活动
     *
     * @param taskId
     * @return
     */
    ActivityImpl queryAcitivityImplByTaskId(String taskId);

    /**
     * 获取task的出口
     *
     * @param taskId
     * @return
     */
    List<String> queryTaskTransNames(String taskId);

    /**
     * 通过PID获取Task的出口
     *
     * @param pid
     * @return
     */
    List<String> queryTaskTransNamesByPid(String pid);

    /**
     * 把任务转办新用户
     *
     * @param taskId
     * @param userCode
     */
    void transferUserInTask(String taskId, String userCode);


    /**
     * 添加批注
     *
     * @param taskId
     * @param userId
     * @param comment
     */
    void addComment(String taskId, String userId, String comment);

    /**
     * 添加批注，同时完成任务
     *
     * @param taskId
     * @param userId
     * @param comment
     * @param outGoing
     */
    void addCommentAndComplete(String taskId, String userId, String comment, String outGoing);

    /**
     * 当前任务得的批注列表
     *
     * @param taskId
     * @return
     */
    List<Comment> queryComments(String taskId);

    /**
     * 查询流程所有的批注
     *
     * @param pid
     * @return
     */
    List<Comment> queryProcessInstanceComments(String pid);


    /**
     * 获取业务主键（统一业务规则）
     *
     * @param bizKey
     * @return
     */
    Long getPrimaryKey(String bizKey);


    Long queryProcessIntanceCount();

    List<ProcessInstance> queryProcessIntanceList(Integer pageIndex, Integer pageSize);

}
