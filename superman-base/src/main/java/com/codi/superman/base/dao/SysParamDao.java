package com.codi.superman.base.dao;

import com.codi.base.dao.BaseDAO;
import com.codi.superman.base.domain.SysParam;

import java.util.List;

/**
 * 参数DAO
 *
 * @author spy
 * @date 2016-12-25 17:16
 */
public interface SysParamDao extends BaseDAO<SysParam> {

    /**
     * 增加参数
     *
     * @param record
     * @return
     */
    SysParam insert(SysParam record);

    /**
     * 删除参数
     *
     * @param id
     * @return
     */
    int deleteByParamId(Long id);

    /**
     * 查询参数
     *
     * @param paramCode
     * @return
     */
    SysParam selectParam(String paramCode);

    /**
     * 查询参数列表
     *
     * @param paramCode 参数
     * @param state     如果为null查询所有
     * @return
     */
    List<SysParam> selectParams(String paramCode, String state);

    /**
     * 更新参数
     *
     * @param record
     * @return
     */
    int updateParam(SysParam record);

    /**
     * 更新参数状态
     *
     * @param paramId
     * @param state
     * @return
     */
    int updateParamState(Long paramId, String state);

    /**
     * 查询参数总数
     *
     * @return
     */
    Long selectParamsCount();

    /**
     * 查询参数列表
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<SysParam> selectParams(Integer pageIndex, Integer pageSize);
}
