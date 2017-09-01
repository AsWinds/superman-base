package com.codi.superman.workflow.controller;

import com.codi.base.domain.BaseResult;
import com.codi.base.exception.BaseAppException;
import com.codi.base.util.StringUtil;
import com.codi.superman.base.domain.SysUser;
import com.codi.superman.base.util.SessionUtil;
import com.codi.superman.workflow.service.WorkFlowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 流程管理
 *
 * @author shi.pengyan
 * @date 2017-04-18 20:06
 */
@Slf4j
@RestController
@RequestMapping("/sys/workflow")
public class WorkFlowController {

    @Autowired
    private WorkFlowService workFlowService;

    /**
     * 上传流程定义
     *
     * @return
     */
    public BaseResult uploadProcessDefinition() {
        //TODO
        return BaseResult.success();
    }

    /**
     * 查询最新的流程定义总数
     *
     * @return
     */
    @RequestMapping(value = "qryProcessDefinitionCount", method = RequestMethod.GET)
    public BaseResult queryProcessDefinitionCount() {
        return BaseResult.success(workFlowService.queryProcessDefinitionCount());
    }

    /**
     * 分页获取最新的流程列表
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "qryProcessDefinitions", method = RequestMethod.GET)
    public BaseResult queryProcessDefinitions(
        @RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {

        return BaseResult.success(workFlowService.queryProcessDefinitions(pageIndex, pageSize));
    }

    /**
     * 删除与流程定义
     *
     * @param deploymentId
     * @return
     */
    @RequestMapping(value = "processDefinition/{deploymentId}", method = RequestMethod.DELETE)
    public BaseResult deleteProcessDefinition(@PathVariable(name = "deploymentId") String deploymentId) {

        workFlowService.deleteProcessDefinition(deploymentId);

        return BaseResult.success();
    }

    /**
     * 查看流程定义图片
     *
     * @param request
     * @param response
     * @param deploymentId
     * @param resourceName
     */
    @RequestMapping(value = "processDefinition/image", method = RequestMethod.GET)
    public void queryProcessDefinitionImage(HttpServletRequest request,
                                            HttpServletResponse response,
                                            @RequestParam(name = "deploymentId") String deploymentId,
                                            @RequestParam(name = "resourceName") String resourceName) {

        byte[] bytes = new byte[1024];
        int len = 0;
        try {
            // 读取文件
            InputStream reader = workFlowService.queryProcessDefinitionImage(deploymentId, resourceName);
            // 写入浏览器的输出流
            OutputStream out = response.getOutputStream();

            while ((len = reader.read(bytes)) > 0) {
                out.write(bytes, 0, len);
            }
        } catch (Exception e) {
            log.error("flush image to browser error ", e);
        }

    }

    /**
     * 获取个人任务总数
     *
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "task/myTaskCount", method = RequestMethod.GET)
    public BaseResult queryPersonalTaskCount() throws BaseAppException {
        SysUser sysUser = SessionUtil.getSessionUser();

        log.debug("sys user={}", sysUser);

        return BaseResult.success(workFlowService.queryPersonalTaskCount(sysUser.getUserCode()));
    }

    /**
     * 分页获取个人任务
     *
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "task/myTasks", method = RequestMethod.GET)
    public BaseResult queryPersonalTask(
        @RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) throws BaseAppException {
        SysUser sysUser = SessionUtil.getSessionUser();

        log.debug("sys user={}", sysUser);

        return BaseResult.success(workFlowService.queryPersonalTask(sysUser.getUserCode(), pageIndex, pageSize));
    }


    @RequestMapping(value = "task/trans", method = RequestMethod.GET)
    public BaseResult queryTaskTrans(
        @RequestParam(name = "taskId", required = false) String taskId,
        @RequestParam(name = "pid", required = false) String pid) throws BaseAppException {
        if (StringUtil.isNotEmpty(taskId)) {
            return BaseResult.success(workFlowService.queryTaskTransNames(taskId));
        }

        if (StringUtil.isNotEmpty(pid)) {
            return BaseResult.success(workFlowService.queryTaskTransNamesByPid(pid));
        }
        log.error("need taskId or pid, plz check.");

        return BaseResult.success(false);
    }

    @RequestMapping(value = "task/transferUser", method = RequestMethod.POST)
    public BaseResult transferUser(@RequestParam(name = "taskId") String taskId,
                                   @RequestParam(name = "userCode") String userCode) {
        workFlowService.transferUserInTask(taskId, userCode);
        return BaseResult.success();
    }


    /**
     * 查询实例下的评注
     *
     * @param pid
     * @return
     */
    @RequestMapping(value = "processInstance/comments", method = RequestMethod.GET)
    public BaseResult queryProcessInstanceComments(@RequestParam(name = "pid", required = false) String pid) {
        return BaseResult.success(workFlowService.queryProcessInstanceComments(pid));
    }

    /**
     * 查询流程实例总数
     *
     * @return
     */
    @RequestMapping(value = "processInstance/count", method = RequestMethod.GET)
    public BaseResult queryProcessInstanceCount() {
        return BaseResult.success(workFlowService.queryProcessIntanceCount());
    }

    /**
     * 分页查询流程实例
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "processInstance/list", method = RequestMethod.GET)
    public BaseResult queryProcessIntanceList(
        @RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {

        return BaseResult.success(workFlowService.queryProcessIntanceList(pageIndex, pageSize));
    }
}
