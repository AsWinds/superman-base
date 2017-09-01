package com.codi.superman.workflow.dao;

import com.codi.base.dao.BaseDAO;
import com.codi.superman.workflow.domain.SysBizLeave;

/**
 * 请假
 *
 * @author spy
 * @date 2017-04-27 17:30
 */
public interface SysBizLeaveDao extends BaseDAO<SysBizLeave> {
    int deleteByPrimaryKey(Long id);

    int insert(SysBizLeave record);

    int insertSelective(SysBizLeave record);

    SysBizLeave selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysBizLeave record);

    int updateByPrimaryKey(SysBizLeave record);
}
