package com.codi.superman.base.service;

import com.codi.base.exception.BaseAppException;
import com.codi.superman.base.domain.SysParam;

import java.util.List;
import java.util.Map;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2016-12-25 17:18
 */
public interface SysParamService {

    /**
     * 增加参数
     *
     * @param sysParam
     * @return
     * @throws BaseAppException
     */
    SysParam addParam(SysParam sysParam) throws BaseAppException;

    /**
     * 删除param
     *
     * @param paramId
     * @return
     * @throws BaseAppException
     */
    int delParam(Long paramId) throws BaseAppException;

    /**
     * @param sysParam
     * @return
     * @throws BaseAppException
     */
    int updateParam(SysParam sysParam) throws BaseAppException;

    /**
     * 启用参数
     *
     * @param paramId
     * @return
     * @throws BaseAppException
     */
    int enableParam(Long paramId) throws BaseAppException;

    /**
     * 禁用参数
     *
     * @param paramId
     * @return
     * @throws BaseAppException
     */
    int disableParam(Long paramId) throws BaseAppException;

    /**
     * @param paramCode
     * @return
     * @throws BaseAppException
     */
    SysParam queryParam(String paramCode) throws BaseAppException;

    /**
     * @param paramCode
     * @return
     * @throws BaseAppException
     */
    List<SysParam> queryParams(String paramCode) throws BaseAppException;

    /**
     * 查询参数列表
     *
     * @param paramCode
     * @param state
     * @return
     * @throws BaseAppException
     */
    List<SysParam> queryParams(String paramCode, String state) throws BaseAppException;

    /**
     * 查询总数
     *
     * @return
     * @throws BaseAppException
     */
    Long queryParamsCount() throws BaseAppException;

    /**
     * 分页查询列表
     *
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws BaseAppException
     */
    List<SysParam> queryParams(Integer pageIndex, Integer pageSize) throws BaseAppException;

    /**
     * 将sysParam中moreValue转成Map
     *
     * @param sysParam
     * @return
     */
    Map<String, String> queryParamMoreValueMap(SysParam sysParam);
}
