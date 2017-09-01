package com.codi.superman.workflow.dao;

import com.codi.base.dao.BaseDAO;
import com.codi.superman.workflow.domain.SysBizWorkFlow;

import java.util.List;

/**
 * 亲，写个类注释呗
 *
 * @author spy
 * @date 2017-05-08 15:07
 */
public interface SysBizWorkFlowDao extends BaseDAO<SysBizWorkFlow> {
    String TABLE_NAME = "SYS_BIZ_WORKFLOW";
    String PRIMARY_KEY = "ID";

    int deleteByPrimaryKey(Long id);

    int insert(SysBizWorkFlow record);

    int insertSelective(SysBizWorkFlow record);

    SysBizWorkFlow selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysBizWorkFlow record);

    int updateByPrimaryKey(SysBizWorkFlow record);


    List<SysBizWorkFlow> queryList(Long userId, Integer state, Integer pageIndex, Integer pageSize);

    int updateByBiz(SysBizWorkFlow record);
}
