package com.codi.superman.base.service;

import com.codi.base.exception.BaseAppException;
import com.codi.superman.base.domain.SysCommonGroup;

import java.util.List;

/**
 * Created by 周翔 on 2017/4/12.
 */
public interface SysCommonGroupService {
    /**
     * 管理员得到所有分组
     *
     * @return
     * @throws BaseAppException
     */
    List<SysCommonGroup> queryGroupAdmin(Integer pageIndex, Integer pageSize) throws BaseAppException;

    /**
     * 通过业务分组得到分组名
     *
     * @return
     * @throws BaseAppException
     */
    List<SysCommonGroup> queryGroupByBizGroupCode(String bizGroupCode) throws BaseAppException;

    /**
     * 添加分组
     *
     * @return
     */
    int addGroup(SysCommonGroup sysCommonGroup) throws BaseAppException;

    /**
     * 删除分组
     *
     * @param id
     * @return
     */
    int deleteGroup(Long id) throws BaseAppException;

    /**
     * 更新分组
     *
     * @return
     */
    int updateGroup(Long groupId, String groupName, String description, String bizCode, String bizGroupCode) throws BaseAppException;

    /**
     * 管理员得到分组总数
     *
     * @return
     * @throws BaseAppException
     */
    int selectGroupCount() throws BaseAppException;

    /**
     * 验证分组代号唯一性
     *
     * @param bizCode
     * @return
     */
    public Boolean checkBizCode(String bizCode);
}
