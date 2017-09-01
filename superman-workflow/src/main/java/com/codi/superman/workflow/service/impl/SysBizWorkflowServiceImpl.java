package com.codi.superman.workflow.service.impl;

import com.codi.base.domain.BaseServiceImpl;
import com.codi.superman.base.domain.SysDB;
import com.codi.superman.base.domain.SysDBField;
import com.codi.superman.base.service.SysDBService;
import com.codi.superman.workflow.common.WorkFlowConst;
import com.codi.superman.workflow.dao.SysBizWorkFlowDao;
import com.codi.superman.workflow.domain.SysBizWorkFlow;
import com.codi.superman.workflow.service.SysBizWorkFlowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2017-05-08 15:12
 */
@Slf4j
@Service
@Transactional
public class SysBizWorkflowServiceImpl extends BaseServiceImpl implements SysBizWorkFlowService {

    @Resource
    private SysBizWorkFlowDao sysBizWorkFlowDao;

    @Autowired
    private SysDBService sysDBService;

    @Override
    public Long queryCount(Long userId, Integer state) {
        SysDB sysDB = new SysDB();
        sysDB.setTableName(SysBizWorkFlowDao.TABLE_NAME);
        sysDB.setPrimaryKeyName(SysBizWorkFlowDao.PRIMARY_KEY);

        List<SysDBField> fieldList = new ArrayList<>(2);
        SysDBField field1 = new SysDBField();
        field1.setColumnName("USER_ID");
        field1.setColumnValue(userId);

        SysDBField field2 = new SysDBField();
        field2.setColumnName("STATE");
        field2.setColumnValue(state);

        fieldList.add(field1);
        fieldList.add(field2);
        sysDB.setIncludeFields(fieldList);

        return sysDBService.getCount(sysDB);
    }

    @Override
    public List<SysBizWorkFlow> queryList(Long userId, Integer state, Integer pageIndex, Integer pageSize) {
        if (pageSize > 100) {
            pageSize = 10;
        }


        return sysBizWorkFlowDao.queryList(userId, state, pageIndex, pageSize);
    }

    @Override
    public int add(SysBizWorkFlow sysBizWorkFlow) {

        Date now = new Date();

        sysBizWorkFlow.setState(WorkFlowConst.PENDING);
        sysBizWorkFlow.setStateDate(now);
        sysBizWorkFlow.setCreateDate(now);
        sysBizWorkFlow.setUpdateDate(now);

        return sysBizWorkFlowDao.insertSelective(sysBizWorkFlow);
    }

    @Override
    public int add(Long userId, String userCode, String bizType, String bizKey, String desc) {
        SysBizWorkFlow sysBizWorkFlow = new SysBizWorkFlow();

        sysBizWorkFlow.setUserId(userId);
        sysBizWorkFlow.setUserCode(userCode);
        sysBizWorkFlow.setBizType(bizType);
        sysBizWorkFlow.setBizKey(bizKey);
        sysBizWorkFlow.setDescription(desc);

        return add(sysBizWorkFlow);
    }

    @Override
    public int updateState(Long workFlowId, Integer state) {
        SysBizWorkFlow sysBizWorkFlow = new SysBizWorkFlow();

        sysBizWorkFlow.setId(workFlowId);
        sysBizWorkFlow.setState(state);
        sysBizWorkFlow.setStateDate(new Date());

        return sysBizWorkFlowDao.updateByPrimaryKeySelective(sysBizWorkFlow);
    }

    @Override
    public int updateState(String bizType, String bizKey, Integer state) {
        SysBizWorkFlow sysBizWorkFlow = new SysBizWorkFlow();

        sysBizWorkFlow.setBizType(bizType);
        sysBizWorkFlow.setBizKey(bizKey);
        sysBizWorkFlow.setState(state);
        sysBizWorkFlow.setStateDate(new Date());

        return sysBizWorkFlowDao.updateByBiz(sysBizWorkFlow);
    }
}
