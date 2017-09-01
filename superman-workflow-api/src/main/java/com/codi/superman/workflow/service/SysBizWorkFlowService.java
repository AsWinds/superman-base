package com.codi.superman.workflow.service;

import com.codi.superman.workflow.domain.SysBizWorkFlow;

import java.util.List;

/**
 * 所有流程统计信息都在这里
 *
 * @author shi.pengyan
 * @date 2017-05-08 15:10
 */
public interface SysBizWorkFlowService {

    Long queryCount(Long userId, Integer state);

    List<SysBizWorkFlow> queryList(Long userId, Integer state, Integer pageIndex, Integer pageSize);

    /**
     * 增加记录
     *
     * @param sysBizWorkFlow
     * @return
     */
    int add(SysBizWorkFlow sysBizWorkFlow);

    /**
     * 增加记录
     *
     * @param userId
     * @param userCode
     * @param bizType
     * @param bizKey
     * @param desc
     * @return
     */
    int add(Long userId, String userCode, String bizType, String bizKey, String desc);

    /**
     * 更新状态
     *
     * @param workFlowId
     * @param state
     * @return
     */
    int updateState(Long workFlowId, Integer state);


    /**
     * 通过业务来更新状态
     *
     * @param bizType
     * @param bizKey
     * @param state
     * @return
     */
    int updateState(String bizType, String bizKey, Integer state);

}
