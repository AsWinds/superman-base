package com.codi.superman.base.dao;

import com.codi.base.dao.BaseDAO;
import com.codi.superman.base.domain.SysCommonGroup;

import java.util.List;

/**
 * 亲，写个类注释呗
 *
 * @author 周翔
 * @date 2017-04-07 11:21
 */
public interface SysCommonGroupDao extends BaseDAO<SysCommonGroup> {
    /**
     * 管理员删除分组
     *
     * @param groupId
     * @return
     */
    int deleteByPrimaryKey(Long groupId);

    /**
     * 管理员添加分组
     * @param record
     * @return
     */
    int insert(SysCommonGroup record);


    /**
     * 管理员修改分组
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(SysCommonGroup record);


    /**
     * 管理员得到所有分组
     *
     * @return
     */
    List<SysCommonGroup> selectAllGroup(Integer pageIndex, Integer pageSize);

    /**
     * 管理员通过业务分组查找分组名称
     *
     * @return
     */
    List<SysCommonGroup> selectByBizGroupCode(String bizGroupCode);

    /**
     * 管理员得到分组总数
     *
     * @return
     */
    int selectGroupCount();

    /**
     * 得到的所有公告信息（不带分页功能）
     *
     * @return
     */
    List<SysCommonGroup> selectAllGroup();

    /**
     * 通过业务代号得到分组id
     *
     * @param bizCode
     * @return
     */
    Long selectGroupIdByBizCode(String bizCode);

    /**
     * 用作分组代号唯一性校验
     *
     * @param bizCode
     * @return
     */
    int selectCountByBizCode(String bizCode);
}
