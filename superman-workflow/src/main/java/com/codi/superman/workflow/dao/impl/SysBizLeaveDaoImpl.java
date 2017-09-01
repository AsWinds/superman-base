package com.codi.superman.workflow.dao.impl;

import com.codi.base.dao.BaseDAOImpl;
import com.codi.superman.workflow.dao.SysBizLeaveDao;
import com.codi.superman.workflow.domain.SysBizLeave;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2017-04-26 13:40
 */
@Slf4j
@Repository
public class SysBizLeaveDaoImpl extends BaseDAOImpl<SysBizLeave> implements SysBizLeaveDao {
    @Override
    public int deleteByPrimaryKey(Long id) {
        return this.delete(generateStatement("deleteByPrimaryKey"), id);
    }

    @Override
    public int insert(SysBizLeave record) {
        return 0;
    }

    @Override
    public int insertSelective(SysBizLeave record) {
        return this.insert(generateStatement("insertSelective"), record);
    }

    @Override
    public SysBizLeave selectByPrimaryKey(Long id) {
        return this.getObject(generateStatement("selectByPrimaryKey"), id);
    }

    @Override
    public int updateByPrimaryKeySelective(SysBizLeave record) {
        return this.update(generateStatement("updateByPrimaryKeySelective"), record);
    }

    @Override
    public int updateByPrimaryKey(SysBizLeave record) {
        return this.update(generateStatement("updateByPrimaryKey"), record);
    }
}
