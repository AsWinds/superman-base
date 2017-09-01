package com.codi.superman.base.dao;

import com.codi.base.dao.BaseDAO;
import com.codi.superman.base.domain.SysBulletin;

import java.util.List;
import java.util.Map;

/**
 * 亲，写个类注释呗
 *
 * @author 周翔
 * @date 2017-04-07 11:19
 */
public interface SysBulletinDao extends BaseDAO<SysBulletin> {
    /**
     * 管理员删除公告信息
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 管理员添加公告信息
     * @param record
     * @return
     */
    int insert(SysBulletin record);


    /**
     * 管理员通过id得到公告信息
     *
     * @param id
     * @return
     */
    SysBulletin selectByPrimaryKey(Long id);

//    /**
//     * 管理员通过groupId得到公告信息
//     * @param id
//     * @return
//     */
//    List<Map<String, Object>> selectBulletinsByGroupId(Long id, Integer pageIndex, Integer pageSize);

    /**
     * 管理员修改公告信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(SysBulletin record);


    /**
     * 管理员得到的所有公告信息
     *
     * @return
     */
    List<Map<String, Object>> selectAllBulletinAdmin(Long id, Integer pageIndex, Integer pageSize);

    /**
     * 管理员得到公告总数
     *
     * @return
     */
    int selectBulletinCount();

    /**
     * 管理员得到不同分组的公告总数
     *
     * @param groupId
     * @return
     */
    int selectCountByGroupId(Long groupId);

    /**
     * 客户端查询公告信息的标题,日期,id（PC和APP）
     *
     * @return
     */
    Map<String, Object> selectBulletinByGroupId(Long groupId, Integer pageIndex, Integer pageSize);

    /**
     * 客户端根据id查询公告内容
     *
     * @param id
     * @return
     */
    SysBulletin selectBulletinByIdUser(Long id);


}
